/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.desinpattern.factory;

/**
 *
 *
 * @author HaoBin
 * @version $Id: ClothesMaterialFactory.java, v0.1 2018/6/20 11:37 HaoBin 
 */
public interface ClothesMaterialFactory {
    public String createColor();
    public String createMaterial();
}