### 1.概述

 `HashMap` 是一个无序的 Map，因为每次根据 key 的 hashcode 映射到 Entry 数组上，所以遍历出来的顺序并不是写入的顺序。
 
 `LinkedHashMap`是一个有顺序的，继承于`HashMap`实现的，由一个双向链表构成。`LinkedHashMap`的排序方式有两种：
 - 根据写入顺序排序
 - 根据访问顺序排序
 
 其中根据访问顺序排序时，每次 get 都会将访问的值移动到链表末尾，这样重复操作就能的到一个按照访问顺序排序的链表。
 
 ### 2.结构
  
 #### 1) 节点
 
 `LinkedHashMap`的节点`Entry<K,V>`继承自`HashMap.Node<K,V>`，在其基础上扩展了一下。改成了一个双向链表。
 ``` 
     static class Entry<K,V> extends HashMap.Node<K,V> {
         Entry<K,V> before, after;
         Entry(int hash, K key, V value, Node<K,V> next) {
             super(hash, key, value, next);
         }
     }
 ```
 
 同时类里有两个成员变量head tail,分别指向内部双向链表的表头、表尾。
 ``` 
     //双向链表的头结点
     transient LinkedHashMap.Entry<K,V> head;
 
     //双向链表的尾节点
     transient LinkedHashMap.Entry<K,V> tail;
 ```
 
 #### 2) 构造函数
 ``` 
     //默认是false，则迭代时输出的顺序是插入节点的顺序。若为true，则输出的顺序是按照访问节点的顺序。
     //为true时，可以在这基础之上构建一个LruCach
     final boolean accessOrder;
 
     public LinkedHashMap() {
         super();
         accessOrder = false;
     }
     //指定初始化时的容量，
     public LinkedHashMap(int initialCapacity) {
         super(initialCapacity);
         accessOrder = false;
     }
     //指定初始化时的容量，和扩容的加载因子
     public LinkedHashMap(int initialCapacity, float loadFactor) {
         super(initialCapacity, loadFactor);
         accessOrder = false;
     }
     //指定初始化时的容量，和扩容的加载因子，以及迭代输出节点的顺序
     public LinkedHashMap(int initialCapacity,
                          float loadFactor,
                          boolean accessOrder) {
         super(initialCapacity, loadFactor);
         this.accessOrder = accessOrder;
     }
     //利用另一个Map 来构建，
     public LinkedHashMap(Map<? extends K, ? extends V> m) {
         super();
         accessOrder = false;
         //批量插入一个map中的所有数据到本集合中。
         putMapEntries(m, false);
     }
 ```
 
 #### 3) put方法
 
 `LinkedHashMap`并没有重写任何put方法。但是其重写了构建新节点的`newNode()`方法. 
 `newNode()`会在`HashMap`的`putVal()`方法里被调用，`putVal()`方法会在批量插入数据`putMapEntries(Map<? extends K, ? extends V> m, boolean evict)`或者插入单个数据`public V put(K key, V value)`时被调用。
 
 ``` 
     //在构建新节点时，构建的是`LinkedHashMap.Entry` 不再是`Node`.
     Node<K,V> newNode(int hash, K key, V value, Node<K,V> e) {
         LinkedHashMap.Entry<K,V> p =
             new LinkedHashMap.Entry<K,V>(hash, key, value, e);
         linkNodeLast(p);
         return p;
     }
     //将新增的节点，连接在链表的尾部
     private void linkNodeLast(LinkedHashMap.Entry<K,V> p) {
         LinkedHashMap.Entry<K,V> last = tail;
         tail = p;
         //集合之前是空的
         if (last == null)
             head = p;
         else {//将新节点连接在链表的尾部
             p.before = last;
             last.after = p;
         }
     }
 ```
 
