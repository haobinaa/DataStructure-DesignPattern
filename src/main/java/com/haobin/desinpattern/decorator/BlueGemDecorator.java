/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.desinpattern.decorator;

/**
 * 蓝宝石装饰品
 * 每颗攻击力+5
 * @author HaoBin
 * @version $Id: BlueGemDecorator.java, v0.1 2018/6/15 17:29 HaoBin
 */
public class BlueGemDecorator implements IEquipDecorator {

    /**
     * 每个装饰品维护一个装备
     */
    private IEquip equip;

    public BlueGemDecorator(IEquip equip) {
        this.equip = equip;
    }

    @Override
    public int caculateAttack() {
        return 5 + equip.caculateAttack();
    }

    @Override
    public String description() {
        return equip.description() + "+ 蓝宝石";
    }
}