package com.haobin.datastructure.tree;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * 广度优先遍历 BFS
 * 深度优先遍历 DFS
 * @Date 2021/7/21 8:08 下午
 * @author: leobhao
 */
public class Search {


    /**
     * 非递归的方式深度优先
     * 使用栈, 实际上是先序遍历
     * @param root
     */
    public static void deepFirstSearch(BinaryTreeNode root) {
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
}
