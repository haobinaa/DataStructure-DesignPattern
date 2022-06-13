package com.haobin.leetcode21;

/**
 * 给定一个字符串s，你需要反转字符串中每个单词的字符顺序，同时仍保留空格和单词的初始顺序。
 *
 *
 *
 * 示例 1：
 *
 * 输入：s = "Let's take LeetCode contest"
 * 输出："s'teL ekat edoCteeL tsetnoc"
 *
 * @Date 2022/6/13 3:18 PM
 * @author: leobhao
 */
public class D4ReverseWords {


    /**
     * 本来可以用双指针原地反转的， 但是java无法操作string(不可变)中的字符
     */
    public String reverseWords(String s) {
        StringBuffer ans = new StringBuffer();
        int i = 0;
        while (i < s.length()) {
            int start = i;
            while (i < s.length() && s.charAt(i) != ' ') {
                i++;
            }
            for (int p = start; p < i; p++) {
                ans.append(s.charAt(start+i-p-1));
            }
            while (i < s.length() && s.charAt(i) == ' ') {
                i++;
                ans.append(' ');
            }
        }
        return ans.toString();
    }



    public static void main(String[] args) {
        String str = "Let's take LeetCode contest";
        D4ReverseWords result = new D4ReverseWords();
        System.out.println(result.reverseWords(str));
    }
}
