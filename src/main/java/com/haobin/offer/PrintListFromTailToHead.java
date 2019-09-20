package com.haobin.offer;

import java.util.ArrayList;
import java.util.Stack;

/**
 * @author: HaoBin
 * @create: 2019/9/19 8:58
 * @description: 输出一个链表，按从头到尾的顺序返回一个ArrayList
 * eg
 * input: 1 -> 2 -> 3
 * output 3,2,1
 **/
public class PrintListFromTailToHead {


    public static void main(String[] args) {

    }


    /**
     * 头插法构造逆序链表
     */
    public ArrayList<Integer> headInsert(ListNode listNode) {
        ListNode head = new ListNode(-1);
        while (listNode != null) {
            ListNode next = listNode.next;
            listNode.next = head.next;
            head.next = listNode;
            listNode = next;
        }
        // 构建 ArrayList
        ArrayList<Integer> ret = new ArrayList<>();
        head = head.next;
        while (head != null) {
            ret.add(head.val);
            head = head.next;
        }
        return ret;
    }


    /**
     * 使用栈的后进先出的特点来构建一个逆序链表
     */
    public ArrayList<Integer> stackConstruct(ListNode listNode) {
        Stack<Integer> stack = new Stack<>();
        while (listNode != null) {
            stack.add(listNode.val);
            listNode = listNode.next;
        }
        ArrayList<Integer> ret = new ArrayList<>();
        while (!stack.isEmpty()) {
            ret.add(stack.pop());
        }
        return ret;
    }

    static class ListNode {
        public ListNode next = null;
        public Integer val;

        public ListNode(Integer val) {
            this.val = val;
        }
    }
}
