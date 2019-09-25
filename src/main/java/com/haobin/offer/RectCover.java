package com.haobin.offer;

/**
 * @author: HaoBin
 * @create: 2019/9/25 9:48
 * @description: 矩形覆盖
 * 我们可以用 2*1 的小矩形横着或者竖着去覆盖更大的矩形。
 * 请问用 n 个 2*1 的小矩形无重叠地覆盖一个 2*n 的大矩形，总共有多少种方法？
 **/
public class RectCover {


    /**
     * 解题思路:
     * 当n=1的时候， 就只有一个方法， 大矩形也是 2*1
     * 当n=2的时候， 有两种覆盖方法(横着两个，竖着两个)
     *
     * 要覆盖 2*n 的大矩形，可以先覆盖 2*1 的矩形，再覆盖 2*(n-1) 的矩形；
     * 或者先覆盖 2*2 的矩形，再覆盖 2*(n-2) 的矩形
     * 对于2*(n-1)和2*(n-2)的情况，可以看成子问题，递推公式:
     * f(n) =       1   n=1
     *      =       2   n=2
     *      =       f(n-1) + f(n-2) n>2
     *
     * @param args
     */
    public static void main(String[] args) {

    }

    public static int rectCovert(int n) {
        if (n <= 2)
            return n;
        int pre2 = 1, pre1 = 2;
        int result = 0;
        for (int i = 3; i <= n; i++) {
            result = pre2 + pre1;
            pre1 = pre2;
            pre2 = result;
        }
        return result;
    }
}
