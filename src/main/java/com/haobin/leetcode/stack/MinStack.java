package com.haobin.leetcode.stack;

import java.util.Stack;

/**
 * @Author HaoBin
 * @Create 2020/1/15 20:16
 * @Description: 最小栈
 *
 * 设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。
 *
 * push(x) -- 将元素 x 推入栈中。
 * pop() -- 删除栈顶的元素。
 * top() -- 获取栈顶元素。
 * getMin() -- 检索栈中的最小元素。
 *
 *
 * 思路: 空间换时间， 用另一个辅助栈的栈顶始终是当前栈的最小元素
 **/
public class MinStack {

    Stack<Integer> dataStack;
    Stack<Integer> minStack;

    /** initialize your data structure here. */
    public MinStack() {
        dataStack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int x) {
        if (minStack.empty() || x < minStack.peek() ) {
            minStack.push(x);
        } else {
            minStack.push(minStack.peek());
        }
        dataStack.push(x);
    }

    public void pop() {
        minStack.pop();
        dataStack.pop();
    }

    public int top() {
        return dataStack.peek();
    }

    public int getMin() {
        return minStack.peek();
    }
}
