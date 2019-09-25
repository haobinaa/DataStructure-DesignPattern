package com.haobin.offer;

/**
 * @author: HaoBin
 * @create: 2019/9/25 11:16
 * @description: 跳台阶
 *
 * 题目描述
 * 一只青蛙一次可以跳上 1 级台阶，也可以跳上 2 级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法
 *
 *
 * 思路：
 * 当 n = 1 时，只有一种跳法
 * 当 n = 2 时，有两种跳法（1,1|2）
 * 跳 n 阶台阶，可以先跳 1 阶台阶，再跳 n-1 阶台阶；或者先跳 2 阶台阶，再跳 n-2 阶台阶
 * 同样转换成子问题求解， 递推公式为:
 *       f(n) =       1   n=1
 *            =       2   n=2
 *            =       f(n-1) + f(n-2) n>2
 *
 *
 * 另一种理解方法:
 * (1) 假设n个台阶时有f(n)种走法
 * (2) 青蛙最后一步要么跨1个台阶要么跨2个台阶
 * (3) 当最后一步跨1个台阶即之前有n-1个台阶， 根据(1)的假设有f(n-1)种解法
 * (4) 当最后一步跨2个台阶即之前有n-2个台阶， 根据(1)的假设有f(n-2)种解法
 * (5) 显然n个台阶的走法等于前两种情况的和，即: f(n) = f(n-1) + f(n-2)
 * (6) 递推公式的终止条件为， n=1时 f(1)=1. n=2时， f(2)=2
 **/
public class JumpFloor {


    public static void main(String[] args) {
        JumpFloor j = new JumpFloor();
        System.out.println(j.jumpFloor(5));
        System.out.println(j.recursion(5));
    }

    // 迭代解法
    public int jumpFloor(int n) {
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

    // 递归解法
    public int recursion(int n) {
        if(n < 3) {
            return n;
        }
        return recursion(n-1) + recursion(n-2);
    }
}
