/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.designpattern.singleton;

/**
 * @author HaoBin
 * @version $Id: EffectiveSingleTon.java, v0.1 2018/6/20 18:10 HaoBin
 */
public class EffectiveSingleTon {

    private static class SingletonHolder {

        private static final EffectiveSingleTon INSTANCE = new EffectiveSingleTon();
    }

    private EffectiveSingleTon() {
    }

    public static final EffectiveSingleTon getInstance() {
        return SingletonHolder.INSTANCE;
    }
}