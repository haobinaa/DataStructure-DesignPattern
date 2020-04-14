package com.haobin.datastructure.sort;

import com.sun.org.apache.bcel.internal.generic.SWAP;

/**
 * 快速排序
 *
 * @Author: HaoBin
 * @Date 2017/12/21 17:51
 */
public class QuickSort {

    public static void quick_sort(int[] arr, int left, int right) {
        if (left >= right) {
            return;
        }
        int pivot = partition(arr, left, right);
        quick_sort(arr, left, pivot-1);
        quick_sort(arr, pivot+1, right);
    }

    public static int partition(int[] arr, int left, int right) {
        // 将基准点首先选为未处理的最后一个元素
        int pivot = arr[right];
        int i = left;
        for (int j = left; j <= right; j++) {
            if (arr[j] < pivot) {
                Util.swap(arr, i, j);
                i++;
            }
        }
        Util.swap(arr, i, right);
        return i;
    }

    public static void main(String[] args) {
        int[] intArr = {100, 908, 1000001, 100, 0, 9999};
        quick_sort(intArr, 0, intArr.length-1);
        Util.print(intArr);
    }

}
