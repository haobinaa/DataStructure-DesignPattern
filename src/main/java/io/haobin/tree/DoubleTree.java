package io.haobin.tree;

/**
 * 二叉树
 * @Author: HaoBin
 * @Date 2018/1/24 23:26
 */
public class DoubleTree {

    /**
     * 定义根
     */
    public Node root;

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


    /**
     * 添加节点
     * @param value
     */
    public void add(int value) {
        Node newNode = new Node(value);
        if (this.root == null) {
            this.root = newNode;
        } else {
            Node temp = this.root;
            while (true) {
                if(value < temp.getValue()) {
                    if (temp.getLeft() == null) {
                        temp.setLeft(newNode);
                        break;
                    } else {
                        temp = temp.getLeft();
                    }
                } else {
                    if (temp.getRight() == null) {
                        temp.setRight(newNode);
                        break;
                    } else {
                        temp = temp.getRight();
                    }
                }
            }
        }
    }

    /**
     * 遍历二叉树
     * @param pNode
     */
    public void showNode(Node pNode) {
        System.out.print(pNode.getValue() + ",");
        if (pNode.getLeft() != null) {
            showNode(pNode.getLeft());
        }
        if (pNode.getRight() != null) {
            showNode(pNode.getRight());
        }
    }

    public static void main(String[] args) {
        DoubleTree doubleTree = new DoubleTree();
        doubleTree.add(2);
        doubleTree.add(4);
        doubleTree.add(6);
        doubleTree.add(1);
        doubleTree.add(8);
        doubleTree.showNode(doubleTree.root);
    }

}
