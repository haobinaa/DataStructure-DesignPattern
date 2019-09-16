/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.designpattern.template_method;

/**
 * @author HaoBin
 * @version $Id: HRWorker.java, v0.1 2018/6/15 15:22 HaoBin
 */
public class HRWorker extends Worker {

    public HRWorker(String name) {
        super(name);
    }

    @Override
    public void work() {
        System.out.println(name + "看简历-打电话-接电话");
    }
}