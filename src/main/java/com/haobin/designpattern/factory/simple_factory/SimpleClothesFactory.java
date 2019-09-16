/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.designpattern.factory.simple_factory;

/**
 * @author HaoBin
 * @version $Id: SimpleClothesFactory.java, v0.1 2018/6/19 23:30 HaoBin
 */
public class SimpleClothesFactory {

    public static Clothes makeClothes(String type) {
        Clothes clothes = null;
        if (type.equals("牛仔裤")) {
            clothes = new NZClothes();
        } else if (type.equals("棉毛裤")) {
            clothes = new MMCLothes();
        }
        return clothes;
    }
}