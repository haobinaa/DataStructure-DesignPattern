package com.haobin.algorithm.shuffle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * @Date 2021/12/27 11:13 上午
 * @author: leobhao
 *
 *
 * 1. 记下从1到n的数字
 *
 * 2. 从1到剩余的未删除数字之间选择一个随机数k
 *
 * 3. 从首端开始计数，删除尚未删除的第k个数字，并将其写在一个单独的结果列表的末尾
 *
 * 4. 重复从第2步，直到所有数字都被删除
 *
 * 5. 在步骤3中写下的数字序列即是原始数字的随机排列
 */
public class FisherYates {

    public static void main(String[] args) {

        int[] arr = new int[]{1, 2, 3, 4, 5, 6};
        shuffle(arr);
        System.out.println(Arrays.toString(arr));
    }


    public static void shuffle(int[] shuffleNums) {
        List<Integer> list = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < shuffleNums.length; i++) {
            list.add(shuffleNums[i]);
        }
        int shuffleIndex = 0;
        for(int j = list.size()-1; j >= 0; j--) {
            int index = random.nextInt(j+1);
            shuffleNums[shuffleIndex++] = list.get(index);
            list.remove(index);
        }
    }
}
