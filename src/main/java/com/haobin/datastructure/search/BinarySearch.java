package com.haobin.datastructure.search;

/**
 * 二分查找:在一个有序数组中查找某个值
 * @Author: HaoBin
 * @Date 2018/1/26 17:37
 */
public class BinarySearch {
    /**
     * 循环实现二分查找
     */
    public static Integer searchCirculation(int[] array, int value) {
        int low = 0;
        int hight = array.length - 1;
        int middle = 0;
        // 此处是小于等于，如果是小于则会有一个元素判断不到
        while (low <= hight) {
            middle = (low + hight) / 2;
            if(value < array[middle]) {
                hight = middle - 1;
            }else if (value > array[middle]) {
                low = middle + 1;
            }else {
                return middle;
            }

        }
        return 0;
    }

    /**
     * 递归实现二分查找
     */
    public static Integer searchRecursive(int[] array, int value){
        return searchRecursive(array, value, 0, array.length - 1);
    }

    private static Integer searchRecursive(int[] array, int value, int low, int high){
        // 递归退出条件
        if(high < low){
            return -1;
        }

        int middle = (low + high) / 2;

        if(value < array[middle]){
            return searchRecursive(array, value, low, middle - 1);
        }else if(value > array[middle]){
            return searchRecursive(array, value, middle + 1, high);
        }else {
            return middle;
        }
    }
}
