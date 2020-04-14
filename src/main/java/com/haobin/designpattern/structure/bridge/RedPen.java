package com.haobin.designpattern.structure.bridge;

/**
 * @Author HaoBin
 * @Create 2020/4/14 16:52
 * @Description:
 **/
public class RedPen implements DrawAPI {


    @Override
    public void draw(int radius, int x, int y) {
        System.out.println("红笔画图");
    }
}
