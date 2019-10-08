package com.haobin.offer;

/**
 * @author: HaoBin
 * @create: 2019/10/8 9:52
 * @description: 二进制中1的个数
 *
 * 题目描述：
 * 输入一个整数，输出该数二进制表示中 1 的个数
 *
 *
 * 解题思路:
 * n & (n-1) 可以去除n的二进制中表示1最低的那一位, 例如:
 * n  : 10110100
 * n-1: 10110011
 *
 **/
public class NumberOfOne {


    public int numberOfOne(int n) {
        int cnt = 0;
        // 直到n的二进制没有1为止
        while (n != 0) {
            cnt++;
            n &= (n -1);
        }
        return cnt;
    }
}
