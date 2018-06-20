/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.desinpattern.factory.abstract_factory;

/**
 *
 *
 * @author HaoBin
 * @version $Id: Factory.java, v0.1 2018/6/20 17:25 HaoBin 
 */
public abstract class Factory {
    public abstract AbstractProduct ManufactureContainer();
    public abstract AbstractProduct ManufactureMould();
}