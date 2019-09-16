/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.designpattern.chain_of_responsibility;

/**
 * @author HaoBin
 * @version $Id: Dean.java, v0.1 2018/6/15 9:39 HaoBin
 */
public class Dean extends Leader {

    public Dean(String name) {
        super(name);
    }

    public void handleRequest(LeaveNode leaveNode) {
        if (leaveNode.getNumber() <= 10) {
            System.out.println(
                    "院长" + name + "审批" + leaveNode.getPerson() + "同学的请假条,请假天数为" + leaveNode.getNumber() + "天。");
        } else {
            if (this.successor != null) {
                this.successor.handleRequest(leaveNode);
            }
        }
    }
}