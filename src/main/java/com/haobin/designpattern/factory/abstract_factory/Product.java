/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.designpattern.factory.abstract_factory;

/**
 * @author HaoBin
 * @version $Id: Product.java, v0.1 2018/6/20 17:28 HaoBin
 */
class ContainerProductA extends ContainerProduct {

    @Override
    public void Show() {
        System.out.println("生产容器产品A");
    }
}

class ContainerProductB extends ContainerProduct {

    @Override
    public void Show() {
        System.out.println("生产容器产品B");
    }
}

class MouldProductA extends MouldProduct {

    @Override
    public void Show() {
        System.out.println("生产模具产品A");
    }
}

class MouldProductB extends MouldProduct {

    @Override
    public void Show() {
        System.out.println("生产模具产品B");
    }
}
