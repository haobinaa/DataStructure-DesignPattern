### redo 日志

我们所有的操作都是在内存中操作的，最终数据是要持久化到磁盘中去。比如一个事务提交后突然发生了故障，导致这个已提交的事务对数据库的更改也丢失了，这就违反了事务的持久性。要保证持久性，一个比较简单的做法是：在事务提交前就把该事务的修改都刷新到磁盘中，但是这个做法会导致一些问题：
- mysql存储是以页为单位，如果我们仅仅只改了一行数据就刷新整个数据页，这样太浪费了
- 随机io刷新起来很慢。一个事务可能涉及到多个数据页，而这些数据页又不是相邻的，随机IO比顺序IO要慢很多

后来对这个方案加以改进，我们只需让已提交的事务的更改永久生效，即使系统崩溃重启后也能把这种修改恢复出来。所以没必要每次都把内存的修改刷新到磁盘，只需要记录一下修改了哪些记录即可。这样系统崩溃恢复后，只需要按照这个 修改记录操作就可以更新数据页。这个修改记录就是redo日志

#### redo 日志格式

![](../../images/mysql/redo-log.png)
- type: 日志类型
- spaceId： 表空间id
- page number: 页号
- data： 日志内容

#### Min-Transacation(mtr)

`MySQL`把对底层页面中的一次原子访问的过程称之为一个`Mini-Transaction`，简称mtr。

一个mtr可以包含一组redo日志，在进行奔溃恢复时这一组redo日志作为一个不可分割的整体。

一个事务可以包含若干条语句，每一条语句其实是由若干个mtr组成，每一个mtr又可以包含若干条redo日志。


#### redo log block

InnoDB为了更好的进行系统奔溃恢复，把通过mtr生成的redo日志都放在了大小为512字节的页中,存储redo log的页称为block

#### redo log buffer

InnoDB为了解决磁盘速度过慢的问题而引入了Buffer Pool。同理，写入redo日志时也不能直接直接写到磁盘上，实际上在服务器启动时就向操作系统申请了一大片称之为redo log buffer的连续内存空间称为log buffer。 log buffer存储的就是一块块 log block


### redo log的写入过程

#### redo log 写入 log buffer

向log buffer中写入redo日志的过程是顺序的，也就是先往前边的block中写，当该block的空闲空间用完之后再往下一个block中写。InnoDB的提供了一个称之为`buf_free`的全局变量，该变量指明后续写入的redo日志应该写入到log buffer中的哪个位置。

一个mtr执行过程中可能产生若干条redo日志，这些redo日志是一个不可分割的组，所以其实并不是每生成一条redo日志，就将其插入到log buffer中，而是每个mtr运行过程中产生的日志先暂时存到一个地方，当该mtr结束的时候，将过程中产生的一组redo日志再全部复制到log buffer中。不同的mtr产生的一组redo日志占用的存储空间可能不一样，有的mtr产生的redo日志量很少，就会存入同一个block，有的mtr产生的redo日志量非常大，甚至可能占用多个block来存储。

##### Log Sequeue Number

InnoDB为记录已经写入的redo日志量，设计了一个称之为`Log Sequeue Number`的全局变量，翻译过来就是：日志序列号，简称lsn, InnoDB规定初始的lsn值为8704。

##### flushed_to_disk_lsn

redo日志是首先写到log buffer中，之后才会被刷新到磁盘上的redo日志文件。InnoDB的设计了一个称之为`buf_next_to_write`的全局变量，标记当前log buffer中已经有哪些日志被刷新到磁盘中了。如图：

![](../../images/mysql/flush-redo-disk.png)

当flushed_to_disk_lsn和lsn相同的时候，就代表所有的日志都刷新到磁盘了

##### flush链表中的LSN

mtr代表一次对底层页面的原子访问，在访问过程中可能会产生一组不可分割的redo日志，在mtr结束时，会把这一组redo日志写入到log buffer中。除此之外，在mtr结束时还有一件非常重要的事情要做，就是把在mtr执行过程中可能修改过的页面加入到Buffer Pool的flush链表。

当第一次修改某个缓存在Buffer Pool中的页面时，就会把这个页面对应的控制块插入到flush链表的头部，之后再修改该页面时由于它已经在flush链表中了，就不再次插入了。也就是说flush链表中的脏页是按照页面的第一次修改时间从大到小进行排序的。在这个过程中会在缓存页对应的控制块中记录两个关于页面何时修改的属性：
- `oldest_modification`：如果某个页面被加载到Buffer Pool后进行第一次修改，那么就将修改该页面的mtr开始时对应的lsn值写入这个属性
- `newest_modification`: 每修改一次页面，都会将修改该页面的mtr结束时对应的lsn值写入这个属性。也就是说该属性表示页面最近一次修改后对应的系统lsn值

所以，flush链表中的脏页按照修改发生的时间顺序进行排序，也就是按照oldest_modification代表的LSN值进行排序，被多次更新的页面不会重复插入到flush链表中，但是会更新`newest_modification`属性的值


### redo log 刷新到磁盘

mtr运行过程中产生的一组redo日志在mtr结束时会被复制到log buffer中，可是这些日志总在内存里呆着也不是个办法，在一些情况下它们会被刷新到磁盘里，常见的时机：

- log buffer空间不足时
- 事务提交时
- 后台线程刷新，后台有一个线程，大约每秒都会刷新一次log buffer中的redo日志到磁盘。

#### checkpoint

redo log空间有限。并且redo日志只是为了系统奔溃后恢复脏页用的，如果对应的脏页已经刷新到了磁盘，也就是说即使现在系统奔溃，那么在重启后也用不着使用redo日志恢复该页面了，所以该redo
日志也就没有存在的必要了，那么它占用的磁盘空间就可以被后续的redo日志所重用

InnoDB设计了一个全局变量`checkpoint_lsn`来代表当前系统中可以被覆盖的redo日志总量是多少，这个变量初始值也是8704。

一个增加`checkpoint_lsn`的操作，我们把这个过程称之为做一次`checkpoint`:

计算一下当前系统中可以被覆盖的redo日志对应的lsn值最大是多少。计算出当前系统中被最早修改的脏页对应的oldest_modification值，那凡是在系统lsn值小于该节点的oldest_modification
值时产生的redo日志都是可以被覆盖掉的，我们就把该脏页的`oldest_modification`赋值给`checkpoint_lsn`。然后把checkpoint的值存储起来就好了


#### 批量刷新脏页

如果当前系统修改页面的操作十分频繁，这样就导致写日志操作十分频繁，系统lsn值增长过快。如果后台的刷脏操作不能将脏页刷出，那么系统无法及时做checkpoint，可能就需要用户线程同步的从flush链表中把那些最早修改的脏页（oldest_modification最小的脏页）刷新到磁盘，这样这些脏页对应的redo日志就没用了，然后就可以去做checkpoint了。

### 崩溃恢复

redo log的作用就是在服务器崩溃了能恢复。恢复流程如下

#### 确定恢复的起点

数据恢复的起点只需要找到最近一次checkpoint的值就行

#### 确定恢复的终点

找到最后一个没有被写满的block，该block就是最后的终点。

#### 怎么恢复

知道了起点和终点之后，就可以按照redo日志的顺序依次扫描`checkpoint_lsn`之后的各条redo日志，按照日志中记载的内容将对应的页面恢复出来。


### redo log刷新到磁盘配置(innodb_flush_log_at_trx_commit)



