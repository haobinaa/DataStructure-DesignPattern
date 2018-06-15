/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.desinpattern.template_method;

/**
 * @author HaoBin
 * @version $Id: QAWorker.java, v0.1 2018/6/15 15:22 HaoBin
 */
public class QAWorker extends Worker {

    public QAWorker(String name) {
        super(name);
    }

    @Override
    public void work() {
        System.out.println(name + "写测试用例-提交bug-写测试用例");
    }
}