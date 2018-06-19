/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.desinpattern.adapter;

/**
 *
 *
 * @author HaoBin
 * @version $Id: Cat.java, v0.1 2018/6/19 9:43 HaoBin
 */
public class ImitateRobot implements Robot {

    @Override
    public void cry() {
        System.out.println("机器人叫，乌拉乌拉");
    }

    @Override
    public void move() {
        System.out.println("机器人移动，噜噜噜噜");
    }
}