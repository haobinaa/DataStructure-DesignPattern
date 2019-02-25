/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.concurrent.aqsUtil;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * condition的使用 生产者->消费者模式[类ArrayBlockedQueue)
 *
 * @author HaoBin
 * @version $Id: BoundedBuffer.java, v0.1 2019/2/19 15:08 HaoBin
 */
public class BoundedBuffer {

    final Lock lock = new ReentrantLock();

    final Condition notFull = lock.newCondition();
    final Condition notEmpty = lock.newCondition();

    final Object[] itmes = new Object[100];
    int putptr, takeptr, count;

    public void put(Object x) throws InterruptedException {
        lock.lock();
        try {
            while (count == itmes.length) {
                notFull.await(); // 队列已满
            }
            itmes[putptr] = x;
            if (++putptr == itmes.length) {
                putptr = 0;
            }
            ++count;
            notEmpty.signal(); // 生产成功，队列不会为empty， 发送通知
        } finally {
            lock.unlock();
        }
    }

    public Object take() throws InterruptedException {
        lock.lock();
        try {
            while (count == 0) {
                notEmpty.await(); // 队列为空，等待生产
            }
            Object x = itmes[takeptr];
            if (++takeptr == itmes.length) {
                takeptr = 0;
            }
            --count;
            notFull.signal();
            return x;
        } finally {
            lock.unlock();
        }
    }
}