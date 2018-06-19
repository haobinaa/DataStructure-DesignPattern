/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.desinpattern.adapter;

/**
 *
 *
 * @author HaoBin
 * @version $Id: BirdAdapter.java, v0.1 2018/6/19 10:44 HaoBin 
 */
public class BirdAdapter implements Robot {

    Bird bird;

    public BirdAdapter(Bird bird) {
        this.bird = bird;
    }

    @Override
    public void cry() {
        System.out.println("模拟鸟叫：");
        bird.jiujiu();
    }

    @Override
    public void move() {
        System.out.println("模拟鸟移动：");
        bird.fly();
    }
}