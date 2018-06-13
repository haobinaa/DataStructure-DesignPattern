/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.desinpattern.strategy;

/**
 *
 *
 * @author HaoBin
 * @version $Id: DisplayA.java, v0.1 2018/6/13 9:29 HaoBin 
 */
public class DisplayA implements IDisplayBehavior {

    @Override
    public void display() {
        System.out.println("样式一");
    }
}