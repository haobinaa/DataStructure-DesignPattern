package com.haobin.datastructure.sort;

import com.haobin.datastructure.util.PrintUtil;

/** 选择排序
 *
 * @Author: HaoBin
 * @Date 2017/12/14 18:20
 */
public class SelectSort {
    public static void selectSort(int[] table) {
        int temp;
        for (int i = 0; i < table.length; i++) {
            for(int j = i + 1; j < table.length; j++) {
                if(table[j] < table[i]) {
                    temp = table[j];
                    table[j] = table[i];
                    table[i] = temp;
                }
            }

        }
    }

    public static void main(String[] args) {
        int[] intArr = {100,908,1000001,100,0,9999};
        PrintUtil.printArray(intArr);
        selectSort(intArr);
    }
}
