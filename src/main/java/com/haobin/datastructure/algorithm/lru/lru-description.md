### LRU介绍

LRU 是 `Least Recently Used `的简写，字面意思则是最近最少使用。

通常用于缓存的淘汰策略实现，由于缓存的内存非常宝贵，所以需要根据某种规则来剔除数据保证内存不被撑满。

### 实现一

#### 1） 目标：
- 实现一个 LRU 缓存，当缓存数据达到 N 之后需要淘汰掉最近最少使用的数据。
- N 小时之内没有被访问的数据也需要淘汰掉。

#### 2） 思路：
- 保存数据采用`HashMap`
- 内部数据采用一个队列来保存
- 写入的时候判断缓存是否大于了阈值 N，如果满足则根据队列的 FIFO 特性将队列头的数据删除。因为队列头的数据肯定是最先放进去的
- 再开启了一个守护线程用于判断最先放进去的数据是否超期（因为就算超期也是最先放进去的数据最有可能满足超期条件。）
- 设置为守护线程可以更好的表明其目的（最坏的情况下，如果是一个用户线程最终有可能导致程序不能正常退出，因为该线程一直在运行，守护线程则不会有这个情况。）

#### 3） 实现代码

[LRU实现一](https://github.com/haobinaa/DataStructure-DesignPattern/blob/master/src/main/java/com/haobin/datastructure/algorithm/lru/LRUAbstractMap.java)

### 参考资料
- [实现LRU-Cache](https://crossoverjie.top/2018/04/07/algorithm/LRU-cache/)