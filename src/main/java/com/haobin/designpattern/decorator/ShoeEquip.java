/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.designpattern.decorator;

/**
 * @author HaoBin
 * @version $Id: ShoeEquip.java, v0.1 2018/6/15 17:25 HaoBin
 */
public class ShoeEquip implements IEquip {

    @Override
    public int caculateAttack() {
        return 5;
    }

    @Override
    public String description() {
        return "圣战靴子";
    }
}