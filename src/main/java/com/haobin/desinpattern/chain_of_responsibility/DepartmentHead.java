/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.desinpattern.chain_of_responsibility;

/**
 * @author HaoBin
 * @version $Id: DepartmentHead.java, v0.1 2018/6/15 9:38 HaoBin
 */
public class DepartmentHead extends Leader {

    public DepartmentHead(String name) {
        super(name);
    }

    @Override
    public void handleRequest(LeaveNode leaveNode) {
        if (leaveNode.getNumber() <= 7) {
            System.out.println(
                    "系主任" + name + "审批" + leaveNode.getPerson() + "同学的请假条,请假天数为" + leaveNode.getNumber() + "天。");
        } else {
            if (this.successor != null) {
                this.successor.handleRequest(leaveNode);
            }
        }
    }
}