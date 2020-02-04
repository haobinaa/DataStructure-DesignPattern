package com.haobin.leetcode.bit;

/**
 * @Author HaoBin
 * @Create 2020/2/1 18:20
 * @Description: 汉明重量(二进制中1的个数)
 **/
public class HammingWeight {


    /**
     * 找到给定数字二进制中1的个数
     * 每次与 n-1 做 & 运算，消除最低位的1 ，直到为0为止
     */
    public int hammingWeight(int n) {
        int sum = 0;
        while(n != 0) {
            n = n & (n -1);
            sum++;
        }
        return sum;
    }
}
