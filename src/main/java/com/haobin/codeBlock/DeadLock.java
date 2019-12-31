/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.codeBlock;


/**
 * 两个线程持有一个条件，竞争另一个条件形成死锁
 *
 * @author HaoBin
 * @version $Id: DeadLock.java, v0.1 2018/10/31 13:48 HaoBin
 *
 * 打破死锁产生的四个必要条件之一(请求互斥，请求保持，不可剥夺，循环等待)死锁就不会发生
 *
 *
 * 当死锁发生了，如果要消除死锁，有如下几种方式:
 * 1. 最简单的就是重启，不过这种方式代价较大，会导致之前所有的状态都被清除
 * 2. 撤销进程，剥夺资源。终止参与死锁的进程，收回它们占用的资源。
 * 3. 进程回退，如果发生死锁则退到上一步
 */
public class DeadLock extends Thread{

    private String first;
    private String second;

    public DeadLock(String name, String first, String second) {
        super(name);
        this.first = first;
        this.second = second;
    }

    @Override
    public void run() {
        synchronized (first) {
            System.out.println(this.getName() + " obtained: " + first);
            try {
                Thread.sleep(1000L);
                synchronized (second){
                    System.out.println(this.getName() + " obtained: " + second);
                }
            }catch (InterruptedException e){

            }
        }
    }

    public static void main(String[] args) throws InterruptedException{
        String lockA = "lockA";
        String lockB = "lockB";
        DeadLock deadLockA = new DeadLock("thread1", lockA, lockB);
        DeadLock deadLockB = new DeadLock("thread2", lockB, lockA);
        deadLockA.start();
        deadLockB.start();
        deadLockA.join();
        deadLockB.join();
    }
}