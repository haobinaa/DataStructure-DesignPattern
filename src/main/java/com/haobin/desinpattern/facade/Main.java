/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.desinpattern.facade;

/**
 * @author HaoBin
 * @version $Id: Main.java, v0.1 2018/6/19 17:54 HaoBin
 */
public class Main {

    public static void main(String[] args) {
        Light light = new Light();
        TV tv = new TV();
        AirCondition ac = new AirCondition();

        Facade facade = new Facade(tv, light, ac);
        facade.on();
        facade.off();
    }

}