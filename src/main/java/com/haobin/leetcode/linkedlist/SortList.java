package com.haobin.leetcode.linkedlist;

import com.haobin.leetcode.linkedlist.ReverseList.ListNode;

/**
 * @Author HaoBin
 * @Create 2020/2/6 14:46
 * @Description: 排序链表
 *
 * 在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。
 *
 * 示例 1:
 * 输入: 4->2->1->3
 * 输出: 1->2->3->4
 *
 *  示例 2:
 * 输入: -1->5->3->4->0
 * 输出: -1->0->3->4->5
 *
 **/
public class SortList {

    /**
     * 归并排序链表
     * 分割 cut 环节： 找到当前链表中点，并从中点将链表断开（以便在下次递归 cut 时，链表片段拥有正确边界）；
     * 1. 我们使用 fast,slow 快慢双指针法，奇数个节点找到中点，偶数个节点找到中心左边的节点。
     * 2. 找到中点 slow 后，执行 slow.next = None 将链表切断。
     * 3. 递归分割时，输入当前链表左端点 head 和中心节点 slow 的下一个节点 tmp(因为链表是从 slow 切断的)。
     * 4. cut 递归终止条件： 当head.next == None时，说明只有一个节点了，直接返回此节点。
     *
     * 合并 merge 环节： 将两个排序链表合并，转化为一个排序链表。
     * 1. 双指针法合并，建立辅助ListNode h 作为头部。
     * 2. 设置两指针 left, right 分别指向两链表头部，比较两指针处节点值大小，由小到大加入合并链表头部，指针交替前进，直至添加完两个链表。
     * 3. 返回辅助ListNode h 作为头部的下个节点 h.next。
     * 4. 时间复杂度 O(l + r)，l, r 分别代表两个链表长度。
     *
     *
     */
    public ListNode sortList(ListNode head) {
        // head.next = null 时，递归终止(只有一个节点，该归并了)
        if (head == null || head.next == null) {
            return head;
        }
        ListNode fast = head.next;
        ListNode slow = head;
        // 快慢指针找到中点
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        // 中点的下一个节点
        ListNode tmp = slow.next;
        // 截断左边
        slow.next = null;
        ListNode left = sortList(head);
        ListNode right = sortList(tmp);
        ListNode h = new ListNode(0);
        ListNode res = h;
        while (left != null && right != null) {
            if (left.val < right.val) {
                h.next = left;
                left = left.next;
            } else {
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }
        h.next = left == null ? right : left;
        return res.next;
    }
}
