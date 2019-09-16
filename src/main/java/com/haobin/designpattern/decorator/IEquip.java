/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.designpattern.decorator;

/**
 *
 *
 * @author HaoBin
 * @version $Id: IEquip.java, v0.1 2018/6/15 17:20 HaoBin 
 */
public interface IEquip {
    /**
     * 计算攻击力
     *
     * @return
     */
    public int caculateAttack();

    /**
     * 装备的描述
     *
     * @return
     */
    public String description();
}
