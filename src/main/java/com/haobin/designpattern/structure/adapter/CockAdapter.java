package com.haobin.designpattern.structure.adapter;

/**
 * @Author HaoBin
 * @Create 2020/4/14 16:02
 * @Description: 鸡来适配鸭的咕咕叫
 **/
public class CockAdapter implements Duck {

    Cock cock;

    public CockAdapter(Cock cock) {
        this.cock = cock;
    }

    @Override
    public void quack() {
        // 内部其实是一个鸡的咕咕叫
        cock.gobble();
    }

    @Override
    public void fly() {
        cock.fly();
    }


    public static void main(String[] args) {
        Cock wildCock = new WildCock();
        // 适配成一只鸭
        Duck duck = new CockAdapter(wildCock);
    }
}
