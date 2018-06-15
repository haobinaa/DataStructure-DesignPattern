/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.desinpattern.template_method;

/**
 * @author HaoBin
 * @version $Id: ITWorker.java, v0.1 2018/6/15 15:21 HaoBin
 */
public class ITWorker extends Worker {

    public ITWorker(String name) {
        super(name);
    }

    @Override
    public void work() {
        System.out.println(name + "写程序-测bug-fix bug");
    }
}