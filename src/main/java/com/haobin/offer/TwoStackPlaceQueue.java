package com.haobin.offer;

import java.util.Stack;

/**
 * @author: HaoBin
 * @create: 2019/9/23 10:21
 * @description: 两个栈实现队列
 * 用两个栈来实现一个队列，完成队列的 Push 和 Pop 操作
 **/
public class TwoStackPlaceQueue {


    /**
     * 思路：
     * in 栈用来处理入队(push)
     * out 栈用来处理出队(pop)
     * 当元素进入 in 栈，出栈的顺序被反转
     * 元素出栈时， 需要先进入 out 栈， 出栈的顺序再次被反转， 所以跟入栈的顺序是相同的(先入先出)
     */
    public static void main(String[] args) {

    }

    Stack<Integer> in = new Stack<>();
    Stack<Integer> out = new Stack<>();

    public void push(int val) {
        in.push(val);
    }

    public int pop() {
        if (out.isEmpty()) {
            // 如果 out 栈为空， 则把全部元素压入 in 栈
            while (!in.isEmpty()) {
                out.push(in.pop());
            }
        }
        // 如果out栈不为空，则直接出栈
        return out.pop();
    }


}
