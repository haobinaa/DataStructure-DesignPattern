package com.haobin.datastructure.sort;


/**
 * 冒泡排序
 *
 * @Author: HaoBin
 * @Date 2017/12/14 18:18
 */
public class BubbleSort {

    public static void bubbleSort(int table[]) {
        for (int i = 0; i < table.length; i++) {
            boolean flag = false;
            for(int j = 0; j < table.length-i-1; j++) {
                // 如果i位置比j位置大，则i往前移一个位置
                if(table[j] > table[j+1]) {
                    int temp = table[j];
                    table[j] = table[j+1];
                    table[j+1] = temp;
                    // 有数据交换
                    flag = true;
                }
            }
            // 一趟比较完之后，i位置就像最大的一个泡泡一样冒到了尾部
            if (!flag) {
                // 如果没有数据交换，表示此数据已经有序，退出循环
                break;
            }
        }
    }

    public static void main(String[] args) {
        int[] intArr = {100, 908, 1000001, 100, 0, 9999};
        bubbleSort(intArr);
        Util.print(intArr);
    }
}
