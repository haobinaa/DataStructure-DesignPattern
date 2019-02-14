/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.concurrent.aqsUtil;

import java.util.concurrent.CountDownLatch;

/**
 *
 *
 * @author HaoBin
 * @version $Id: CountDownLatch.java, v0.1 2019/2/14 20:23 HaoBin 
 */
public class CountDownLatchT {

    public static class Worker implements Runnable {

        final CountDownLatch startSignal;
        final CountDownLatch doneSignal;

        public Worker(CountDownLatch startSignal, CountDownLatch doneSignal) {
            this.startSignal = startSignal;
            this.doneSignal = doneSignal;
        }

        @Override
        public void run() {
            try {
                // 为了让所有线程同时开始任务，我们让所有线程先阻塞在这里
                // 等大家都准备好了，再打开这个门栓
                startSignal.await();
                doWork();
                doneSignal.countDown();
            } catch (InterruptedException ex) {
            } // return;
        }

        void doWork() {
            System.out.println("work");
        }
    }

    public static void main(String[] args) throws Exception {
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(5);

        for (int i = 0; i < 5; ++i)
            new Thread(new Worker(startSignal, doneSignal)).start();

        // 这边插入一些代码，确保上面的每个线程先启动起来，才执行下面的代码。
        doSomethingElse();
        // 先打开第一个闸门，n=1只需要调用一次
        startSignal.countDown();
        doSomethingElse();
        // 等待所有任务结束
        doneSignal.await();
        doSomethingElse();
    }

    public static void doSomethingElse() {
        System.out.println("some thing");
    }
}