package com.haobin.datastructure.sort;

/**
 * 快速排序
 *
 * @Author: HaoBin
 * @Date 2017/12/21 17:51
 */
public class QuickSort {


    public static void quickSort(int[] arr, int left, int right) {
        int i, j, temp;
        if (left > right) {
            return;
        }
        // 基准数
        temp = arr[left];
        i = left;
        j = right;
        while (i != j) {
            // 从右往左找小于基准数的
            while (arr[j] >= temp && i < j)
                j--;
            // 从左往右找大于基准数的
            while (arr[i] <= temp && i < j)
                i++;
            if (i < j) {
                // 交换两数在数组的位置
                Util.swap(arr, i, j);
            }
            // 将基数归位
            arr[left] = arr[i];
            arr[i] = temp;

            // 继续处理基数左边
            quickSort(arr, left, i);
            // 继续处理基数右边
            quickSort(arr, i+1, right);
        }
    }

    public static void main(String[] args) {
        int[] intArr = {100, 908, 1000001, 100, 0, 9999};
        quickSort(intArr, 0, intArr.length-1);
        Util.print(intArr);
    }

}
