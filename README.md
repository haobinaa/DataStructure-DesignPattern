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

- [栈|队列](./docs/datastructure/StackAndQueue.md)
- [排序算法总结：冒泡|选择|插入|希尔|快速|归并|堆|桶](./docs/datastructure/Sort.md)
- [查找算法:二分查找|跳表](./docs/datastructure/Search.md)
- [散列](./docs/datastructure/HashTable.md)
- [树|二叉查找树](./docs/datastructure/Tree.md)
- [二叉树对应的实现](src/main/java/com/haobin/datastructure/tree/BinarySearchTree.java)
- [平衡二叉树对应的实现](src/main/java/com/haobin/datastructure/tree/AVLTree.java)
- [B-Tree](./docs/datastructure/B-Tree.md)
- [B+Tree](./docs/datastructure/B+Tree.md)
- [红黑树](./docs/datastructure/RedBlackTree.md)
- [一致性hash算法](docs/consistent_hash.md)
- [LRU](docs/lru-description.md)
- [快慢指针](./docs/datastructure/SlowFastPoint.md)
- [(回溯)八皇后](src/main/java/com/haobin/algorithm/EightQueen.java)
- [(动态规划)01背包问题 | 已选择物品](src/main/java/com/haobin/algorithm/Backpack.java)
- [(动态规划)多重背包问题](src/main/java/com/haobin/algorithm/ManyBackpack.java)
- [(动态规划)完全背包问题](src/main/java/com/haobin/algorithm/CompleteBackpack.java)


#### 剑指offer
- [删除链表倒数第N个节点](src/main/java/com/haobin/offer/DeleteLastNNode.java)
- [3-数组中重复的数字](src/main/java/com/haobin/offer/DuplicateNumInArray.java)
- [4-判断数是否在二维数组中](src/main/java/com/haobin/offer/NumInDimensionalArray.java)
- [5-替换空格](src/main/java/com/haobin/offer/ReplaceSpace.java)
- [6-从尾到头打印链表(反转链表)](src/main/java/com/haobin/offer/PrintListFromTailToHead.java)
- [7-根据中序和前序结果构建二叉树](src/main/java/com/haobin/offer/RebuildBinaryTree.java)
- [8-找到中序遍历的下一个节点](src/main/java/com/haobin/offer/BinaryTreeNextNode.java)
- [9-用两个栈来实现队列](src/main/java/com/haobin/offer/TwoStackPlaceQueue.java)
- [(递推)10-1-斐波那契数列](src/main/java/com/haobin/offer/Fibonacci.java)
- [(递推)10-2-矩形覆盖](src/main/java/com/haobin/offer/RectCover.java)
- [(递推)10-3-跳台阶(附递推类问题理解)](src/main/java/com/haobin/offer/JumpFloor.java)
- [(动态规划)10-4-跳台阶进阶](src/main/java/com/haobin/offer/JumpFloorII.java)
- [11-旋转数组中最小的数字](src/main/java/com/haobin/offer/MinNumInRotateArray.java)
- [(回溯)12-矩阵中的路径](src/main/java/com/haobin/offer/HasPathInRect.java)
- [(深度优先DFS)13-机器人的运动范围](src/main/java/com/haobin/offer/RobotMove.java)
- [(动态规划)14-整数拆分(剪绳子)](src/main/java/com/haobin/offer/IntegerBreaker.java)
- [(二进制)15-二进制中1的个数](src/main/java/com/haobin/offer/NumberOfOne.java)
- [(数值运算)16-数值的整数次方](src/main/java/com/haobin/offer/Power.java)
- [17-打印n位的最大十进制数](src/main/java/com/haobin/offer/PrintOneToMaxOfN.java)
- [18-O(1)复杂度删除链表节点](src/main/java/com/haobin/offer/PrintOneToMaxOfN.java)
- [19-删除排序链表重复节点](src/main/java/com/haobin/offer/PrintOneToMaxOfN.java)
- [20-正则表达式匹配](src/main/java/com/haobin/offer/RegexMatch.java)

#### leetcode

