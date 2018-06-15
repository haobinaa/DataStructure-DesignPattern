/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.desinpattern.chain_of_responsibility;

/**
 *
 *
 * @author HaoBin
 * @version $Id: Leader.java, v0.1 2018/6/15 9:34 HaoBin 
 */
public abstract class Leader {
    /** 姓名 **/
    public String name;

    /** 后继者 **/
    protected Leader successor;

    public Leader(String name){
        this.name = name;
    }

    public void setSuccessor(Leader successor) {
        this.successor = successor;
    }

    public abstract void handleRequest(LeaveNode LeaveNode);
}