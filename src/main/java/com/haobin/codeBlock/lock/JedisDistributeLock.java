package com.haobin.codeBlock.lock;

import java.util.Collections;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

/**
 * @Author HaoBin
 * @Create 2020/3/17 17:37
 * @Description: jedis分布式锁
 **/
public class JedisDistributeLock {

    private Logger logger = LoggerFactory.getLogger(JedisDistributeLock.class);

    private static final String LOCK_SUCCESS = "OK";
    private static final Long RELEASE_SUCCESS = 1L;
    private static final String SET_IF_NOT_EXIST = "NX";
    private static final String SET_WITH_EXPIRE_TIME = "PX";

    /**
     * redis 客户端
     */
    private Jedis jedis;

    /**
     * 分布式锁的键值
     */
    private String lockKey;

    /**
     * 锁的超时时间 10s
     */
    int expireTime = 10 * 1000;

    /**
     * 锁等待，防止线程饥饿
     */
    int acquireTimeout = 1 * 1000;

    /**
     * 获取指定键值的分布式锁
     */
    public JedisDistributeLock(Jedis jedis, String lockKey) {
        this.jedis = jedis;
        this.lockKey = lockKey;
    }

    /**
     * 获取锁，同时设置超时时间和过期时间
     */
    public JedisDistributeLock(Jedis jedis, String lockKey, int expireTime, int acquireTimeout) {
        this.jedis = jedis;
        this.lockKey = lockKey;
        this.expireTime = expireTime;
        this.acquireTimeout = acquireTimeout;
    }

    public String acquire() {
        try {
            // 获取锁的超时时间，超过这个时间则放弃获取锁
            long end = System.currentTimeMillis() + acquireTimeout;
            // 随机生成一个value
            String requireToken = UUID.randomUUID().toString();
            while (System.currentTimeMillis() < end) {
                String result = jedis.set(lockKey, requireToken, SET_IF_NOT_EXIST, SET_WITH_EXPIRE_TIME, expireTime);
                if (LOCK_SUCCESS.equals(result)) {
                    return requireToken;
                }
                try {
                    // 空等一下再去获取锁
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            }
        } catch (Exception e) {
            logger.error("acquire lock due to error", e);
        }

        return null;
    }

    public boolean release(String identify) {
        if (identify == null) {
            return false;
        }
        // lua 脚本，来保证判断和del的原子性
        String script = "if redis.call('get', KEYS[1]) == ARGV[1] then return redis.call('del', KEYS[1]) else return 0 end";
        Object result = new Object();
        try {
            result = jedis.eval(script, Collections.singletonList(lockKey),
                Collections.singletonList(identify));
            if (RELEASE_SUCCESS.equals(result)) {
                logger.info("release lock success, requestToken:{}", identify);
                return true;
            }
        } catch (Exception e) {
            logger.error("release lock due to error", e);
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        logger.info("release lock failed, requestToken:{}, result:{}", identify, result);
        return false;
    }
}
