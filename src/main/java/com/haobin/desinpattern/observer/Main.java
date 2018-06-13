/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.desinpattern.observer;

/**
 *
 *
 * @author HaoBin
 * @version $Id: Main.java, v0.1 2018/6/13 15:11 HaoBin 
 */
public class Main {

    public static void main(String[] args) {
        //模拟一个3D的服务号
        ObjectFor3D subjectFor3d = new ObjectFor3D();
        //客户1
        Observer observer1 = new Observer1(subjectFor3d);
        Observer observer2 = new Observer2(subjectFor3d);

        subjectFor3d.setMsg("20140420的3D号码是：127" );
        subjectFor3d.setMsg("20140421的3D号码是：333" );
    }

}