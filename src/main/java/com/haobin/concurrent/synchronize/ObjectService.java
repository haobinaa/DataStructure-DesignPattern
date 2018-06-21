/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.concurrent.synchronize;

/**
 * @author HaoBin
 * @version $Id: ObjectService.java, v0.1 2018/6/21 10:40 HaoBin
 */
public class ObjectService {

    public void serviceMethod() {
        try {
            synchronized (this) {
                System.out.println(Thread.currentThread().getName() + " begin time=" + System.currentTimeMillis());
                Thread.sleep(1000);
                System.out.println(Thread.currentThread().getName() + " end    end=" + System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class ThreadAA extends Thread {

    private ObjectService service;

    public ThreadAA(ObjectService service) {
        super();
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.serviceMethod();
    }
}

class ThreadBB extends Thread {

    private ObjectService service;

    public ThreadBB(ObjectService service) {
        super();
        this.service = service;
    }

    @Override
    public void run() {
        super.run();
        service.serviceMethod();
    }
}

class ObjectServiceMain {

    public static void main(String[] args) {
        ObjectService service = new ObjectService();
        ThreadAA a = new ThreadAA(service);
        a.setName("a");
        a.start();
        ThreadBB b = new ThreadBB(service);
        b.setName("b");
        b.start();
    }
}