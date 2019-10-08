package com.haobin.offer;

/**
 * @author: HaoBin
 * @create: 2019/10/8 10:26
 * @description: 数值的整数次方
 * 题目描述:
 * 给定一个 double 类型的浮点数 base 和 int 类型的整数 exponent，求 base 的 exponent 次方
 *
 * 解题思路:
 * x^n = (x*x)^(n/2)        n%/2 = 0 n为偶数
 *     = x*(x*x)^(n/2)      n%/2 = 1 n为奇数（因为n/2向下取整，需要补上一位）
 **/
public class Power {

    public double Power(double base, int exponent) {
        if (exponent == 0)
            return 1;
        if (exponent == 1)
            return base;
        boolean isNegative = false;
        if (exponent < 0) {
            exponent = -exponent;
            isNegative = true;
        }
        // 递归求解
        double pow = Power(base * base, exponent / 2);
        if (exponent % 2 != 0)
            pow = pow * base;
        // 负数的指数是倒数
        return isNegative ? 1 / pow : pow;
    }
}