` void afterNodeInsertion(boolean evict)`以及`boolean removeEldestEntry(Map.Entry<K,V> eldest)
`是构建LRUCache需要的回调，在LinkedHashMap里可以忽略它们。
``` 
    //回调函数，新节点插入之后回调 ， 根据evict 和   判断是否需要删除最老插入的节点。如果实现LruCache会用到这个方法。
    void afterNodeInsertion(boolean evict) { // possibly remove eldest
        LinkedHashMap.Entry<K,V> first;
        //LinkedHashMap 默认返回false 则不删除节点
        if (evict && (first = head) != null && removeEldestEntry(first)) {
            K key = first.key;
            removeNode(hash(key), key, null, false, true);
        }
    }
    //LinkedHashMap 默认返回false 则不删除节点。 返回true 代表要删除最早的节点。通常构建一个LruCache会在达到Cache的上限是返回true
    protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
        return false;
    }
```

#### 4) remove
LinkedHashMap也没有重写remove()方法，因为它的删除逻辑和HashMap并无区别。 
但它重写了`afterNodeRemoval()`这个回调方法。该方法会在
`Node<K,V> removeNode(int hash, Object key, Object value,boolean matchValue, boolean movable)`方法中回调，removeNode()会在所有涉及到删除节点的方法中被调用，上文分析过，是删除节点操作的真正执行者。

``` 
    //在删除节点e时，同步将e从双向链表上删除
    void afterNodeRemoval(Node<K,V> e) { // unlink
        LinkedHashMap.Entry<K,V> p =
            (LinkedHashMap.Entry<K,V>)e, b = p.before, a = p.after;
        //待删除节点 p 的前置后置节点都置空
        p.before = p.after = null;
        //如果前置节点是null，则现在的头结点应该是后置节点a
        if (b == null)
            head = a;
        else//否则将前置节点b的后置节点指向a
            b.after = a;
        //同理如果后置节点时null ，则尾节点应是b
        if (a == null)
            tail = b;
        else//否则更新后置节点a的前置节点为b
            a.before = b;
    }
```

#### 5) get
LinkedHashMap重写了get()和getOrDefault()方法：
``` 
    public V get(Object key) {
        Node<K,V> e;
        if ((e = getNode(hash(key), key)) == null)
            return null;
        if (accessOrder)
            afterNodeAccess(e);
        return e.value;
    }
    public V getOrDefault(Object key, V defaultValue) {
       Node<K,V> e;
       if ((e = getNode(hash(key), key)) == null)
           return defaultValue;
       if (accessOrder)
           afterNodeAccess(e);
       return e.value;
   }
```

`在afterNodeAccess()`函数中，会将当前被访问到的节点e，移动至内部的双向链表的尾部:
``` 
 void afterNodeAccess(Node<K,V> e) { // move node to last
        LinkedHashMap.Entry<K,V> last;//原尾节点
        //如果accessOrder 是true ，且原尾节点不等于e
        if (accessOrder && (last = tail) != e) {
            //节点e强转成双向链表节点p
            LinkedHashMap.Entry<K,V> p =
                (LinkedHashMap.Entry<K,V>)e, b = p.before, a = p.after;
            //p现在是尾节点， 后置节点一定是null
            p.after = null;
            //如果p的前置节点是null，则p以前是头结点，所以更新现在的头结点是p的后置节点a
            if (b == null)
                head = a;
            else//否则更新p的前直接点b的后置节点为 a
                b.after = a;
            //如果p的后置节点不是null，则更新后置节点a的前置节点为b
            if (a != null)
                a.before = b;
            else//如果原本p的后置节点是null，则p就是尾节点。 此时 更新last的引用为 p的前置节点b
                last = b;
            if (last == null) //原本尾节点是null  则，链表中就一个节点
                head = p;
            else {//否则 更新 当前节点p的前置节点为 原尾节点last， last的后置节点是p
                p.before = last;
                last.after = p;
            }
            //尾节点的引用赋值成p
            tail = p;
            //修改modCount。
            ++modCount;
        }
    }
```

### 3.参考资料
- [LinkedHashMap源码分析](https://blog.csdn.net/zxt0601/article/details/77429150)