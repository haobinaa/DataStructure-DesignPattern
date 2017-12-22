package io.haobin.sort;

/**插入排序
 *
 * @Author: HaoBin
 * @Date 2017/12/21 22:11
 */
public class InsertSort {

    // 将数值与以排序好的比较找到合适的地方插入
    public static void insertSort(int[] array) {

        for(int i = 1; i < array.length; i++) {
            int temp = array[i];
            int j = i - 1;
            while(j >= 0 && array[j] > temp) {
                array[j + 1] = array[j];
                j--;
            }

            if(j != i - 1) {
                array[j + 1] = temp;
            }
        }
    }
}
