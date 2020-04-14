package com.haobin.designpattern.build.singleton;

/**
 * @Author HaoBin
 * @Create 2020/4/14 15:16
 * @Description: 内嵌式单例
 **/
public class NestedSingleton {

    private NestedSingleton() {}

    private static class Holder {
        private static NestedSingleton instance = new NestedSingleton();
    }

    public static NestedSingleton getInstance() {
        // 用一个嵌套类，延迟初始化
        return Holder.instance;
    }
}
