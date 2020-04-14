package com.haobin.designpattern.build.singleton;

/**
 * @Author HaoBin
 * @Create 2020/4/14 15:02
 * @Description: 饱汉模式-双重锁检测
 **/
public class DCLSingleton {

    private DCLSingleton() {}

    // volatile 修饰，禁止指令重排序
    private static volatile DCLSingleton instance = null;

    public static DCLSingleton getInstance() {
        if (instance == null) {
            synchronized (DCLSingleton.class) {
                // 再次判断，避免并发问题
                if (instance == null) {
                    instance = new DCLSingleton();
                }
            }
        }
        return instance;
    }
}
