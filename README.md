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



#### 查找算法

- [二分查找](./src/main/java/com/haobin/datastructure/search/BinarySearch.java)


#### 算法

- [斐波那契数列](src/main/java/com/haobin/algorithm/Fibonacci.java)

- [八皇后](src/main/java/com/haobin/algorithm/EightQueen.java)

- [背包问题](src/main/java/com/haobin/algorithm/Backpack.java)

- [一致性hash算法](docs/consistent_hash.md)

- [LRU](docs/lru-description.md)

- [B-Tree](./docs/datastructure/B-Tree.md)

- [B+Tree](./docs/datastructure/B+Tree.md)

- [反转链表](src/main/java/com/haobin/datastructure/ReverseList.java)

- [布隆过滤(判断一个元素是否存在一个庞大的集合中)]
##### 字符串问题

- [KMP](src/main/java/com/haobin/algorithm/str/KMP.java)
- [最长公共前缀](src/main/java/com/haobin/algorithm/str/CommonPrefix.java)
- [回文(最长回文串、验证回文串、最长回文子串、最长回文子序列)](src/main/java/com/haobin/algorithm/str/Palindrome.java)



#### 设计模式

- [设计模式汇总](./docs/design-pattern/design-pattern.md)
  
  
  #### 代码片段
  收集常用的编程范式和代表性工具
  
  ##### 工作中经典实现收集
  
  - [基于数据库的消息队列(db-queue)](./db-queue/README.md)
  
  
  ##### 多线程经典范式
  
  - [死锁案例](https://github.com/haobinaa/DataStructure-DesignPattern/blob/master/src/main/java/com/haobin/codeBlock/DeadLock.java)
  
  - [线程间通信-交互打印奇偶数](./src/main/java/com/haobin/concurrent/PrintOddEvenNumber.java)
  
  
  ### 分布式
  - [拜占庭将军问题-Raft实现](./docs/Byzantine.md)