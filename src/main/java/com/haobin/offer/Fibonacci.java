package com.haobin.offer;

/**
 * @author: HaoBin
 * @create: 2019/9/23 10:31
 * @description: 斐波那契数列
 * 实现斐波那契数列
 **/
public class Fibonacci {


    /**
     * 思路:
     * 斐波那契数列:
     * f(n) =  f(n-1) + f(n-2)    (n>1)
     *      =  0                  (n=0)
     *      =  1                  (n=1)
     *
     * 可以看出用递归是最简单的
     */
    public static void main(String[] args) {
        System.out.println(recursion(5));
        System.out.println(fibonacci(5));
    }

    /**
     * 递归实现
     */
    public static int recursion(int n) {
        if (n <= 2) {
            return  1;
        }
        return recursion(n-1) + recursion(n-2);
    }

    /**
     * 动态规划实现
     */
    public static int fibonacci(int n) {
        if (n <= 2) {
            return 1;
        }
        int pre1 = 1, pre2 =1;
        int fib = 0;
        for (int i = 2; i < n; i++) {
            fib = pre1 + pre2;
            pre1 = pre2;
            pre2 = fib;
        }
        return fib;
    }
}
