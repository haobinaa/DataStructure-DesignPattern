package io.haobin.sort;

import io.haobin.util.PrintUtil;

/** 选择排序
 *
 * @Author: HaoBin
 * @Date 2017/12/14 18:20
 */
public class SelectSort {
    public static void selectSort(int[] table) {
        for (int i = 0; i < table.length; i++) {
            // 当前位置为最小值
            int min = i;
            for(int j = i + 1; j < table.length; j++) {
                // 如果小于当前最小值， 则min记录当前最小值
                if(table[j] < table[min]) {
                    min = j;
                }
                // 如果min不是当前刻度， 则把最小的那个放到当前位置，
                if(min != i) {
                    int temp = table[i];
                    table[i] = table[j];
                    table[j] = temp;
                }
                // 一趟完毕能找出此躺的最小值，下一次从刻度+1的位置开始找
            }

        }
        System.out.println();
        PrintUtil.printArray(table);
    }

    public static void main(String[] args) {
        int[] intArr = {1,3,4,52,123,31};
        PrintUtil.printArray(intArr);
        selectSort(intArr);
    }
}
