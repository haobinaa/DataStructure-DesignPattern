package io.haobin.sort;

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
            mergeSort(array, low, middle);
            mergeSort(array, middle + 1, high);

            merge(array, low, middle, high);
        }
    }

    public static void merge(int[] array, int low, int middle, int high){
        int[] helper = new int[array.length];
        for (int i = 0; i <= high; i++) {
            helper[i] = array[i];
        }

        int helperLeft = low;
        int helperRight = middle + 1;
        int current = low;

        /**
         * 迭代访问helper数组，比较左右两半元素
         * 并将较小的元素复制到原先的数组中
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
         * 将数组左半剩余元素复制到原先的数组中
         */
        int remaining = middle - helperLeft;
        for(int i = 0; i <= remaining; i++){
            array[current + i] = helper[helperLeft + i];
        }
    }
}
