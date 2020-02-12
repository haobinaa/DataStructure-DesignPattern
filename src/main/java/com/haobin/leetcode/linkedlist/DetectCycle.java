package com.haobin.leetcode.linkedlist;


import com.haobin.leetcode.linkedlist.ReverseList.ListNode;

/**
 * @Author HaoBin
 * @Create 2020/2/12 22:49
 * @Description: 返回链表环的入口
 *
 *
 * 给定一个链表，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 *
 * 为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。
 *
 * 说明：不允许修改给定的链表。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：tail connects to node index 1
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 **/
public class DetectCycle {


    /**
     * 双指针
     * 若链表有环， 当 fast == slow 的时候(第一次相遇)：
     * 设链表共 a + b 个节点， 头节点到环入口有 a 个几点， 环有 b 个节点， fast,slow 经过的节点有如下关系:
     * 1. fast = 2*slow (fast走两步，slow走一步)
     * 2. fast - slow = n*b (fast 比 slow 多走 n 圈环)
     * 两个式子相减得出 => f=2*nb , s = n*b
     * 即 fast 和 slow 分别走了 n 和 2n 个环的周长
     *
     *
     * 解法:
     * 1. 如果让指针从链表头部一直向前走并统计步数k，那么走到链表入口节点时的步数是：k=a+nb
     * 2. 而目前(两指针第一次相遇)，slow 指针走过的步数为 nb 步。因此，只要想办法让 slow 再走 a 步停下来，就可以到环的入口
     * 3. 构建一个指针，此指针需要有以下性质：
     *    此指针和slow 一起向前走 a 步后，两者在入口节点重合。那么从哪里走到入口节点需要 a 步？答案是链表头部 head。
     *
     *
     */
    public ListNode detectCycle(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (true) {
            if (fast == null || fast.next == null) {
                return null;
            }
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) {
                break;
            }
        }
        // 在走a补就到环口
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }
}
