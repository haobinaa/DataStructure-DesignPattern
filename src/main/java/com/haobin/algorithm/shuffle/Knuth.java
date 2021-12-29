package com.haobin.algorithm.shuffle;

import java.util.Arrays;
import java.util.Random;

/**
 * @Date 2021/12/28 7:12 下午
 * @author: leobhao
 *
 * 与 FisherYates 类似， 只不过不在删除最后一个数字， 而是与当前随机的交换
 */
public class Knuth {


    public static void main(String[] args) {
        int[] arr = new int[]{1, 2, 3, 4, 5, 6};
        shuffle(arr);
        System.out.println(Arrays.toString(arr));
    }

    public static void shuffle(int[] shuffleNums) {
        Random random = new Random();
        for(int j = shuffleNums.length-1; j >= 0; j--) {
            int index = random.nextInt(j+1);
            int temp = shuffleNums[index];
            shuffleNums[index] = shuffleNums[j];
            shuffleNums[j] = temp;
        }
    }
}
