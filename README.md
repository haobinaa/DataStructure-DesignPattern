该仓库是我在工作中遇到的一些基础知识的积累， 包含算法与数据结构、设计模式、 经典编程范式、 工作中比较优秀的库的实现

### 目录结构


#### Java Collection源码分析

- [ArrayList](./docs/collection/ArrayList分析.md)

- [LinkedList](./docs/collection/LinkedList分析.md)

- [HashMap](./docs/collection/HashMap分析.md)

- [HashSet](./docs/collection/HashSet分析.md)

- [LinkedHashMap](./docs/collection/LinkedHashMap分析.md)

- [ConcurrentHashMap](./docs/collection/ConcurrentHashMap分析.md)

#### 常用的数据结构和算法思想

- [栈和队列](./docs/datastructure/StackAndQueue.md)

- [递归](docs/datastructure/Recursion.md)

- [排序算法总结：冒泡|选择|插入|希尔|快速|归并|堆|桶](./docs/datastructure/Sort.md)

- [查找算法:二分查找|跳表](./docs/datastructure/Search.md)

- [散列](./docs/datastructure/HashTable.md)

- [树|二叉查找树](./docs/datastructure/Tree.md)

- [B-Tree](./docs/datastructure/B-Tree.md)

- [B+Tree](./docs/datastructure/B+Tree.md)

- [八皇后](src/main/java/com/haobin/algorithm/EightQueen.java)

- [背包问题](src/main/java/com/haobin/algorithm/Backpack.java)

- [一致性hash算法](docs/consistent_hash.md)

- [LRU](docs/lru-description.md)


#### 剑指offer

- [3-数组中重复的数字](src/main/java/com/haobin/offer/DuplicateNumInArray.java)
- [4-判断数是否在二维数组中](src/main/java/com/haobin/offer/NumInDimensionalArray.java)
- [5-替换空格](src/main/java/com/haobin/offer/ReplaceSpace.java)
- [6-从尾到头打印链表(反转链表)](src/main/java/com/haobin/offer/PrintListFromTailToHead.java)
- [7-根据中序和前序结果构建二叉树](src/main/java/com/haobin/offer/RebuildBinaryTree.java)
- [8-找到中序遍历的下一个节点](src/main/java/com/haobin/offer/BinaryTreeNextNode.java)
- [9-用两个栈来实现队列](src/main/java/com/haobin/offer/TwoStackPlaceQueue.java)
- [9-用两个栈来实现队列](src/main/java/com/haobin/offer/TwoStackPlaceQueue.java)
- [(递推)10-1-斐波那契数列](src/main/java/com/haobin/offer/Fibonacci.java)
- [(递推)10-2-矩形覆盖](src/main/java/com/haobin/offer/RectCover.java)
- [(递推)10-3-跳台阶(附递推类问题理解)](src/main/java/com/haobin/offer/JumpFloor.java)
- [(动态规划)10-4-跳台阶进阶](src/main/java/com/haobin/offer/JumpFloorII.java)
- [11-旋转数组中最小的数字](src/main/java/com/haobin/offer/MinNumInRotateArray.java)


#### 设计模式

- [设计模式汇总](./docs/design-pattern/design-pattern.md)
  
  
#### 多线程经典范式
  
  - [死锁案例](/src/main/java/com/haobin/codeBlock/DeadLock.java)
  
  - [线程间通信-交互打印奇偶数](/src/main/java/com/haobin/codeBlock/PrintOddEvenNumber.java)
  
  
#### elastic-job

- [选主过程](./docs/syn/elastic-startup.md)
- [作业分片流程](./docs/syn/elastic-shard.md)
- [失效转移](./dcos/syn/ealstic-failover.md)
- [错过补偿机制](./docs/syn/elastic-misfire.md)
  
### Mysql专题

- [mysql存储引擎](./docs/mysql/engin.md)
- [字符集](./docs/mysql/charactor-compare.md)
- [innodb记录的结构和innodb数据页存储](./docs/mysql/innodb-record-struct.md)
- [索引与 B+ Tree](./docs/mysql/index.md)
- [单表和连表查询过程](./docs/mysql/join-table-query.md)
- [explain 执行计划](./docs/mysql/explain.md)
- [表空间和buffer pool](./docs/mysql/buffer_pool.md)
- [redo 日志](docs/mysql/redo.md)
- [redo 日志](docs/mysql/undo.md)
- [事务隔离与MVCC](./docs/mysql/transcation-mvcc.md)
- [锁](./docs/mysql)