package com.haobin.designpattern.build.singleton;

/**
 * @Author HaoBin
 * @Create 2020/4/14 14:46
 * @Description: 饿汉模式单例
 **/
public class HungrySingleton {

    private HungrySingleton() {};

    // 提前初始化好
    private static HungrySingleton instance = new HungrySingleton();

    public static HungrySingleton getInstance() {
        return instance;
    }
}
