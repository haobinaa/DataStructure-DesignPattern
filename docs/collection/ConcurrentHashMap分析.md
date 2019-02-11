### java7 中 ConcurrnentHashMap分析

#### 数据结构
基于JDK7的`ConcurrentHashMap`的底层数据结构仍然是数组和链表。与HashMap不同的是，ConcurrentHashMap最外层不是一个大的数组，而是一个Segment的数组。每个Segment包含一个与HashMap数据结构差不多的链表数组。整体数据结构如下图所示:

![](../../images/concurrenthashmap_java7.png)

#### 寻址方式, 获取在segment数组上的索引

在读写某个Key时，先取该Key的哈希值。并将哈希值的高N位对Segment个数取模从而得到该Key应该属于哪个Segment，接着如同操作HashMap一样操作这个Segment，为保证值均匀分布到不同的Segment
，使用的hash方法如下:
``` 
private int hash(Object k) {
  int h = hashSeed;
  if ((0 != h) && (k instanceof String)) {
    return sun.misc.Hashing.stringHash32((String) k);
  }
  h ^= k.hashCode();
  h += (h <<  15) ^ 0xffffcd7d;
  h ^= (h >>> 10);
  h += (h <<   3);
  h ^= (h >>>  6);
  h += (h <<   2) + (h << 14);
  return h ^ (h >>> 16);
}
```

同样为了提高取模运算效率，通过如下计算，ssize即为大于concurrencyLevel的最小的2的N次方，同时segmentMask为2^N-1。这一点跟上文中计算数组长度的方法一致。对于某一个Key
的哈希值，只需要向右移segmentShift位以取高sshift位，再与segmentMask取与操作即可得到它在Segment数组上的索引:
``` 
int sshift = 0;
int ssize = 1;
while (ssize < concurrencyLevel) {
  ++sshift;
  ssize <<= 1;
}
this.segmentShift = 32 - sshift;
this.segmentMask = ssize - 1;
Segment<K,V>[] ss = (Segment<K,V>[])new Segment[ssize];

```

#### get方法

Segment继承自ReentrantLock，所以我们可以很方便的对每一个Segment上锁。

只需要将 Key 通过 Hash 之后定位到具体的 Segment ，再通过一次 Hash 定位到具体的元素上。由于 HashEntry 中的 value 属性是用 volatile 关键词修饰的，保证了内存可见性，所以每次获取时都是最新值

#### put方法

内部 HashEntry 类 :
``` 
  static final class HashEntry<K,V> {
        final int hash;
        final K key;
        volatile V value;
        volatile HashEntry<K,V> next;

        HashEntry(int hash, K key, V value, HashEntry<K,V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }
```

对于写操作，并不要求同时获取所有Segment的锁，因为那样相当于锁住了整个Map。它会先获取该Key-Value对所在的Segment的锁，获取成功后就可以像操作一个普通的HashMap一样操作该Segment，并保证该Segment的安全性。

首先也是先通过key的hash定位到具体的segment， 与HashMap不同的是，ConcurrentHashMap会在put之前就进行一次扩容校验。HashMap 是插入元素之后再看是否需要扩容，有可能扩容之后后续就没有插入就浪费了本次扩容(扩容非常消耗性能)

#### size方法

put、remove和get操作只需要关心一个Segment，而size操作需要遍历所有的Segment才能算出整个Map的大小。put、remove和get操作只需要关心一个Segment，而size操作需要遍历所有的Segment才能算出整个Map的大小。一个简单的方案是，先锁住所有Sgment，计算完后再解锁。但这样做，在做size操作时，不仅无法对Map进行写操作，同时也无法进行读操作，不利于对Map的并行操作。

为更好支持并发操作，ConcurrentHashMap会在不上锁的前提逐个Segment计算3次size，如果某相邻两次计算获取的所有Segment的更新次数（每个Segment都与HashMap一样通过modCount
跟踪自己的修改次数，Segment每修改一次其modCount加一）相等，说明这两次计算过程中无更新操作，则这两次计算出的总size相等，可直接作为最终结果返回。如果这三次计算过程中Map有更新，则对所有Segment加锁重新计算Size。

#### 与HashMap的比较

ConcurrentHashMap与HashMap相比，有以下不同点：
- ConcurrentHashMap线程安全，而HashMap非线程安全
- HashMap允许Key和Value为null，而ConcurrentHashMap不允许
- HashMap不允许通过Iterator遍历的同时通过HashMap修改(fail-fast异常)，而ConcurrentHashMap允许该行为，并且该更新对后续的遍历可见


### java8 中的实现

Java 8为进一步提高并发性，摒弃了分段锁的方案，而是直接使用一个大的数组，采用了 `CAS + synchronized` 来保证并发安全性。同时为了提高哈希碰撞下的寻址性能，Java 8在链表长度超过一定阈值（8）时将链表（寻址时间复杂度为O(N)）转换为红黑树（寻址时间复杂度为O(long
(N))）。其数据结构如下图所示：

