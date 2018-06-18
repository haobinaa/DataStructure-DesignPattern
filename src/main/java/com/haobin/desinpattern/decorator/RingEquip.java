/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.desinpattern.decorator;

/**
 * 戒指
 * 攻击力5
 * @author HaoBin
 * @version $Id: RingEquip.java, v0.1 2018/6/15 17:24 HaoBin 
 */
public class RingEquip implements IEquip {

    @Override
    public int caculateAttack() {
        return 5;
    }

    @Override
    public String description() {
        return "圣战戒指";
    }
}