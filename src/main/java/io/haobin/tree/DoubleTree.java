package io.haobin.tree;

/**
 * 二叉树
 * @Author: HaoBin
 * @Date 2018/1/24 23:26
 */
public class DoubleTree {
    /**
     * 定义节点
     */
    class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public Node getLeft() {
            return left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }

    Node root;

    public void add(int value) {
        Node newNode = new Node(value);
        if (root == null) {
            this.root = newNode;
        } else {

        }
    }
}
