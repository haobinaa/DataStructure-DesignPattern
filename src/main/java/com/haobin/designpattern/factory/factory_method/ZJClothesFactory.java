/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.designpattern.factory.factory_method;

import com.haobin.designpattern.factory.simple_factory.Clothes;

/**
 *
 *
 * @author HaoBin
 * @version $Id: ZJClothesFactory.java, v0.1 2018/6/20 11:29 HaoBin 
 */
public class ZJClothesFactory extends ClothesFactory {

    @Override
    Clothes makeClothes(String type) {
        Clothes clothes = null;
        if (type.equals("棉毛裤")) {
            clothes = new ZJMMClothes();
        }
        return clothes;
    }
}