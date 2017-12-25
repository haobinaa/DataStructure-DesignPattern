package io.haobin.sort;

import io.haobin.util.PrintUtil;

/**
 * 希尔排序
 *
 * @Author: HaoBin
 * @Date 2017/12/25 18:57
 */
public class ShellInsertSort {
    // 插入法交换
    public static void shellSort(int[] table) {
        // 增量最开始等于length/2，并逐渐减少
        for(int gap = table.length/2; gap > 0; gap /= 2) {
            // 从gap个元素，逐个对其所在组进行直接插入排序操作
            for(int i = gap; i < table.length; i++) {
                int j = i;
                int temp = table[j];
                if(table[j] < table[j - gap]) {
                    while(j - gap >= 0 && temp < table[j-gap]) {
                        // 移动法
                        table[j] = table[j - gap];
                        j -= gap;
                    }
                    table[j] = temp;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] intArr = {100,908,1000001,100,0,9999};
        PrintUtil.printArray(intArr);
        shellSort(intArr);
        System.out.println();
        PrintUtil.printArray(intArr);
    }
}
