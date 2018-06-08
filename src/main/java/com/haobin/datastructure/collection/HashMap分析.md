### 1.概述
基于JDK1.7的HashMap，使用拉链法来解决hash冲突
![](https://raw.githubusercontent.com/haobinaa/DataStructure-DesignPattern/master/images/HashMap_1_7.png)

### 2.基本逻辑

#### 1)常量
``` 
// 初始容量，扩容容量必须是2的倍数
static final int DEFAULT_INITIAL_CAPACITY = 16;

// 最大容量，2的30次方  
static final int MAXIMUM_CAPACITY = 1 << 30;

// 加载因子，当长度大于阈值(存储容量*加载因子)，则进行扩容
static final float DEFAULT_LOAD_FACTOR = 0.75f;

// 阈值，达到阈值后进行resize扩容操作
int threshold;

// 存储Entry的数组
transient Entry<K,V>[] table;
```

#### 2)Entry
``` 
static class Entry<K,V> implements Map.Entry<K,V> {
    final K key;
    V value;
    // 下一个节点
    Entry<K,V> next;
    int hash;
    // 构造函数，每次用新节点作为链表的头结点
    Entry(int h, K k, V v, Entry<K,V> n) {
        value = v;
        next = n;
        key = k;
        hash = h;
    }

    public final K getKey() {
        return key;
    }

    public final V getValue() {
        return value;
    }

    public final V setValue(V newValue) {
        V oldValue = value;
        value = newValue;
        return oldValue;
    }
// 重写equals和hashcode
    public final boolean equals(Object o) {
        if (!(o instanceof Map.Entry))
            return false;
        Map.Entry e = (Map.Entry)o;
        Object k1 = getKey();
        Object k2 = e.getKey();
        if (k1 == k2 || (k1 != null && k1.equals(k2))) {
            Object v1 = getValue();
            Object v2 = e.getValue();
            if (v1 == v2 || (v1 != null && v1.equals(v2)))
                return true;
        }
        return false;
    }

    public final int hashCode() {
        return Objects.hashCode(getKey()) ^ Objects.hashCode(getValue());
    }

    public final String toString() {
        return getKey() + "=" + getValue();
    }

    /**
     * This method is invoked whenever the value in an entry is
     * overwritten by an invocation of put(k,v) for a key k that's already
     * in the HashMap.
     */
    void recordAccess(HashMap<K,V> m) {
    }

    /**
     * This method is invoked whenever the entry is
     * removed from the table.
     */
    void recordRemoval(HashMap<K,V> m) {
    }
}
```
#### 3）put操作
1. 通过key的hash值确定table下标 
2. 查找table下标，如果key存在则更新对应的value 
3. 如果key不存在则调用addEntry()方法 
``` 
public V put(K key, V value) {
    if (table == EMPTY_TABLE) {
        inflateTable(threshold);
    }
    // 键为 null 单独处理, 允许一个为null的key
    if (key == null)
        return putForNullKey(value);
    int hash = hash(key);
    // 确定桶下标
    int i = indexFor(hash, table.length);
    // 先找出是否已经存在键为 key 的键值对，如果存在的话就更新这个键值对的值为 value
    for (Entry<K,V> e = table[i]; e != null; e = e.next) {
        Object k;
        if (e.hash == hash && ((k = e.key) == key || key.equals(k))) {
            V oldValue = e.value;
            e.value = value;
            e.recordAccess(this);
            return oldValue;
        }
    }

    modCount++;
    // 插入新键值对
    addEntry(hash, key, value, i);
    return null;
}
```
HashMap 允许插入键位 null 的键值对，因为无法调用 null 的 hashCode()，也就无法确定该键值对的桶下标，只能通过强制指定一个桶下标来存放。HashMap 使用第 0 个桶存放键为 null 的键值对。
``` 
private V putForNullKey(V value) {
    for (Entry<K,V> e = table[0]; e != null; e = e.next) {
        if (e.key == null) {
            V oldValue = e.value;
            e.value = value;
            e.recordAccess(this);
            return oldValue;
        }
    }
    modCount++;
    addEntry(0, null, value, 0);
    return null;
}
```
使用链表的头插法，也就是新的键值对插在链表的头部，而不是链表的尾部。（设计者认为新加入的节点被使用的几率更大）
``` 
void addEntry(int hash, K key, V value, int bucketIndex) {
    if ((size >= threshold) && (null != table[bucketIndex])) {
        resize(2 * table.length);
        hash = (null != key) ? hash(key) : 0;
        bucketIndex = indexFor(hash, table.length);
    }

    createEntry(hash, key, value, bucketIndex);
}

void createEntry(int hash, K key, V value, int bucketIndex) {
    Entry<K,V> e = table[bucketIndex];
    // 头插法，链表头部指向新的键值对
    table[bucketIndex] = new Entry<>(hash, key, value, e);
    size++;
}
```
#### 4)确定桶下标
在操作hash表的时候，需要先确定桶的下标
``` 
int hash = hash(key);
int i = indexFor(hash, table.length);
```
(1)计算hash值
``` 
final int hash(Object k) {
    int h = hashSeed;
    if (0 != h && k instanceof String) {
        return sun.misc.Hashing.stringHash32((String) k);
    }

    h ^= k.hashCo de();
    
    // >>> 无符号右移
    h ^= (h >>> 20) ^ (h >>> 12);
    return h ^ (h >>> 7) ^ (h >>> 4);
}
public final int hashCode() {
    return Objects.hashCode(key) ^ Objects.hashCode(value);
}
```

#### 5) 扩容操作
扩容根据以下几个属性来判断

| 参数  | 含义 |
| ------------- | ------------- |
| capacity  | table 的容量大小，默认为 16，需要注意的是 capacity 必须保证为 2 的次方  |
| size  | table 的实际使用量  |
| threshold  |size 的临界值，size 必须小于 threshold，如果大于等于，就必须进行扩容操作|
| load_factor  |table 能够使用的比例，threshold = capacity * load_factor|


``` 
void resize(int newCapacity) {
    Entry[] oldTable = table;
    int oldCapacity = oldTable.length;
    if (oldCapacity == MAXIMUM_CAPACITY) {
        threshold = Integer.MAX_VALUE;
        return;
    }

    Entry[] newTable = new Entry[newCapacity];
    transfer(newTable);
    table = newTable;
    threshold = (int)(newCapacity * loadFactor);
}
// 将原来的Entry映射到新的数组
void transfer(Entry[] newTable) {
    Entry[] src = table;
    int newCapacity = newTable.length;
    for (int j = 0; j < src.length; j++) {
        Entry<K,V> e = src[j];
        if (e != null) {
            src[j] = null;
            do {
                Entry<K,V> next = e.next;
                int i = indexFor(e.hash, newCapacity);
                // 头插法， 插入节点指向整个链表
                e.next = newTable[i];
                // 头结点指向插入节点
                newTable[i] = e;
                // 遍历下一个节点
                e = next;
            } while (e != null);
        }
    }
}
```
### 3.HashMap的线程不安全体现
在resize的时候，会进行一步将原有元素重新映射到新的hash表的rehash操作，在多线程环境下，分析rehash步骤,只看关键代码
``` 
while(null != e) {
    Entry<K,V> next = e.next;
    e.next = newTable[i]; //
    newTable[i] = e;
    e = next;
}
```
1. Entry<K,V> next = e.next;——因为是单链表，如果要转移头指针，一定要保存下一个结点，不然转移后链表就丢了

2. e.next = newTable[i];——e 要插入到链表的头部，所以要先用 e.next 指向新的 Hash 表第一个元素

3. newTable[i] = e;——现在新 Hash 表的头指针仍然指向 e 没转移前的第一个元素，所以需要将新 Hash 表的头指针指向 e

4. e = next——转移 e 的下一个结点

假设这里有两个线程同时执行了put()操作，并进入了transfer()环节
``` 
while(null != e) {
    Entry<K,V> next = e.next; //线程1执行到这里被调度挂起了
    e.next = newTable[i];
    newTable[i] = e;
    e = next;
}
```
现在状态如下:
![](https://raw.githubusercontent.com/haobinaa/DataStructure-DesignPattern/master/images/rehash.jpg)

从上面的图我们可以看到，因为线程1的 e 指向了 key(3)，而 next 指向了 key(7)，在线程2 rehash 后，就指向了线程2 rehash 后的链表。

然后线程1被唤醒了：

1. 执行e.next = newTable[i]，于是 key(3)的 next 指向了线程1的新 Hash 表，因为新 Hash 表为空，所以e.next = null

2. 执行newTable[i] = e，所以线程1的新 Hash 表第一个元素指向了线程2新 Hash 表的 key(3)。好了，e 处理完毕。

3. 执行e = next，将 e 指向 next，所以新的 e 是 key(7)

然后该执行 key(3)的 next 节点 key(7):

1.现在的 e 节点是 key(7)，首先执行Entry<K,V> next = e.next,那么 next 就是 key(3)了

2. 执行e.next = newTable[i]，于是key(7) 的 next 就成了 key(3)

3. 执行newTable[i] = e，那么线程1的新 Hash 表第一个元素变成了 key(7)

4. 执行e = next，将 e 指向 next，所以新的 e 是 key(3)

现在状态变为：
![](https://raw.githubusercontent.com/haobinaa/DataStructure-DesignPattern/master/images/after-rehahs.jpg)

然后又该执行 key(7)的 next 节点 key(3)了:

1. 现在的 e 节点是 key(3)，首先执行Entry<K,V> next = e.next,那么 next 就是 null

2. 执行e.next = newTable[i]，于是key(3) 的 next 就成了 key(7)

3. 执行newTable[i] = e，那么线程1的新 Hash 表第一个元素变成了 key(3)

4. 执行e = next，将 e 指向 next，所以新的 e 是 key(7)

状态变成了:
![](https://raw.githubusercontent.com/haobinaa/DataStructure-DesignPattern/master/images/rehash-circle-list.jpg)

很明显，环形链表出现了,现在hashmap就是线程1的hashmap了
### 4.HashMap和HashTable
- HashTable 是同步的，它使用了 synchronized 来进行同步。它也是线程安全的，多个线程可以共享同一个 HashTable。HashMap 不是同步的，但是可以使用 ConcurrentHashMap，它是 HashTable 的替代，而且比 HashTable 可扩展性更好。
- HashMap 可以插入键为 null 的 Entry。
- HashMap 的迭代器是 fail-fast 迭代器，而 Hashtable 的 enumerator 迭代器不是 fail-fast 的。
- 由于 Hashtable 是线程安全的也是 synchronized，所以在单线程环境下它比 HashMap 要慢。
- HashMap 不能保证随着时间的推移 Map 中的元素次序是不变的。

