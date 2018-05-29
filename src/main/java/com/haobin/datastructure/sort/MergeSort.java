package com.haobin.datastructure.sort;

import com.haobin.datastructure.utils.PrintUtil;

/**
 * 归并排序
 *
 * @Author: HaoBin
 * @Date 2017/12/22 00:05
 */
public class MergeSort {

    public static void mergeSort(int[] array, int low, int high) {
        if(low < high) {
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
     * 归并数组
     * 对[low,......middle]  [middle+1,..... high]两部分进行归并
     *
     * 将目标数组的所有元素拷贝到临时数组helper中，记下左右位置
     * 迭代访问helper，将左右两半中较小的元素复制到目标数组
     * 最后将余下所有的元素复制回目标数组
     */
    public static void merge(int[] array, int low, int middle, int high){
        // 填充辅助数组
        int[] helper = new int[array.length];
        for (int i = 0; i <= high; i++) {
            helper[i] = array[i];
        }

        // 将原数组分割
        int helperLeft = low;
        int helperRight = middle + 1;
        int current = low;

        /**
         * 迭代访问helper数组，比较左右两半元素
         * 并将较小的元素复制到目标数组中
         */
        while(helperLeft <= middle && helperRight <= high){
            if(helper[helperLeft] <= helper[helperRight]){
                array[current] = helper[helperLeft];
                helperLeft++;
            } else{
                array[current] = helper[helperRight];
                helperRight++;
            }
            current++;
        }

        /**
         * 将数组左半剩余元素复制到目标数组中
         * 因为归并的两边本来就是有序的
         * 如果左边的有剩余，代表左边剩余所有大于现有数组
         * 如果左边全部赋值完毕，右边有剩余，那么他们本来位置就是对的
         */
        int remaining = middle - helperLeft;
        for(int i = 0; i <= remaining; i++){
            array[current + i] = helper[helperLeft + i];
        }
    }

    public static void main(String[] args) {
        int[] intArr = {100,908,1000001,100,0,999,12319};
        PrintUtil.printArray(intArr);
        mergeSort(intArr, 0, intArr.length-1);
        System.out.println();
        PrintUtil.printArray(intArr);
    }
}
