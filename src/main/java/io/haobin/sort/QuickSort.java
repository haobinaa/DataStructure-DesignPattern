package io.haobin.sort;

import io.haobin.util.PrintUtil;

/**
 * 快速排序
 *
 * @Author: HaoBin
 * @Date 2017/12/21 17:51
 */
public class QuickSort {
    public static void quickSort(int[] array, int left, int right) {
        int index = partition(array, left, right);
        // 递归排序左边
        if(left < index -1) {
            quickSort(array, left, index -1);
        }
        // 递归排序右边
        if (index + 1 < right) {
            quickSort(array, index, right);
        }
    }

    /**
     * 找到基准点，使得左边都小于它，右边都大于它
     * @param array
     * @param left
     * @param right
     * @return
     */
    public static int partition(int[] array, int left, int right) {
        int pivot = array[( left + right) / 2];
        int temp;
        while (left < right) {
            // 找到左边第一个比基准小的
            while (array[left] < pivot)
                left++;
            // 找到右边第一个比基准大的
            while (array[right] > pivot)
                right--;

            // 如果符合条件，则交换两边位置
            if(left < right) {
                temp= array[left];
                array[left] = array[right];
                array[right] = temp;
                // 回退
                left++;
                right--;
            }
        }
        // 返回基准点左边的
        return left;
    }

    public static void main(String[] args) {
        int[] arr = {123,456,324,5645,243234,45656,56,324,453,234};
        PrintUtil.printArray(arr);
        quickSort(arr, 0, arr.length-1);
        System.out.println();
        PrintUtil.printArray(arr);
    }
}
