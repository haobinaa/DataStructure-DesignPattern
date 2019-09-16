/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.designpattern.singleton;

/**
 * @author HaoBin
 * @version $Id: SingleTon.java, v0.1 2018/6/20 17:51 HaoBin
 */
public class SingleTon {

    public volatile static SingleTon instance;

    private SingleTon() {
    }

    public static synchronized SingleTon getInstance() {
        if (instance == null) {                         //Single Checked
            synchronized (SingleTon.class) {
                if (instance == null) {                 //Double Checked
                    instance = new SingleTon();
                }
            }
        }
        return instance ;
    }
}