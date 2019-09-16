/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.designpattern.factory.simple_factory;

/**
 *
 *
 * @author HaoBin
 * @version $Id: Clothe.java, v0.1 2018/6/19 23:20 HaoBin 
 */
public abstract class Clothes {

    protected String name;

    public void colorClothes() {
        System.out.println("给衣服上色");
    }

    public void cutClothes() {
        System.out.println("裁剪衣服");;
    }
}