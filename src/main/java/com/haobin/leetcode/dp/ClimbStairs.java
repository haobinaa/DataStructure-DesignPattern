package com.haobin.leetcode.dp;

/**
 * @Author HaoBin
 * @Create 2020/1/15 17:26
 * @Description: 爬楼梯
 **/
public class ClimbStairs {

    /**
     * 递归解法(leetcode 上显示超时)
     */
    public int climbStairs1(int n) {
        if (n < 3) {
            return n;
        }
        return climbStairs1(n-1) + climbStairs1(n-2);
    }


    /**
     * 迭代解法
     * @param n
     * @return
     */
    public int climbStairs(int n) {
        if (n < 2) {
            return n;
        }
        // 前一种和前两种
        int pre1 = 2, pre2 = 1;
        int result = 0;
        for (int i = 2; i < n; i++) {
            result = pre1 + pre2;
            pre2 = pre1;
            pre1 = result;
        }
        return result;
    }

}
