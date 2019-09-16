/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.designpattern.factory.factory_method;

import com.haobin.designpattern.factory.simple_factory.Clothes;

/**
 * @author HaoBin
 * @version $Id: ClothesFactory.java, v0.1 2018/6/19 23:29 HaoBin
 */
public abstract class ClothesFactory {

    public Clothes sellClothes(String type) {
        Clothes clothes = null;
        clothes = makeClothes(type);
        clothes.colorClothes();
        clothes.cutClothes();
        return clothes;
    }

    abstract Clothes makeClothes(String type);

}