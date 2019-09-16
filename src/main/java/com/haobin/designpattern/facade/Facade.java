/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.designpattern.facade;

/**
 *
 *
 * @author HaoBin
 * @version $Id: Facade.java, v0.1 2018/6/19 17:53 HaoBin 
 */
public class Facade {
    TV tv;
    Light light;
    AirCondition airCondition;

    public Facade(TV tv, Light light, AirCondition airCondition) {
        this.tv = tv;
        this.light = light;
        this.airCondition = airCondition;
    }

    public void on() {
        this.tv.on();
        this.light.on();
        this.airCondition.on();
    }

    public void off() {
        this.tv.off();
        this.light.off();
        this.airCondition.off();
    }
}