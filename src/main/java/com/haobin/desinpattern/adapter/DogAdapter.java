/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.desinpattern.adapter;

/**
 *
 *
 * @author HaoBin
 * @version $Id: DogAdapter.java, v0.1 2018/6/19 10:06 HaoBin 
 */
public class DogAdapter implements Robot {

    Dog dog;

    public DogAdapter(Dog dog) {
        this.dog = dog;
    }

    @Override
    public void cry() {
        System.out.println("模拟狗叫：");
        dog.wang();
    }

    @Override
    public void move() {
        System.out.println("模拟狗移动:");
        dog.run();
    }
}