/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.designpattern.strategy;

/**
 *
 *
 * @author HaoBin
 * @version $Id: RunJCTQ.java, v0.1 2018/6/13 9:07 HaoBin 
 */
public class RunJCTQ implements IRunBehavior {

    @Override
    public void run() {
        System.out.println("金蝉脱壳！");
    }
}