![](../../images/concurrenthashmap_java8.jpg)

#### Node结构(HashEntry)

将 1.7 中存放数据的 HashEntry 改为 Node，但作用都是相同的, 其中val，next都采用volatile修饰，保证了可见性
```
static class Node<K,V> implements Map.Entry<K,V> {
    final int hash;
    final K key;
    volatile V val;
    volatile Node<K,V> next;

    Node(int hash, K key, V val, Node<K,V> next) {
        this.hash = hash;
        this.key = key;
        this.val = val;
        this.next = next;
    }

    public final K getKey()       { return key; }
    public final V getValue()     { return val; }
    public final int hashCode()   { return key.hashCode() ^ val.hashCode(); }
    public final String toString(){ return key + "=" + val; }
    public final V setValue(V value) {
        throw new UnsupportedOperationException();
}
```

#### put方法

put方法如下:
``` 
    final V putVal(K key, V value, boolean onlyIfAbsent) {
        if (key == null || value == null) throw new NullPointerException();
        int hash = spread(key.hashCode());
        int binCount = 0;
        for (Node<K,V>[] tab = table;;) { // 步骤1
            Node<K,V> f; int n, i, fh;
            if (tab == null || (n = tab.length) == 0) // 步骤2
                tab = initTable();
            else if ((f = tabAt(tab, i = (n - 1) & hash)) == null) { // 步骤3
                if (casTabAt(tab, i, null,
                             new Node<K,V>(hash, key, value, null)))
                    break;                   // no lock when adding to empty bin
            }
            else if ((fh = f.hash) == MOVED) // 步骤4
                tab = helpTransfer(tab, f);
            else {
                V oldVal = null;
                synchronized (f) { // 步骤5
                    if (tabAt(tab, i) == f) {
                        if (fh >= 0) {
                            binCount = 1;
                            for (Node<K,V> e = f;; ++binCount) {
                                K ek;
                                if (e.hash == hash &&
                                    ((ek = e.key) == key ||
                                     (ek != null && key.equals(ek)))) {
                                    oldVal = e.val;
                                    if (!onlyIfAbsent)
                                        e.val = value;
                                    break;
                                }
                                Node<K,V> pred = e;
                                if ((e = e.next) == null) {
                                    pred.next = new Node<K,V>(hash, key,
                                                              value, null);
                                    break;
                                }
                            }
                        }
                        else if (f instanceof TreeBin) {
                            Node<K,V> p;
                            binCount = 2;
                            if ((p = ((TreeBin<K,V>)f).putTreeVal(hash, key,
                                                           value)) != null) {
                                oldVal = p.val;
                                if (!onlyIfAbsent)
                                    p.val = value;
                            }
                        }
                    }
                }
                if (binCount != 0) {
                    if (binCount >= TREEIFY_THRESHOLD) // 步骤6
                        treeifyBin(tab, i);
                    if (oldVal != null)
                        return oldVal;
                    break;
                }
            }
        }
        addCount(1L, binCount);
        return null;
    }
```
流程如下:
1. 根据 key 计算出 hashcode
2. 判断是否需要进行初始化
3. f 即为当前 key 定位出的 Node，如果为空表示当前位置可以写入数据，利用 CAS 尝试写入，失败则自旋保证成功
4. 如果当前位置的 hashcode == MOVED == -1,则需要进行扩容
5. 如果都不满足，则利用 synchronized 锁写入数据
6. 如果数量大于 TREEIFY_THRESHOLD 则要转换为红黑树。


#### get方法

``` 
    public V get(Object key) {
        Node<K,V>[] tab; Node<K,V> e, p; int n, eh; K ek;
        int h = spread(key.hashCode());
        if ((tab = table) != null && (n = tab.length) > 0 &&
            (e = tabAt(tab, (n - 1) & h)) != null) {
            if ((eh = e.hash) == h) {
                if ((ek = e.key) == key || (ek != null && key.equals(ek)))
                    return e.val;
            }
            else if (eh < 0)
                return (p = e.find(h, key)) != null ? p.val : null;
            while ((e = e.next) != null) {
                if (e.hash == h &&
                    ((ek = e.key) == key || (ek != null && key.equals(ek))))
                    return e.val;
            }
        }
        return null;
    }
```

1. 根据计算出来的 hashcode 寻址，如果就在桶上那么直接返回值。
2. 如果是红黑树那就按照树的方式获取值
3. 都不满足那就按照链表的方式遍历获取值



### 参考资料

- [ConcurrentHashMap的演进](http://www.jasongj.com/java/concurrenthashmap/)
- [HashMap与ConcurrentHashMap](https://gitbook.cn/books/5a51ef3743ebf841f7e6fe86/index.html)