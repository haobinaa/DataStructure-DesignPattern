package com.haobin.offer;

import java.util.Arrays;

/**
 * @author: HaoBin
 * @create: 2019/9/25 11:16
 * @description: 跳台阶进阶
 *
 * 题目描述：
 * 一只青蛙一次可以跳上 1 级台阶，也可以跳上 2 级... 它也可以跳上 n 级。
 * 求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 *
 *
 * 数学分析思路:
 * 1. 跳上 n-1 级台阶，可以从 n-2 级跳 1 级上去，也可以从 n-3 级跳 2 级上去...等
 *    可表示为 f(n-1) = f(n-1) + f(n-2) + ...... + f(0)
 * 2. 同理可推: 跳上 n 级台阶： f(n) = f(n-1) + f(n-2) + ...... + f(0)
 * 3. 综上可推导 f(n) - f(n-1) = f(n-1), 即：f(n) = 2 * f(n-1)
 * 所以f(n)是一个等比数列
 **/
public class JumpFloorII {

    /**
     * 动态规划解法
     */
    public int JumpFloorII(int target) {
        if (target <= 0) {
            return 0;
        }
        if (target == 1) {
            return 1;
        }
        int result = 1;
        for (int i = 2; i <= target; i++) {
            result = 2 * result;
        }
        return result;
    }
}
