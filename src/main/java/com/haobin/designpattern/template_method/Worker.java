/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.designpattern.template_method;

import java.time.LocalDateTime;

/**
 * @author HaoBin
 * @version $Id: Worker.java, v0.1 2018/6/15 15:14 HaoBin
 */
public abstract class Worker {

    protected String name;

    public Worker(String name) {
        this.name = name;
    }

    public final void workOneDay() {
        System.out.println("-----------------work start ---------------");
        enterCompany();
        computerOn();
        work();
        computerOff();
        exitCompany();
        System.out.println("-----------------work end ---------------");
    }

    /**
     * 工作
     */
    public abstract void work();

    /**
     * 关闭电脑
     */
    private void computerOff() {
        System.out.println(name + "关闭电脑");
    }

    /**
     * 打开电脑
     */
    private void computerOn() {
        System.out.println(name + "打开电脑");
    }

    /**
     * 进入公司
     */
    public void enterCompany() {
        System.out.println(name + "进入公司");
    }

    /**
     * 离开公司
     */
    public void exitCompany() {
        if (isNeedPrintDate()) {
            System.out.print(LocalDateTime.now() + "-->");
        }
        System.out.println(name + "离开公司");
    }

    /**
     * 打印时间hook
     * @return
     */
    public boolean isNeedPrintDate() {
        return false;
    }
}