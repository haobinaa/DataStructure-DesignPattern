package com.haobin.datastructure.sort;


/**插入排序
 *
 * @Author: HaoBin
 * @Date 2017/12/21 22:11
 */
public class InsertSort {

    // 将数值与以排序好的比较找到合适的地方插入
    public static void insertSort(int[] array) {
        // 将第一个记录看成已经排序好的序列
        for(int i = 1; i < array.length; i++) {
            // 未排序区间的第一个记录
            int temp = array[i];
            // 找到排序好的最后个记录进行比较
            int j = i - 1;
            // 如果到底了，或者找到了插入的位置
            while(j >= 0 && array[j] > temp) {
                // 后移，空出位置
                array[j + 1] = array[j];
                j--;
            }
            // 插入到j的后面
            if(j != i - 1) {
                array[j + 1] = temp;
            }
        }
    }


    public static void main(String[] args) {
        int[] intArr = {100, 908, 1000001, 100, 0, 9999};
        insertSort(intArr);
        Util.print(intArr);
    }

}
