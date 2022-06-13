package com.haobin.leetcode21;

/**
 *
 * 编写一个函数，其作用是将输入的字符串反转过来。输入字符串以字符数组 s 的形式给出。
 *
 * 不要给另外的数组分配额外的空间，你必须原地修改输入数组、使用 O(1) 的额外空间解决这一问题。
 *
 *
 * 示例 1：
 * 输入：s = ["h","e","l","l","o"]
 * 输出：["o","l","l","e","h"]
 *
 *
 *
 * @Date 2022/6/13 11:30 AM
 * @author: leobhao
 */
public class D4ReverseString {


    public void reverseString(char[] s) {
        int leftIndex = 0, rightIndex = s.length - 1;
        while (leftIndex <= rightIndex) {
            char temp = s[leftIndex];
            s[leftIndex] = s[rightIndex];
            s[rightIndex] = temp;
            leftIndex++;
            rightIndex--;
        }
    }
}
