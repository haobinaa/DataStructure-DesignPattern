### 概述

 `HashMap` 是一个无序的 Map，因为每次根据 key 的 hashcode 映射到 Entry 数组上，所以遍历出来的顺序并不是写入的顺序。
 
 `LinkedHashMap`继承于`HashMap`，具有HashMap的快速查找特性
 ```
 public class LinkedHashMap<K,V> extends HashMap<K,V> implements Map<K,V>
 ```
 内部维护一个双向链表，用来维护插入顺序或LRU顺序
 ``` 
 /**
  * The head (eldest) of the doubly linked list.
  */
 transient LinkedHashMap.Entry<K,V> head;
 
 /**
  * The tail (youngest) of the doubly linked list.
  */
 transient LinkedHashMap.Entry<K,V> tail;
 ```
accessOrder 决定了顺序，默认为 false，此时维护的是插入顺序。
``` 
final boolean accessOrder;
```
 
 ### 结构
  LinkedHashMap 最重要的是以下用于维护顺序的函数，它们会在 put、get 等方法中调用。
  ``` 
  void afterNodeAccess(Node<K,V> p) { }
  void afterNodeInsertion(boolean evict) { }
  ```
 ####  afterNodeAccess()
 
 当一个节点被访问时，如果 accessOrder 为 true，则会将该节点移到链表尾部。也就是说指定为 LRU 顺序之后，在每次访问一个节点时，会将这个节点移到链表尾部，保证链表尾部是最近访问的节点，那么链表首部就是最近最久未使用的节点。

``` 
 void afterNodeAccess(Node<K,V> e) {
        //原尾节点
        LinkedHashMap.Entry<K,V> last;
        //如果accessOrder 是true ，且原尾节点不等于e, 这里就将尾节点赋值给了last
        if (accessOrder && (last = tail) != e) {
            //节点e强转成双向链表节点p
            LinkedHashMap.Entry<K,V> p =(LinkedHashMap.Entry<K,V>)e, 
            b = p.before, a = p.after;
            // p的尾节点赋null
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

#### afterNodeInsertion()

在 put 等操作之后执行，当 removeEldestEntry() 方法返回 true 时会移除最晚的节点，也就是链表首部节点 first。

evict 只有在构建 Map 的时候才为 false，在这里为 true。
``` 
void afterNodeInsertion(boolean evict) { // possibly remove eldest
    LinkedHashMap.Entry<K,V> first;
    if (evict && (first = head) != null && removeEldestEntry(first)) {
        K key = first.key;
        removeNode(hash(key), key, null, false, true);
    }
}
```
removeEldestEntry() 默认为 false，如果需要让它为 true，需要继承 LinkedHashMap 并且覆盖这个方法的实现，这在实现 LRU 的缓存中特别有用，通过移除最近最久未使用的节点，从而保证缓存空间足够，并且缓存的数据都是热点数据。
```
protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
    return false;
}
```






