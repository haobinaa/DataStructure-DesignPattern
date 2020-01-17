package com.haobin.leetcode.linkedlist;

import com.haobin.leetcode.linkedlist.ReverseList.ListNode;
import java.util.List;

/**
 * @Author HaoBin
 * @Create 2020/1/15 20:38
 * @Description: 查找链表相交节点
 *
 * 题目描述: https://leetcode-cn.com/problems/intersection-of-two-linked-lists/
 **/
public class GetInterSectionNode {

    /**
     * 暴力解法，对 A 的每个节点都遍历 B 看是否有相同节点。 时间复杂度 O(mn)
     * hash解法: 存储 A 的每个节点的引用， 遍历 B 看是否能映射到 hash 表的同一个位置。 时间复杂度 O(m+n)
     *
     *
     * 双指针法消除长度差：
     * 1. 创建两个指针 pApA 和 pBpB，分别初始化为链表 A 和 B 的头结点。然后让它们向后逐结点遍历。
     * 2. 当 pApA 到达链表的尾部时，将它重定位到链表 B 的头结点; 类似的，当 pBpB 到达链表的尾部时，将它重定位到链表 A 的头结点。
     * 3. 若在某一时刻 pApA 和 pBpB 相遇，则 pA/pB 为相交结点
     * 这个思路的精髓就是，双指针走同样的距离消除长度差
     */
    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode pA = headA;
        ListNode pB = headB;
        // 这里就算没有交点也会最终在两个都走到对方的尾部后退出循环
        // 此时 D(A) = L(A) + L(B) , D(B) = L(B) + L(A)
        // 这个循环写的很巧妙
        while (pA != pB) {
            pA = pA == null ? headB : pA.next;
            pB = pB == null ? headA : pB.next;
        }
        return pA;
    }
}
