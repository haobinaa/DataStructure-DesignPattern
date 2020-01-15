package com.haobin.leetcode.linkedlist;

import com.haobin.leetcode.linkedlist.ReverseList.ListNode;

/**
 * @Author HaoBin
 * @Create 2020/1/15 17:46
 * @Description: 链表是否有环
 **/
public class HasCycle {

    /**
     * 快慢指针，当快指针走到头证明无环，若快指针赶上慢指针则证明有环
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode fast, slow;
        slow = fast= head;
        while (fast.next !=null && fast.next.next != null) {
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        return false;
    }
}
