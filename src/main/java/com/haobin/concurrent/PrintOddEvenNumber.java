/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.concurrent;

/**
 * wait/notify机制
 * 2个多线程交替打印1-100内数字,其中t1线程打印奇数,t2程打印偶数\
 * 关键点： print方法的锁两个线程交替获取
 *
 * @author HaoBin
 * @version $Id: PrintOddEvenNumber.java, v0.1 2019/1/7 22:17 HaoBin 
 */
public class PrintOddEvenNumber {


    //定义打印的方法
    public synchronized void print(String str){
        notify();
        System.out.println(str);
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    //定义打印奇数的线程类
    class A implements Runnable{
        @Override
        public void run() {
            for(int i=1;i<100;i+=2){
                print("A"+i);
            }
        }
    }


    //定义打印偶数的线程类
    class B implements Runnable{
        @Override
        public void run() {
            for(int i=2;i<=100;i+=2){
                print("B"+i);
            }
        }
    }

    public static void main(String[] args) {
        PrintOddEvenNumber printOddEvenNumber = new PrintOddEvenNumber();
        A a = printOddEvenNumber.new A();
        B b = printOddEvenNumber.new B();
        new Thread(a).start();
        new Thread(b).start();
    }

}