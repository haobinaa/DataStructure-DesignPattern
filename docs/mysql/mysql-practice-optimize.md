### mysql 性能

#### 最大数据量

MySQL没有限制单表最大记录数，它取决于操作系统对文件大小的限制。以 Linux Ext4文件系统为例, 理论最大容量为16TB。

《阿里巴巴Java开发手册》提出单表行数超过500万行或者单表容量超过2GB，才推荐分库分表

#### 最大并发数

并发数是指同一时刻数据库能处理多少个请求，由`max_connections`和`max_user_connections`决定

`max_connections`是指MySQL实例的最大连接数，上限值是`16384`，`max_user_connections`是指每个数据库用户的最大连接数。

推荐 `max_used_connections / max_connections` 比例超过 10%, 配置示例:
```mysql
[mysqld]
max_connections = 100
max_used_connections = 20
```

#### 查询耗时

建议将单次查询耗时控制在0.5秒以内，0.5秒是个经验值，源于用户体验的3秒原则。如果用户的操作3秒内没有响应，将会厌烦甚至退出。

响应时间=客户端UI渲染耗时+网络请求耗时+应用程序处理耗时+查询数据库耗时

0.5秒就是留给数据库1/6的处理时间。


### 
### limit 优化

分页查询中如下：
```
SELECT * 
FROM   operation 
WHERE  type = 'SQLStats' 
       AND name = 'SlowLog' 
ORDER  BY create_time 
LIMIT  1000, 10;
```

当`limit 10000, 10` 的时候， 虽然还是只查10条但是mysql并不知道第10000条记录从何开始，在加了索引的情况下，比如对`type, name, create_time` 加上组合索引，然后使用上一页的最大值当作查询条件，就可以命中索引，快速筛选出大量数据的偏移量位置.

优化后sql语句如下:
```
SELECT * 
FROM   operation 
WHERE  type = 'SQLStats' 
       AND name = 'SlowLog' 
ORDER  BY create_time 
LIMIT  1000, 10;
```

这种查询并不会随着数据量的增长而发生变化，执行时间比较固定

#### 隐式转换

mysql中查询变量与字段类型定义不匹配是一个隐形的坑， 如:
```
mysql> explain extended SELECT * 
     > FROM   my_balance b 
     > WHERE  b.bpn = 14000000123 
     >       AND b.isverified IS NULL ;
mysql> show warnings;
| Warning | 1739 | Cannot use ref access on index 'bpn' due to type or collation conversion on field 'bpn'
```

其中字段 bpn 的定义为 varchar(20)，MySQL 的策略是将字符串转换为数字之后再比较。函数作用于表字段，索引失效。

所以这里要注意， 有时候框架会做一些转换，最后执行的sql语句中就有可能不会命中索引

