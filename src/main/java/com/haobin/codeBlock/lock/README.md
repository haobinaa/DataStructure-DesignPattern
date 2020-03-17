### redis 

- [redis实现分布式锁的错误示范](./RedisDistributeLockWrong.java)
- [基于Jedis的单机版分布式锁](./JedisDistributeLock.java)
- [集群下Redis分布式锁的实现](./ClusterRedisDistributeLock.java)


#### redLock

集群环境下的分布式锁(其实还可以用 Redisson, 原生就支持)。 多个Master节点情况下, redLock 获取锁思想:
1. 得到本地时间
2. client 使用相同的 key 和随机数，按照顺序再每个 Master 实例中尝试获取锁， 再获取锁的过程中为每个锁设置一个超时时间
3. 客户端计算初与master获得锁操作过程中消耗的时间，当且仅当Client获取锁消耗时间小于锁存活时间，并且再一半master节点中成功获取锁，才认为client获取锁成功
4. 如果获取到了锁，client执行任务的时间窗口是锁的存活时间减去获取锁消耗的时间
5. 如果client获取锁数量不足一半以上，就再所有master释放锁