package io.haobin.sort;

/**
 * @Author: HaoBin
 * @Date 2017/12/21 17:51
 */
public class QuickSort {
    public static void quickSort(int[] array, int left, int right) {

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
            while (array[left] < pivot)
                left++;
            while (array[right] > pivot)
                right--;

            if(left < right) {
                temp= array[left];
                array[left] = array[right];
                array[right] = temp;
                left++;
                right--;
            }
        }
        return left;
    }
}
