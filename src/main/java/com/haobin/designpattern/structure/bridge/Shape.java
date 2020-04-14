package com.haobin.designpattern.structure.bridge;

/**
 * @Author HaoBin
 * @Create 2020/4/14 17:52
 * @Description:
 **/
public abstract class Shape {
    protected DrawAPI drawAPI;


    protected Shape(DrawAPI drawAPI) {
        this.drawAPI = drawAPI;
    }




}