- 数组
    - [两数之和](src/main/java/com/haobin/leetcode/arrays/TwoSum.java)
    - [三数之和](src/main/java/com/haobin/leetcode/arrays/ThreeSum.java)
    - [两数相加](src/main/java/com/haobin/leetcode/arrays/TwoAdd.java)
    - [最接近三数之和](src/main/java/com/haobin/leetcode/arrays/ThreeSumClosest.java)
    - [四数之和](src/main/java/com/haobin/leetcode/arrays/FourSum.java)
    - [排序数组去重](src/main/java/com/haobin/leetcode/arrays/RemoveDuplicates.java)
    - [两个有序数组的中位数](src/main/java/com/haobin/leetcode/arrays/FindMedianSortedArray.java)
    - [数组最大连续子序列](src/main/java/com/haobin/leetcode/arrays/MaxSubArray.java)
    - [只出现一次的数字(可用位运算方式)](src/main/java/com/haobin/leetcode/arrays/MaxSubArray.java)
    - [寻找最短无序子数组](src/main/java/com/haobin/leetcode/arrays/FindUnsortedSubArray.java)
    - [移动0](src/main/java/com/haobin/leetcode/arrays/MoveZeroes.java)
    - [消失的数字](src/main/java/com/haobin/leetcode/arrays/FindDisappearedNumbers.java)
- 字符串
    - [最长无重复子串](src/main/java/com/haobin/leetcode/string/LongestSubString.java)
- 链表
    - [反转链表](src/main/java/com/haobin/leetcode/linkedlist/ReverseList.java)
    - [链表是否有环](src/main/java/com/haobin/leetcode/linkedlist/HasCycle.java)
    - [查找链表的交点](src/main/java/com/haobin/leetcode/linkedlist/GetInterSectionNode.java)
    - [判断是否是回文链表](src/main/java/com/haobin/leetcode/linkedlist/PalindromeLinkedList.java)
- 栈
    - [有效的括号](/src/main/java/com/haobin/leetcode/stack/ValidBrackets.java)
    -[最小栈](/src/main/java/com/haobin/leetcode/stack/MinStack.java)
- 动态规划
    - [买卖股票的最佳时机](src/main/java/com/haobin/leetcode/dp/MaxProfit.java)
    - [打家劫舍](src/main/java/com/haobin/leetcode/dp/Rob.java)

#### 设计模式

- [设计模式汇总](./docs/design-pattern/design-pattern.md)
  
  
#### 经典范式

 - [死锁案例](/src/main/java/com/haobin/codeBlock/DeadLock.java)
 - [线程间通信-交互打印奇偶数](/src/main/java/com/haobin/codeBlock/PrintOddEvenNumber.java)
 、
 - [缓存击穿解决方法](/src/main/java/com/haobin/codeBlock/CacheBreakdown.java)
 - [传统IO模型](/src/main/java/com/haobin/codeBlock/IOModel/ClassicServerLoop.java) 
 - [IO-reactor模式(单线程)](/src/main/java/com/haobin/codeBlock/IOModel/SingleReactor.java)
  - [IO-reactor模式(多线程)](/src/main/java/com/haobin/codeBlock/IOModel/MultiReactor.java)

#### 处理大数据量思路
  - [统计大文件单词出现的频率](/src/main/java/com/haobin/bigdata/wordcount/WordCount.java)


### Mysql专题

- [mysql存储引擎](./docs/mysql/engin.md)
- [字符集](./docs/mysql/charactor-compare.md)
- [innodb记录的结构和innodb数据页存储](./docs/mysql/innodb-record-struct.md)
- [索引与 B+ Tree](./docs/mysql/index.md)
- [单表和连表查询过程](./docs/mysql/join-table-query.md)
- [explain 执行计划](./docs/mysql/explain.md)
- [表空间和buffer pool](./docs/mysql/buffer_pool.md)
- [redo/undo 日志](docs/mysql/redo.md)
- [事务隔离与MVCC](./docs/mysql/transcation-mvcc.md)
- [锁](./docs/mysql)
- [mysql优化实践](./docs/mysql/mysql-practice-optimize.md)