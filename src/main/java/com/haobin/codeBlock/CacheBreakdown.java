package com.haobin.codeBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: HaoBin
 * @create: 2019/10/8 17:51
 * @description: 缓存击穿
 * <p>
 * 在高并发下,多线程同时查询同一个资源,如果缓存中没有这个资源(比如刚好失效),
 * 那么这些线程都会去数据库查找,对数据库造成极大压力,缓存失去存在的意义
 * <p>
 * 应对方案:
 * <p>
 * 1. 后台定期刷新：在后台使用一个定时任务主动更新缓存，在缓存失效前，将数据库中的数据更新到缓存
 * 这种方案如果缓存的Key比较分散，则实现起来较为复杂
 * 2. 加锁方案，也是最常用的方案， 实现方式对比
 **/
public class CacheBreakdown {

    static Object lock;
    static Lock reenLock = new ReentrantLock();

    // 从缓存中读数据
    private List<String> getDataFromCache() {
        return null;
    }

    // 从数据库中查询
    private List<String> getDataFromDB() {
        return null;
    }

    // 将查询到的数据写入数据库
    private void setDataToCache(List<String> result) {
    }


    /**
     * 方案1
     * 这种方式虽然能够防止缓存失效的时候，高并发打到数据库
     * 但是即使在缓存没有失效的时候，也需要排队获取锁
     * 会极大的降低系统的吞吐率
     */
    public synchronized List<String> getData01() {
        List<String> result = new ArrayList<String>();
        result = getDataFromCache();
        if (result.isEmpty()) {
            // 从数据库查询数据
            result = getDataFromDB();
            // 将查询到的数据写入缓存
            setDataToCache(result);
        }
        return result;
    }


    /**
     * 方案2
     * 这种方案在命中缓存的时候，系统吞吐量不受影响
     * 但是缓存失效的时候还是会打到数据库，只是从原来的高并发变成了阻塞
     * 不但阻塞了请求，还是会给数据库带来额外的压力
     */
    public List<String> getData02() {
        List<String> result = new ArrayList<String>();
        result = getDataFromCache();
        if (result.isEmpty()) {
            synchronized (lock) {
                result = getDataFromDB();
                setDataToCache(result);
            }
        }
        return result;
    }


    /**
     * 方案3
     * 这种方案能阻止请求打到数据库
     * 但是在第二个及以后的请求命中缓存的时候，还是会排队等待
     * 会让排队的这部分用户阻塞住，体验不好
     */
    public List<String> getData03() {
        List<String> result = new ArrayList<String>();
        result = getDataFromCache();
        if (result.isEmpty()) {
            synchronized (lock) {
                //双重判断,第二个以及之后的请求不必去找数据库,直接命中缓存
                result = getDataFromCache();
                if (result.isEmpty()) {
                    result = getDataFromDB();
                    setDataToCache(result);
                }
            }
        }
        return result;
    }


    /**
     * 方案4
     * 互斥锁方案，有效的避免了前几种方式产生的问题
     */
    public List<String> getData04() throws InterruptedException {
        List<String> result = new ArrayList<String>();
        result = getDataFromCache();
        if (result.isEmpty()) {
            if (reenLock.tryLock()) {
                try {
                    System.out.println("我拿到锁了,从DB获取数据库后写入缓存");
                    result = getDataFromDB();
                    setDataToCache(result);
                } finally {
                    reenLock.unlock();// 释放锁
                }
            } else {
                result = getDataFromCache();// 先查一下缓存
                if (result.isEmpty()) {
                    System.out.println("我没拿到锁,缓存也没数据,先小憩一下");
                    Thread.sleep(100);// 小憩一会儿
                    return getData04();// 重试
                }
            }
        }
        return result;
    }


}
