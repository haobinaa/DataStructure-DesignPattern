package com.haobin.offer;

/**
 * @author: HaoBin
 * @create: 2019/9/23 8:57
 * @description: 二叉树的下一个节点
 * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。
 * 注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针
 **/
public class BinaryTreeNextNode {

    static class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        public TreeLinkNode(int val) {
            this.val = val;
        }
    }

    /**
     * 思路： 是找到中序遍历的下一个节点
     * 1. 如果节点的右子树不为空， 则下一个节点是右子树的最左节点
     * 2. 如果右子树为空， 则向上找第一个左连接指向的树的祖先节点(这个不好理解)
     * @param args
     */
    public static void main(String[] args) {

    }

    public TreeLinkNode GetNext(TreeLinkNode pNode) {
        if (pNode.right != null) {
            TreeLinkNode node = pNode.right;
            while (node.left != null)
                node = node.left;
            return node;
        } else {
            while (pNode.next != null) {
                TreeLinkNode parent = pNode.next;
                if (parent.left == pNode)
                    return parent;
                pNode = pNode.next;
            }
        }
        return null;
    }
}
