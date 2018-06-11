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