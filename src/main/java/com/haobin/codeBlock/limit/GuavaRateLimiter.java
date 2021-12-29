package com.haobin.codeBlock.limit;

import com.google.common.util.concurrent.RateLimiter;

import java.util.concurrent.TimeUnit;

/**
 * @Date 2021/9/22 4:31 下午
 * @author: leobhao
 */
public class GuavaRateLimiter {

    public static void main(String[] args) throws InterruptedException {
        testBursty();
//        testWarmUp();
    }
    static void testBursty() throws InterruptedException {
        RateLimiter limiter = RateLimiter.create(2);
        acquire(limiter);
    }
    static void testWarmUp() throws InterruptedException {
        RateLimiter limiter = RateLimiter.create(2, 2, TimeUnit.SECONDS);
        acquire(limiter);
    }
    static void acquire(RateLimiter limiter) throws InterruptedException {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 5; i++) {
            limiter.acquire(1);
            long now = System.currentTimeMillis();
            System.out.println("acquire at " + (now - start) + " ms");
        }
        TimeUnit.SECONDS.sleep(2);
        for (int i = 0; i < 5; i++) {
            limiter.acquire(1);
            long now = System.currentTimeMillis();
            System.out.println("acquire at " + (now - start) + " ms");
        }
    }
}
