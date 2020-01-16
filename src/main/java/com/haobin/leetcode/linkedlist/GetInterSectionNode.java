package com.haobin.leetcode.linkedlist;

import com.haobin.leetcode.linkedlist.ReverseList.ListNode;

/**
 * @Author HaoBin
 * @Create 2020/1/15 20:38
 * @Description: 查找链表相交节点
 *
 * 题目描述: https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 **/
public class GetInterSectionNode {

    /**
     * 如果两个链表相交，那么相交之后的长度是相等的
     * 那么如果让两个链表距离链表末尾相等距离的位置开始遍历就可以找到，这个距离只能是短链表的长度
     * 做法就是需要消除 A，B 链表的长度差
     * 1. 指针 pA 指向 A 链表，指针 pB 指向 B 链表，依次往后遍历
     * 2. 如果 pA 到了末尾，则 pA = headB 继续遍历
     * 3. 如果 pB 到了末尾，则 pB = headA 继续遍历
     * 4. 比较长的链表指针指向较短链表head时，长度差就消除了
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode intersecVal = null;
        return intersecVal;
    }
}
