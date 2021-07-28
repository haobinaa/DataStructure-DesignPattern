package com.haobin.datastructure.tree;

import java.util.*;

/**
 * 广度优先遍历 BFS
 * 深度优先遍历 DFS， 使用非递归的方式
 *
 * @Date 2021/7/21 8:08 下午
 * @author: leobhao
 */
public class Search {


    /**
     * 广度优先， 使用队列层次遍历
     *
     * @param root
     */
    public static void breadthFirstSearch(BinaryTreeNode root) {
        Queue<BinaryTreeNode> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BinaryTreeNode node = queue.poll();
            // 队列先入先出， 优先压入左节点
            if (node.left != null) {
                queue.add(node.left);
            }
            if (node.right != null) {
                queue.add(node.right);
            }
        }
    }


    /**
     * 非递归的方式深度优先
     * 使用栈, 实际上是先序遍历
     *
     * @param root
     */
    public static void preOrder(BinaryTreeNode root) {
        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            BinaryTreeNode node = stack.pop();
            System.out.println(node.data);
            // 先压右节点，满足栈先入后出的特性
            if (node.right != null) {
                stack.push(node.right);
            }
            if (node.left != null) {
                stack.push(node.left);
            }
        }
    }

    /**
     * 非递归方式， 中序遍历
     * @param root
     */
    public static void inOrder(BinaryTreeNode root) {
        Stack<BinaryTreeNode> stack = new Stack<>();
        BinaryTreeNode cur = root;
        while (!stack.isEmpty() || cur != null) {
            if (cur != null) {
                // 入栈， 并一直往左遍历
                stack.push(cur);
                cur = cur.left;
            } else {
                // cur == null 代表已经没有左子节点了, 弹出最近的根节点
                cur = stack.pop();
                // 输出节点
                System.out.println(cur.data);
                // 然后遍历右节点
                cur = cur.right;
            }
        }
    }

    /**
     * 非递归方式后序遍历, 跟前序遍历相反
     * @param root
     */
    public static void postOrder(BinaryTreeNode<Integer> root) {
        LinkedList<Integer> queue = new LinkedList<>();
        Stack<BinaryTreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            BinaryTreeNode<Integer> cur = stack.pop();
            queue.add(cur.data);
            // 因为要从尾到头遍历队列，左节点需要后入队， 这里先压入左节点(后面会先弹出右节点所以后压入的先入队)
            if (cur.left != null) {
                stack.push(cur.left);
            }
            if (cur.right != null) {
                stack.push(cur.right);
            }
        }
        // 从尾到头遍历 queue， 则是后序遍历
        while (!queue.isEmpty()) {
            System.out.printf(queue.getLast().toString());
        }
    }
}
