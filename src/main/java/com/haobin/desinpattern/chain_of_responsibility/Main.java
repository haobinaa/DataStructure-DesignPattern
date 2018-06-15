/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.desinpattern.chain_of_responsibility;

/**
 *
 *
 * @author HaoBin
 * @version $Id: Main.java, v0.1 2018/6/15 9:40 HaoBin 
 */
public class Main {

    public static void main(String[] args) {
        Leader instructor = new Instructor("毕玄");
        Leader department = new DepartmentHead("多隆");
        Leader dean = new Dean("风清扬");
        // 设置处理链顺序
        instructor.setSuccessor(department);
        department.setSuccessor(dean);

        LeaveNode leaveNode1 = new LeaveNode(3, "张三");
        instructor.handleRequest(leaveNode1);

        LeaveNode leaveNode2 = new LeaveNode(5, "李四");
        instructor.handleRequest(leaveNode2);

        LeaveNode leaveNode3 = new LeaveNode(9, "王五");
        instructor.handleRequest(leaveNode3);
    }
}