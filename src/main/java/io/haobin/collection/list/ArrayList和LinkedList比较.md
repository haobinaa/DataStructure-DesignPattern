### 添加
LinkedList的DoubleLinkedList远远高于ArrayList：

- ArrayList扩容的时候消耗很大，一个是扩容，一个是要把之前的值复制给新的数组
- DoubleLinkedList添加的时候，头节点是直接指向最后一个节点的时候，效率非常高

### 删除

- ArrayList循环删除，从后往前遍历进行删除，越删前面的数据，性能就会越低，删除靠后的数据效率就会高
- 对于LinkedList
    - 如果是单链表实现，因为是从前往后遍历的，所以删前面的效率就比较高
    - 如果是双向循环链表的实现，删前后的效率都很高，但是越靠近中间，删除的效率越低，需要遍历的次数就越多
    
### 获取和设置

ArrayList的效率要高的多，ArrayList是直接访问下标

LinkedList需要循环遍历


### 综合
LinkedList添加和删除综合效率高，ArrayList修改和获取效率高

所以我们平时开发中一般是用ArrayList，如果在不同业务场景，适当选取不同的集合