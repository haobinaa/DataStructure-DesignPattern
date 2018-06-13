/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.desinpattern.observer;

/**
 *
 *
 * @author HaoBin
 * @version $Id: Subject.java, v0.1 2018/6/13 14:58 HaoBin 
 */
public interface Subject {

    public void registerObserver(Observer observer);

    public void removeObserver(Observer observer);

    public void notifyObservers();
}