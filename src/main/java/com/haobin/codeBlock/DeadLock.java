/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.codeBlock;


/**
 * 两个线程持有一个条件，竞争另一个条件形成死锁
 *
 * @author HaoBin
 * @version $Id: DeadLock.java, v0.1 2018/10/31 13:48 HaoBin 
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