/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.desinpattern.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author HaoBin
 * @version $Id: ObjectFor3D.java, v0.1 2018/6/13 15:02 HaoBin
 */
public class ObjectFor3D implements Subject {

    private List<Observer> observers = new ArrayList<Observer>();

    private String msg;

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        int index = observers.indexOf(observer);
        if (index >= 0) {
            observers.remove(index);
        }
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(msg);
        }
    }

    /**
     * 更新状态，通知所有观察者
     * @param msg
     */
    public void setMsg(String msg) {
        this.msg = msg;
        this.notifyObservers();
    }
}