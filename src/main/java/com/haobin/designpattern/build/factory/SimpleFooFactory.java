package com.haobin.designpattern.build.factory;

/**
 * @Author HaoBin
 * @Create 2020/4/14 10:33
 * @Description: 创建型-简单工厂模式
 **/
public class SimpleFooFactory {


    public static Food makeFood(String name) {
        if (name.equals("rice")) {
            Food rice = new Rice();
            return rice;
        } else if (name.equals("noodle")) {
            Noodle noodle = new Noodle();
            return noodle;
        }
        return null;
    }
}
