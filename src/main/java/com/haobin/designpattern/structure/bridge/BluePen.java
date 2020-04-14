package com.haobin.designpattern.structure.bridge;

/**
 * @Author HaoBin
 * @Create 2020/4/14 16:53
 * @Description:
 **/
public class BluePen implements DrawAPI {

    @Override
    public void draw(int radius, int x, int y) {
        System.out.println("绿色笔画图");
    }
}
