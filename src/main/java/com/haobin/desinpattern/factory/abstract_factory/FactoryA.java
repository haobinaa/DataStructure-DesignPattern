/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.desinpattern.factory.abstract_factory;

/**
 *
 *
 * @author HaoBin
 * @version $Id: FactoryA.java, v0.1 2018/6/20 17:31 HaoBin 
 */
public class FactoryA extends Factory {

    @Override
    public AbstractProduct ManufactureContainer() {
        return new ContainerProductA();
    }

    @Override
    public AbstractProduct ManufactureMould() {
        return new MouldProductA();
    }
}