/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.datastructure.sort;

import java.util.Date;

/**
 *
 *
 * @author HaoBin
 * @version $Id: Util.java, v0.1 2019/2/13 17:59 HaoBin 
 */
public class Util {
    /**
     * 交换数组元素位置
     */
    public static void swap(int[] data, int i, int j) {
        if (i == j) {
            return;
        }
        data[i] = data[i] + data[j];
        data[j] = data[i] - data[j];
        data[i] = data[i] - data[j];
    }

    // 打印数组
    public static void print(int[] data) {
        for (int i = 0; i < data.length; i++) {
            System.out.print(data[i] + "\t");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        System.out.println(new Date());
    }
}