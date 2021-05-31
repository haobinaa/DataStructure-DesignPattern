package com.haobin.datastructure;

import java.util.PriorityQueue;

/**
 * @Author HaoBin
 * @Create 2020/2/10 23:24
 * @Description: 堆-数据结构
 * 1. 满二叉树
 * 2. 满足大顶堆或小顶堆
 **/
public class Heap {
    private int[] a; // 数组，从下标1开始存储数据
    private int n;  // 堆可以存储的最大数据个数
    private int count; // 堆中已经存储的数据个数

    public Heap(int capacity) {
        // 索引0不存储数据, left 为 2i, right 为 2i + 1, parent 为 i/2
        a = new int[capacity + 1];
        n = capacity;
        count = 0;
    }

    /**
     * 插入堆
     * 1. 满足满二叉树特性
     * 2. 自下向上比较父节点满足堆特性
     */
    public void insert(int data) {
        if (count >= n) return; // 堆满了
        ++count;
        a[count] = data;
        int i = count;
        // 交换父子节点
        while (i/2 > 0 && a[i] > a[i/2]) { // 自下往上堆化(新插入节点于父节点比较)
            swap(a, i, i/2); // swap()函数作用：交换下标为i和i/2的两个元素
            i = i/2;
        }
    }

    private void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    /**
     * 最后一个节点放到堆顶，然后堆化
     * @return
     */
    public int remove() {
        if (count == 0) {
            return 0;
        }
        int removeElement = a[count];
        a[1] = a[count];
        heapify(a, count, 1);
        return removeElement;
    }

    // 自上往下堆化
    private void heapify(int[] a, int n, int i) {
        while (true) {
            int maxPos = i;
            if (i * 2 <= n && a[i] < a[i * 2]) {
                maxPos = i * 2;
            }
            if (i*2+1 <= n && a[maxPos] < a[i*2+1]) {
                maxPos = i*2+1;
            }
            // 当前已经完成堆化
            if (maxPos == i) {
                break;
            }
            swap(a, i , maxPos);
            i = maxPos;
        }
    }


    public static void main(String[] args) {
        PriorityQueue queue = new PriorityQueue();
        queue.add(2);
        queue.add(4);
    }
}
