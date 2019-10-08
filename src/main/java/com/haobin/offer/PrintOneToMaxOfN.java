package com.haobin.offer;

/**
 * @author: HaoBin
 * @create: 2019/10/8 15:24
 * @description: 打印从1到最大的n位十进制数
 *
 * 题目描述:
 * 输入数字 n，按顺序打印出从 1 到最大的 n 位十进制数。比如输入 3，则打印出 1、2、3 一直到最大的 3 位数即 999
 **/
public class PrintOneToMaxOfN {


    public static void main(String[] args) {
        PrintOneToMaxOfN printer = new PrintOneToMaxOfN();
        printer.print1ToMaxOfNDigits(3);
    }


    public void print1ToMaxOfNDigits(int n) {
        if (n <= 0)
            return;
        // 位数太多，用字符数组存储结果
        char[] number = new char[n];
        print1ToMaxOfNDigits(number, 0);
    }


    private void print1ToMaxOfNDigits(char[] number, int digit) {
        // 基线条件
        if (digit == number.length) {
            printNumber(number);
            return;
        }
        // 每一位有10个数
        for (int i = 0; i < 10; i++) {
            number[digit] = (char) (i + '0');
            print1ToMaxOfNDigits(number, digit + 1);
        }
    }

    private void printNumber(char[] number) {
        int index = 0;
        while (index < number.length && number[index] == '0')
            index++;
        while (index < number.length)
            System.out.print(number[index++]);
        System.out.println();
    }
}
