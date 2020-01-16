package com.haobin.leetcode.linkedlist;

import com.haobin.leetcode.linkedlist.ReverseList.ListNode;
import java.util.List;
import java.util.Stack;

/**
 * @Author HaoBin
 * @Create 2020/1/16 15:26
 * @Description: 回文链表
 **/
public class PalindromeLinkedList {

    /**
     * 全部压入栈，然后出栈与原链表比较
     */
    public boolean isPalindrome1(ListNode head) {
        Stack<Integer> stack = new Stack<>();
        ListNode stackPointer = head;
        while (stackPointer != null) {
            stack.push(stackPointer.val);
            stackPointer = stackPointer.next;
        }
        while (stack.empty()) {
            if (head.val != stack.pop()) {
                return false;
            }
            head = head.next;
        }
        return true;
    }

    /**
     * 快慢指针
     * 翻转前半部分链表，然后跟后半部分比较
     */
    public boolean isPalindrome(ListNode head) {
        if(head == null || head.next == null) {
            return true;
        }
        ListNode slow = head, fast = head;
        ListNode pre = head, prepre = null;
        // 快慢指针，翻转前半部分链表
        while(fast != null && fast.next != null) {
            pre = slow;
            slow = slow.next;
            fast = fast.next.next;
            pre.next = prepre;
            prepre = pre;
        }
        if(fast != null) {
            slow = slow.next;
        }
        while(pre != null && slow != null) {
            if(pre.val != slow.val) {
                return false;
            }
            pre = pre.next;
            slow = slow.next;
        }
        return true;
    }
}
