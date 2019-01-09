/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.concurrent.synchronize;

/**
 * 对象锁
 *
 * @author HaoBin
 * @version $Id: Service.java, v0.1 2018/6/21 11:48 HaoBin
 */
public class Service {

    String anyString = new String();

    public Service(String anyString) {
        this.anyString = anyString;
    }

    public void setUsernamePassword(String username, String password) {
        try {
            synchronized (anyString) {
                System.out.println("线程名称为：" + Thread.currentThread().getName()
                        + "在" + System.currentTimeMillis() + "进入同步块");
                Thread.sleep(3000);
                System.out.println("线程名称为：" + Thread.currentThread().getName()
                        + "在" + System.currentTimeMillis() + "离开同步块");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}

class Thread1 extends Thread {

    private Service service;

    public Thread1(Service service) {
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.setUsernamePassword("a", "aa");

    }

}

class Thread2 extends Thread {

    private Service service;

    public Thread2(Service service) {
        super();
        this.service = service;
    }

    @Override
    public void run() {
        service.setUsernamePassword("b", "bb");

    }

}

class ServiceMain {

    public static void main(String[] args) {
        Service service = new Service("service string");

        Thread1 a = new Thread1(service);
        a.setName("A");
        a.start();

        Thread2 b = new Thread2(service);
        b.setName("B");
        b.start();
    }
}