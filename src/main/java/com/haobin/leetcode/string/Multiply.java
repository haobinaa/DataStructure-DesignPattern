package com.haobin.leetcode.string;

/**
 * @Author haobin
 * @Date 2020/4/18 5:25 下午
 * @Description: 字符串相乘
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 *
 * 示例 1:
 *
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 *
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 **/
public class Multiply {

    public static String multiply(String num1, String num2) {
        int m = num1.length(), n = num2.length();
        // 结果长度最多 m+n
        int[] res = new int[m+n];
        for (int i = m-1; i >= 0; i--) {
            for (int j = n-1; j >= 0; j--) {
                int mul = Character.getNumericValue(num1.charAt(i)) * Character.getNumericValue(num2.charAt(j));
                // 结果占据的位置索引
                int p1 = i+j, p2 = i + j + 1;
                // 叠加到 res 上
                int sum = res[p2] + mul;
                // 一个取个位，一个取十位
                res[p2] = sum % 10;
                res[p1] += sum / 10;
            }
        }
        int i = 0;
        while (i < res.length && res[i] == 0)
            i++;
        StringBuilder ans = new StringBuilder();
        for (; i < res.length; i++) {
            ans.append(String.valueOf(res[i]));
        }
        return ans.toString().equals("") ? "0" : ans.toString();
    }

    public static void main(String[] args) {
        String num1 = "123";
        String num2 = "45";
        System.out.println(multiply(num1, num2));
    }
}
