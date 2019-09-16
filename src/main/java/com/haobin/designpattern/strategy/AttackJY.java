/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.designpattern.strategy;

/**
 *
 *
 * @author HaoBin
 * @version $Id: AttackJY.java, v0.1 2018/6/13 9:06 HaoBin 
 */
public class AttackJY implements IAttackBeHavior {

    @Override
    public void attack() {
        System.out.println("九阳神功！");
    }
}