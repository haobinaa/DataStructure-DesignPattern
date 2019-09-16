/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.designpattern.observer;

/**
 * @author HaoBin
 * @version $Id: Observer2.java, v0.1 2018/6/13 15:10 HaoBin
 */
public class Observer2 implements Observer {

    private Subject subject;

    public Observer2(Subject subject) {
        this.subject = subject;
        subject.registerObserver(this);
    }

    @Override
    public void update(String msg) {
        System.out.println("observer2 得到 3D 号码 -->" + msg + "我要告诉舍友们。");
    }
}