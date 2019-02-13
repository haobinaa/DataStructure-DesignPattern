package com.haobin.datastructure.sort;

/**
 * 堆排序
 *
 * @Author: HaoBin
 * @Date 2017/12/26 9:02
 */
public class HeapSort {





    /**
     * 最大堆调整
     */
    public static void maxHeapfiy(int[] array, int index, int heapSize) {
        int iMax = index;
        int iLeft = 2 * index + 1;
        int iRight = 2 * (index + 1);
        // 找出最大的子节点
        if (iLeft < heapSize && array[index] < array[iLeft]) {
            iMax = iLeft;
        }
        if (iRight < heapSize && array[iMax] < array[iRight]) {
            iMax = iRight;
        }
        // 将最大节点放到根
        if (iMax != index) {
            Util.swap(array, iMax, index);
            // 递归调整
            maxHeapfiy(array, iMax, heapSize);
        }
    }

    /**
     * 创建最大堆
     */
    public static void buildMaxHeadp(int[] array, int heapSize) {
        int i;
        // (heapSize -1)/2以后的节点都是叶子节点，没必要调整
        int iParent = (int) Math.floor((heapSize - 1) / 2);
        for (i = iParent; i >= 0; i--) {
            maxHeapfiy(array, i, heapSize);
        }
    }

    /**
     * 堆排序
     */
    public static void heapSort1(int[] array, int heapSize) {
        buildMaxHeadp(array, heapSize);
        for (int i = heapSize - 1; i > 0; i--) {
            Util.swap(array, 0, i);
            maxHeapfiy(array, 0, i);
        }
    }


    public static void main(String[] args) {
        int[] intArr = {100, 908, 1000001, 100, 0, 9999};
        heapSort1(intArr, intArr.length);
        Util.print(intArr);
    }
}
