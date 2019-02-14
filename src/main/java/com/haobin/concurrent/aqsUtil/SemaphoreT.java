/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.concurrent.aqsUtil;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Semaphore;

/**
 * Semaphore也是一个线程同步的辅助类，可以维护当前访问自身的线程个数，并提供了同步机制。使用Semaphore可以控制同时访问资源的线程个数
 *
 * @author HaoBin
 * @version $Id: SemaphoreT.java, v0.1 2019/2/14 22:20 HaoBin 
 */
public class SemaphoreT {

    private int threadNum;
    private Semaphore semaphore;

    public SemaphoreT(int permits,int threadNum, boolean fair) {
        this.threadNum = threadNum;
        this.semaphore = new Semaphore(permits, fair);
    }
    private void println(String msg){
        SimpleDateFormat sdf = new SimpleDateFormat("[YYYY-MM-dd HH:mm:ss.SSS] ");
        System.out.println(sdf.format(new Date()) + msg);
    }

    public void test(){
        for(int i =  0; i < threadNum; i ++){
            new Thread(() -> {
                try {
                    semaphore.acquire();
                    println(Thread.currentThread().getName() + " acquire");
                    Thread.sleep(5000);//模拟业务耗时
                    println(Thread.currentThread().getName() + " release");
                    semaphore.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    public static void main(String[] args) {
        // 采用公平锁，按线程启动顺序竞争
        SemaphoreT semaphoreT = new SemaphoreT(3, 9, true);
        semaphoreT.test();
    }
}