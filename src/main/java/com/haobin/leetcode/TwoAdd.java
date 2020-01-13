package com.haobin.leetcode;

/**
 * @Author HaoBin
 * @Create 2020/1/13 13:56
 * @Description: 两数相加
 * <p>
 * 给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。
 * 如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。 您可以假设除了数字 0
 * 之外，这两个数都不会以 0 开头。 示例： 输入：(2 -> 4 -> 3) + (5 -> 6 -> 4) 输出：7 -> 0 -> 8 原因：342 + 465 = 807
 **/
public class TwoAdd {

    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

    /**
     * 1. 数组逆序存储，顺序遍历刚好是低位开始相加
     * 2. 链表长度不一， 短链表补0
     * 3. 链表遍历完毕后如果进位为1， 需要在最后补一个为1的节点
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode current = new ListNode(0);
        ListNode pre = current;
        int carry = 0;
        int sum = 0;
        while (l1 != null || l2 != null) {
            int v1 = l1== null ? 0 : l1.val;
            int v2 = l2 == null ? 0 : l2.val;
            sum = v1 + v2 + carry;
            carry = sum / 10;
            int val = sum % 10;
            current.next = new ListNode(val);
            current = current.next;
            if (l1 != null) {
                l1 = l1.next;
            }
            if (l2 != null) {
                l2 = l2.next;
            }
        }
        if (carry == 1) {
            current.next = new ListNode(1);
        }
        return pre.next;
    }
}
