package com.haobin.codeBlock.lock;

import redis.clients.jedis.Jedis;

/**
 * @Author HaoBin
 * @Create 2020/3/17 16:31
 * @Description: redis 分布式锁
 *
 *
 * 分布式锁需要满足: 1.互斥 2.不会发生死锁 3.加锁和解锁必须是同一个客户端 4.容错性(Redis集群部分节点宕机也不影响)
 **/
public class RedisDistributeLockWrong {


    /**
     * 加锁错误示范1
     * setnx 和 expire 两个指令不是原子的，导致死锁
     */
    public static void wrongGetLock1(Jedis jedis, String lockKey, String requestId, int expire) {
        long result = jedis.setnx(lockKey, requestId);
        if (result == 1) {
            // 这里如果程序突然崩溃，则导致死锁
            jedis.expire(lockKey, expire);
        }
    }

    /**
     * 加锁错误示范2
     * 1. 客户端自己生成的过期时间，需要所有服务器时钟同步
     * 2. 锁过期的时候，多个客户端同时执行可能被其他客户端获取到锁，不具备加锁必须是同一个客户端的特性
     */
    public static boolean wrongGetLock2(Jedis jedis, String lockKey, int expire) {
        long expires = System.currentTimeMillis() + expire;
        String expiresStr = String.valueOf(expires);
        // 如果当前锁不存在，返回加锁成功
        if (jedis.setnx(lockKey, expiresStr) == 1) {
            return true;
        }
        // 如果锁存在，获取锁的过期时间
        String currentValueStr = jedis.get(lockKey);
        if (currentValueStr != null && Long.parseLong(currentValueStr) < System.currentTimeMillis()) {
            // 锁已过期，获取上一个锁的过期时间，并设置现在锁的过期时间
            String oldValueStr = jedis.getSet(lockKey, expiresStr);
            if (oldValueStr != null && oldValueStr.equals(currentValueStr)) {
                // 考虑多线程并发的情况，只有一个线程的设置值和当前值相同，它才有权利加锁
                return true;
            }
        }
        // 其他情况，一律返回加锁失败
        return false;
    }

    /**
     * 解锁错误示范1
     * 这种肯定是错误的，都不判断是否拥有锁直接
     */
    public static void wrongReleaseLock1(Jedis jedis, String lockKey) {
        jedis.del(lockKey);
    }

    /**
     * 解锁错误释放2
     * 判断和删除不是原子性的操作，可能发生的情况
     * A解锁的操作，再执行del的时候突然过期了。这时候B加锁成功，A再执行del操作就会把B的锁给删了
     */
    public static void wrongReleaseLock2(Jedis jedis, String lockKey, String requestId) {
        // 判断加锁与解锁是不是同一个客户端
        if (requestId.equals(jedis.get(lockKey))) {
            // 若在此时，这把锁突然不是这个客户端的，则会误解锁
            jedis.del(lockKey);
        }
    }
}
