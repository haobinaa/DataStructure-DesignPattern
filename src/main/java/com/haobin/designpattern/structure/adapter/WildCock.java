package com.haobin.designpattern.structure.adapter;

/**
 * @Author HaoBin
 * @Create 2020/4/14 15:57
 * @Description: 野鸡
 **/
public class WildCock implements Cock {

    @Override
    public void gobble() {
        System.out.println("野鸡咕咕叫");
    }

    @Override
    public void fly() {
        System.out.println("野鸡飞");
    }
}
