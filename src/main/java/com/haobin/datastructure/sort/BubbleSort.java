package com.haobin.datastructure.sort;

import com.haobin.datastructure.utils.PrintUtil;

/**
 * 冒泡排序
 *
 * @Author: HaoBin
 * @Date 2017/12/14 18:18
 */
public class BubbleSort {
    public static void bubbleSort(int table[]) {
        for (int i = 0; i < table.length; i++) {
            for(int j = 0; j < table.length-i-1; j++) {
                // 如果i位置比j位置大，则i往前移一个位置
                if(table[j] > table[j+1]) {
                    int temp = table[j];
                    table[j] = table[j+1];
                    table[j+1] = temp;
                }
            }
            // 一趟比较完之后，i位置就像最大的一个泡泡一样冒到了尾部
        }
    }
    public static void main(String[] args) {
        int[] arr =  {8,9,8,9,8,9,8,9};
        PrintUtil.printArray(arr);
        bubbleSort(arr);
    }
}
