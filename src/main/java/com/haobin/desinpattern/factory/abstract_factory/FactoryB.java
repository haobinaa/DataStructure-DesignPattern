/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.desinpattern.factory.abstract_factory;

/**
 *
 *
 * @author HaoBin
 * @version $Id: FactoryB.java, v0.1 2018/6/20 17:32 HaoBin 
 */
public class FactoryB extends Factory {

    @Override
    public AbstractProduct ManufactureContainer() {
        return new ContainerProductB();
    }

    @Override
    public AbstractProduct ManufactureMould() {
        return new MouldProductB();
    }
}