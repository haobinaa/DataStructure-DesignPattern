/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.desinpattern.decorator;

/**
 * 黄宝石饰品
 * 攻击力+5
 * @author HaoBin
 * @version $Id: YellowGemDecorator.java, v0.1 2018/6/15 17:30 HaoBin
 */
public class YellowGemDecorator implements IEquipDecorator {

    private IEquip equip;

    public YellowGemDecorator(IEquip equip) {
        this.equip = equip;
    }

    @Override
    public int caculateAttack() {
        return 5 + equip.caculateAttack();
    }

    @Override
    public String description() {
        return equip.description() + "+ 黄宝石";
    }
}