package com.haobin.algorithm;

/**
 * @Author: HaoBin
 * @Date 2018/1/24 18:50
 *
 * 斐波那契数列: ：F(0)=0，F(1)=1, F(n)=F(n-1)+F(n-2)（n>=2，n∈N*）
 * 当n>=2时，第n项等于前两项之和
 * 如：1、1、2、3、5、8、13、21、34、……
 */
public class Fibonacci {
    // 循环实现斐波那契数列
    public static void loop() {
        int num1 = 1;
        int num2 = 1;
        int num3 = 0;
        // 数列长度
        int n = 6;
        for (int i = 3; i <= n; i++) {
            num3 = num1 + num2;
            num1 = num2;
            num2 = num3;
            System.out.println(num3);
        }
    }

    // 递归实现斐波那契
    public static int run(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return run(n -1) + run(n - 2);
    }

    public static void main(String[] args) {
        System.out.println(run(3));
    }
}
