package com.haobin.algorithm;

/**
 * @Author HaoBin
 * @Create 2019/12/22 11:29
 * @Description: 汉诺塔解法
 *  有三根细柱（A、B、C），A柱上套着6个圆盘，圆盘的大小都不一样，它们按照从大到小的顺序自下而上地摆放，
 *  现在我们需要把A柱上的圆盘全部移动到B柱上去，并且在移动时有如下约定：
 *  1. 一次只能移动柱子最上端的一个圆盘
 *  2. 小圆盘上不能放大圆盘
 *
 **/
public class Hanoi {


    /**
     * 递推公式为:
     * H(n) = 0  n=0
     *      = H(n-1) + 1 + H(n-1) n>= 1  (将上层的取出来，然后将最底下大的放好，在将上层的移回来)
     * @param args
     */
    public static void main(String[] args) {
        System.out.println(hanno(1));
    }

    private static int hanno(int n) {
        if (n == 0) {
            return 0;
        } else {
            return hanno(n-1)*2 + 1;
        }
    }
}
