package haobin.leetcode.stack;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

/**
 * 1. 定义两个栈：一个用来存放数字、另一个存放运算符
 * 2. 定义进行计算的子过程：弹出栈顶运算符，弹出相应数量的数字，计算后结果入数字栈。
 * 3. 自左向右地，每扫描到一个字符 ch：
 *      如果遇到数字，直接入数字栈
 *      如果遇到四则运算符：
 *      如果运算符栈顶优先级更高，则先计算、再入栈（先计算栈顶，直到栈顶优先级比当前运算符低为止； 再把当前运算符入栈）
 *      否则 先入栈、后面等机会再计算。
 *      如果遇到左括号，直接入运算符栈，以等待右括号闭合。
 *      如果遇到右括号，则不断出栈进行计算，直到左括号出栈。
 * 4. 最后数字栈中应该仅剩余一个元素，即计算结果。
 *
 */
public class CalExpression {

    public static void main(String[] args) {
        CalExpression c = new CalExpression();
        String expr = "3+1+(2x(2+1-2x1+1))";
        System.out.println(c.calculate(expr));
    }


    public int calculate(String s) {
        // 存放所有的数字
        Stack<Integer> numS = new Stack<>();
        // 为了防止第一个数为负数，先往 nums 加个 0
        numS.push(0);
        // 将所有的空格去掉
        s = s.replaceAll(" ", "");
        // 存放所有的操作，包括 +/-
        Stack<Character> ops = new Stack<>();
        int n = s.length();
        char[] cs = s.toCharArray();
        for (int i = 0; i < n; i++) {
            char c = cs[i];
            if (c == '(') {
                ops.push(c);
            } else if (c == ')') {
                // 计算到最近一个左括号为止
                while (!ops.isEmpty()) {
                    char op = ops.pop();
                    if (op != '(') {
                        calc(numS, ops);
                    } else {
                        ops.pop();
                        break;
                    }
                }
            } else {
                if (isNum(c)) {
                    int u = 0;
                    int j = i;
                    // 将从 i 位置开始后面的连续数字整体取出，加入 nums
                    while (j < n && isNum(cs[j])) u = u * 10 + (int)(cs[j++] - '0');
                    numS.push(u);
                    i = j - 1;
                } else {
                    if (i > 0 && (cs[i - 1] == '(' || cs[i - 1] == '+' || cs[i - 1] == '-')) {
                        numS.push(0);
                    }
                    // 有一个新操作要入栈时，先把栈内可以算的都算了
                    while (!ops.isEmpty() && ops.pop() != '(') calc(numS, ops);
                    ops.push(c);
                }
            }
        }
        while (!ops.isEmpty()) calc(numS, ops);
        return numS.pop();
    }
    void calc(Stack<Integer> nums, Stack<Character> ops) {
        if (nums.isEmpty() || nums.size() < 2) return;
        if (ops.isEmpty()) return;
        // 弹出两个操作数
        int b = nums.pop(), a = nums.pop();
        char op = ops.pop();
        switch (op) {
            case '+':
                nums.push(a + b);
            case '-':
                nums.push(a-b);
            case  '*':
                nums.push(a*b);
            case '/':
                nums.push(a/b);
        }
    }

    boolean isNum(char c) {
        return Character.isDigit(c);
    }
}
