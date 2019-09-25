package com.haobin.offer;

/**
 * @author: HaoBin
 * @create: 2019/9/25 11:16
 * @description: 跳台阶
 * 一只青蛙一次可以跳上 1 级台阶，也可以跳上 2 级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法
 *
 * 当 n = 1 时，只有一种跳法
 * 当 n = 2 时，有两种跳法（1,1|2）
 * 跳 n 阶台阶，可以先跳 1 阶台阶，再跳 n-1 阶台阶；或者先跳 2 阶台阶，再跳 n-2 阶台阶
 * 同样转换成子问题求解， 递推公式为:
 *       f(n) =       1   n=1
 *            =       2   n=2
 *            =       f(n-1) + f(n-2) n>2
 **/
public class JumpFloor {

    public int jumpFloor(int n) {
        if (n < 2) {
            return n;
        }
        // 前一种和前两种
        int pre1 = 2, pre2 = 1;
        int result = 1;
        for (int i = 3; i < n; i++) {
            result = pre1 + pre2;
            pre2 = pre1;
            pre1 = result;
        }
        return result;
    }
}
