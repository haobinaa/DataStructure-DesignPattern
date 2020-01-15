package com.haobin.leetcode.stack;

import java.util.Stack;

/**
 * @Author HaoBin
 * @Create 2020/1/15 16:33
 * @Description: 有效的括号
 *
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 *
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 *
 * 示例 1:
 * 输入: "()"
 * 输出: true
 **/
public class ValidBrackets {


    /**
     * 利用括号对称的特性，使用栈来保存栈的对应关系
     */
    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (stack.empty() || c == '(' || c == '[' || c == '{') {
                stack.add(c);
            } else if (c == ')' && stack.peek() == '(') {
                stack.pop();
            } else if (c == ']' && stack.peek() == '[') {
                stack.pop();
            } else if (c == '}' && stack.peek() == '{') {
                stack.pop();
            } else {
                return false;
            }
         }
        return stack.empty() ? true : false;
    }

    public static void main(String[] args) {
        String s = "(])";
        System.out.println(isValid(s));
    }
}
