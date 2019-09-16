/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.designpattern.chain_of_responsibility;

/**
 *
 *
 * @author HaoBin
 * @version $Id: LeaveNode.java, v0.1 2018/6/15 9:34 HaoBin 
 */
public class LeaveNode {
    /** 请假天数 **/
    private  int number;

    /** 请假人 **/
    private String person;

    public LeaveNode(int number, String person) {
        this.number = number;
        this.person = person;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }
}