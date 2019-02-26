package com.haobin.datastructure.sort;


/**
 * 归并排序
 *
 * @Author: HaoBin
 * @Date 2017/12/22 00:05
 */
public class MergeSort {

    public static void mergeSort(int[] array, int low, int high) {
        if (low < high) {
            int middle = (low + high) / 2;
            // 归并左边
            mergeSort(array, low, middle);
            // 归并右边
            mergeSort(array, middle + 1, high);
            // 合并数组
            merge(array, low, middle, high);
        }
    }

    /**
     * 归并数组 对[low,......middle]  [middle+1,..... high]两部分进行归并
     *
     * 将目标数组的所有元素拷贝到临时数组helper中，记下左右位置 迭代访问helper，将左右两半中较小的元素复制到目标数组 最后将余下所有的元素复制回目标数组
     */
    public static void merge(int[] array, int low, int middle, int high) {
        // 申请一片空间拷贝原数组
        int[] tmp = new int[array.length];
        for (int i = 0; i <= high; i++) {
            tmp[i] = array[i];
        }
        // 记录左右游标位置
        int tmpLeft = low;
        int tmpRight = middle + 1;
        // 归并好的数组下标
        int current = low;
        // 比较左右两边数据，将小的放入并后移游标
        while (tmpLeft <= middle && tmpRight <= high) {
            if (tmp[tmpLeft] <= tmp[tmpRight]) {
                array[current] = tmp[tmpLeft];
                tmpLeft++;
            } else {
                array[current] = tmp[tmpRight];
                tmpRight++;
            }
            current++;
        }
        /**
         * 将数组左半剩余元素复制到目标数组中
         * 如果是右边有剩余，就不必操作(本身已经拷贝到之前数组了)
         */
        int remaining = middle - tmpLeft;
        for (int i = 0; i <= remaining; i++) {
            array[current + i] = tmp[tmpLeft + i];
        }
    }

    public static void main(String[] args) {
        int[] intArr = {100, 908, 1000001, 100, 0, 9999};
        mergeSort(intArr, 0, intArr.length-1);
        Util.print(intArr);
    }

}
