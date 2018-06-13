/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.desinpattern.strategy;

/**
 *
 *
 * @author HaoBin
 * @version $Id: DefendTBS.java, v0.1 2018/6/13 9:06 HaoBin 
 */
public class DefendTBS implements IDefendBehavior {

    @Override
    public void defend() {
        System.out.println("铁布衫");
    }
}