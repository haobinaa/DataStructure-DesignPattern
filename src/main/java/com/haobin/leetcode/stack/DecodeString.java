package com.haobin.leetcode.stack;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * @Author HaoBin
 * @Create 2020/2/13 17:36
 * @Description: 字符串解码
 *
 * 给定一个经过编码的字符串，返回它解码后的字符串。
 *
 * 编码规则为: k[encoded_string]，表示其中方括号内部的 encoded_string 正好重复 k 次。注意 k 保证为正整数。
 *
 * 你可以认为输入字符串总是有效的；输入字符串中没有额外的空格，且输入的方括号总是符合格式要求的。
 *
 * 此外，你可以认为原始数据不包含数字，所有的数字只表示重复的次数 k ，例如不会出现像 3a 或 2[4] 的输入。
 *
 * 示例:
 *
 * s = "3[a]2[bc]", 返回 "aaabcbc".
 * s = "3[a2[c]]", 返回 "accaccacc".
 * s = "2[abc]3[cd]ef", 返回 "abcabccdcdcdef".
 *
 **/
public class DecodeString {

    /**
     * 用两个栈存储字符和数字， multi 保存数字， ans 保存当前的字符串
     * 遇到 [ 就将数字和当前计算的字符串入栈
     * 遇到 ] 就将当前栈顶的数字和字符取出做运算，然后加上之前字符串
     * 遇到数字计算 multi , 可能存在多个数字相连的情况， 需要将之前计算的 multi 进位
     */
    public String decodeString(String s) {
        StringBuilder ans = new StringBuilder();
        Integer multi = 0;
        LinkedList<Integer> multiStack = new LinkedList<>();
        LinkedList<String> ansStack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '[') {
                ansStack.addLast(ans.toString());
                multiStack.addLast(multi);
                multi = 0;
                ans = new StringBuilder();
            } else if (c == ']') {
                StringBuilder tmp = new StringBuilder();
                int currentMulti = multiStack.removeLast();
                for (int j = 0; j < currentMulti; j++) {
                    tmp.append(ans);
                }
                ans = new StringBuilder(ansStack.removeLast() + tmp);
            } else if (c >= '0' && c <= '9'){
                // 如果数字是多位，需要进位(乘10)
                multi = multi * 10 + Integer.parseInt(c + "");
            } else {
                ans.append(c);
            }
        }
        return ans.toString();
    }
}
