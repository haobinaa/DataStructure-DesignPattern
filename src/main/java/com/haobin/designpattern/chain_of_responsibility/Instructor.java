/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.designpattern.chain_of_responsibility;

/**
 * @author HaoBin
 * @version $Id: Instructor.java, v0.1 2018/6/15 9:35 HaoBin
 */
public class Instructor extends Leader {

    public Instructor(String name) {
        super(name);
    }

    @Override
    public void handleRequest(LeaveNode leaveNode) {
        if (leaveNode.getNumber() < 3) {
            System.out.println("辅导员" + name + "审批" +leaveNode.getPerson() + "同学的请假条,请假天数为" + leaveNode.getNumber() + "天。");
        } else {
            this.successor.handleRequest(leaveNode);
        }
    }
}