/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.desinpattern.factory;

/**
 *
 *
 * @author HaoBin
 * @version $Id: ZJMaterialFactory.java, v0.1 2018/6/20 11:39 HaoBin 
 */
public class ZJMaterialFactory implements ClothesMaterialFactory {

    @Override
    public String createColor() {
        return "浙江生产的颜料";
    }

    @Override
    public String createMaterial() {
        return "浙江生产的材料";
    }
}