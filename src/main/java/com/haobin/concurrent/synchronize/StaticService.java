/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.concurrent.synchronize;

/**
 *
 *
 * @author HaoBin
 * @version $Id: StaticService.java, v0.1 2018/6/21 17:33 HaoBin 
 */
public class StaticService {

    synchronized public static void printA() {
        try {
            System.out.println("线程名称为：" + Thread.currentThread().getName()
                    + "在" + System.currentTimeMillis() + "进入printA");
            Thread.sleep(3000);
            System.out.println("线程名称为：" + Thread.currentThread().getName()
                    + "在" + System.currentTimeMillis() + "离开printA");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    synchronized public static void printB() {
        System.out.println("线程名称为：" + Thread.currentThread().getName() + "在"
                + System.currentTimeMillis() + "进入printB");
        System.out.println("线程名称为：" + Thread.currentThread().getName() + "在"
                + System.currentTimeMillis() + "离开printB");
    }
}

class ThreadA1 extends Thread {

    @Override
    public void run() {
        super.run();
        StaticService.printA();
    }
}

class ThreadB1 extends Thread {

    @Override
    public void run() {
        super.run();
        StaticService.printB();
    }
}

class StaticMain {

    public static void main(String[] args) {
        ThreadA1 a = new ThreadA1();
        a.setName("A");
        a.start();

        ThreadB1 b = new ThreadB1();
        b.setName("B");
        b.start();
    }
}
