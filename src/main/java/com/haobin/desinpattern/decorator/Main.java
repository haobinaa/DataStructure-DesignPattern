/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.desinpattern.decorator;

/**
 *
 *
 * @author HaoBin
 * @version $Id: Main.java, v0.1 2018/6/15 17:31 HaoBin 
 */
public class Main {

    public static void main(String[] args) {
        // 一个镶嵌2颗红宝石，1颗蓝宝石的靴子
        System.out.println(" 一个镶嵌2颗黄宝石，1颗蓝宝石的靴子");
        IEquip equip = new YellowGemDecorator(new YellowGemDecorator(new BlueGemDecorator(new ShoeEquip())));
        System.out.println("攻击力  : " + equip.caculateAttack());
        System.out.println("描述 :" + equip.description());
        System.out.println("-------");
        // 一个镶嵌1黄红宝石，1颗蓝宝石的武器
        System.out.println(" 一个镶嵌1颗蓝宝石,1颗黄宝石的戒指");
        equip = new YellowGemDecorator(new BlueGemDecorator(new RingEquip()));
        System.out.println("攻击力  : " + equip.caculateAttack());
        System.out.println("描述 :" + equip.description());
        System.out.println("-------");
    }
}