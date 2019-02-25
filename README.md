该仓库是我在工作中遇到的一些基础知识的积累， 包含算法与数据结构、设计模式、 经典编程范式、 工作中比较优秀的库的实现

### 目录结构


#### Collection源码分析

- [ArrayList](./docs/collection/ArrayList分析.md)

- [LinkedList](./docs/collection/LinkedList分析.md)

- [HashMap](./docs/collection/HashMap分析.md)

- [HashSet](./docs/collection/HashSet分析.md)

- [LinkedHashMap](./docs/collection/LinkedHashMap分析.md)

- [ConcurrentHashMap](./docs/collection/ConcurrentHashMap分析.md)


#### 排序算法

- [冒泡排序](https://github.com/haobinaa/DataStructure-DesignPattern/blob/master/src/main/java/com/haobin/datastructure/sort/BubbleSort.java)

- [桶排序](https://github.com/haobinaa/DataStructure-DesignPattern/blob/master/src/main/java/com/haobin/datastructure/sort/BucketSort.java)

- [堆排序](https://github.com/haobinaa/DataStructure-DesignPattern/blob/master/src/main/java/com/haobin/datastructure/sort/HeapSort.java)

- [插入排序](https://github.com/haobinaa/DataStructure-DesignPattern/blob/master/src/main/java/com/haobin/datastructure/sort/InsertSort.java)

- [归并排序](https://github.com/haobinaa/DataStructure-DesignPattern/blob/master/src/main/java/com/haobin/datastructure/sort/MergeSort.java)

- [快速排序](https://github.com/haobinaa/DataStructure-DesignPattern/blob/master/src/main/java/com/haobin/datastructure/sort/QuickSort.java)

- [选择排序](https://github.com/haobinaa/DataStructure-DesignPattern/blob/master/src/main/java/com/haobin/datastructure/sort/SelectSort.java)

- [希尔排序](https://github.com/haobinaa/DataStructure-DesignPattern/blob/master/src/main/java/com/haobin/datastructure/sort/ShellInsertSort.java)

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

- [设计原则与类关系](https://github.com/haobinaa/DataStructure-DesignPattern/blob/master/src/main/java/com/haobin/desinpattern/design-pattern.md)


设计模式分三种类型，共23种

- 创建型模式: 主要由两个主导思想构成。一是将系统使用的具体类封装起来，二是隐藏这些具体类的实例创建和结合的方式。
   - [单例模式](https://github.com/haobinaa/DataStructure-DesignPattern/blob/master/src/main/java/com/haobin/desinpattern/singleton/singleton.md)
   - 建造者模式
   - [工厂模式、简单工厂模式、抽象工厂模式](https://github.com/haobinaa/DataStructure-DesignPattern/blob/master/src/main/java/com/haobin/desinpattern/factory/factory.md)
   - 原型模式

 - 结构型模式：主要是用于处理类或者对象的组合，它描述了如何来类或者对象更好的组合起来，是从程序的结构上来解决模块之间的耦合问题。
   - [适配器模式](https://github.com/haobinaa/DataStructure-DesignPattern/blob/master/src/main/java/com/haobin/desinpattern/adapter/adapter.md)
   - 桥接模式
   - [装饰模式](https://github.com/haobinaa/DataStructure-DesignPattern/blob/master/src/main/java/com/haobin/desinpattern/decorator/decorator_pattern.md)
   - 组合模式
   - [外观模式](https://github.com/haobinaa/DataStructure-DesignPattern/blob/master/src/main/java/com/haobin/desinpattern/facade/facade.md)
   - 享元模式
   - 代理模式
   
- 行为型模式： 主要是用于描述类或者对象是怎样交互和怎样分配职责的
  - [模板方法模式](https://github.com/haobinaa/DataStructure-DesignPattern/blob/master/src/main/java/com/haobin/desinpattern/template_method/template_method_pattern.md)
  - 命令模式
  - 迭代器模式
  - [观察者模式](https://github.com/haobinaa/DataStructure-DesignPattern/blob/master/src/main/java/com/haobin/desinpattern/observer/observer_pattern.md)
  - 中介者模式
  - 备忘录模式
  - 解释器模式
  - 状态模式
  - [策略模式](https://github.com/haobinaa/DataStructure-DesignPattern/blob/master/src/main/java/com/haobin/desinpattern/strategy/strategy.md)
  - [责任链模式](https://github.com/haobinaa/DataStructure-DesignPattern/blob/master/src/main/java/com/haobin/desinpattern/chain_of_responsibility/chain_of_responsibility_pattern.md)
  - 访问者模式
  
  
  #### 代码片段
  收集常用的编程范式和代表性工具
  
  ##### 工作中经典实现收集
  
  - [基于数据库的消息队列(db-queue)](./db-queue/README.md)
  
  
  ##### 多线程经典范式
  
  - [死锁案例](https://github.com/haobinaa/DataStructure-DesignPattern/blob/master/src/main/java/com/haobin/codeBlock/DeadLock.java)
  
  - [线程间通信-交互打印奇偶数](./src/main/java/com/haobin/concurrent/PrintOddEvenNumber.java)
  
  
  ### 分布式
  - [拜占庭将军问题-Raft实现](./docs/Byzantine.md)