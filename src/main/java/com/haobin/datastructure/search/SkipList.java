/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.datastructure.search;


import java.util.Random;

/**
 * 1. 跳表采用的是双向链表，无论前后结点还是上下结点，都各有两个指针相互指向彼此
 * 2. 插入节点会随机"晋升", 用来优化节点多而索引不够用
 * 3. 删除节点后， 需要把其关联的索引节点也删除， 如果索引节点被删光了，直接失去那一层索引
 *
 * @author HaoBin
 * @version $Id: SkipList.java, v0.1 2019/2/26 22:59 HaoBin
 */
public class SkipList {

    private Node head, tail;

    private int maxLevel;

    // 每个插入节点都有概率向上晋升为索引节点
    private static final double PROMOTE_RATE = 0.5;

    /**
     * 为了方便代码编写， 跳表每层最左侧和最右侧分别是空节点，负无穷大和正无穷大
     */
    public SkipList() {
        head = new Node(Integer.MIN_VALUE);
        tail = new Node(Integer.MAX_VALUE);
        head.right = tail;
        tail.left = head;
    }

    /**
     * 跳表中查找节点
     */
    public Node search(int data) {
        Node p = findNode(data);
        if (p.data == data) {
            return p;
        }
        System.out.println("未找到节点:" + data);
        return null;
    }

    public Node findNode(int data) {
        Node node = head;
        while (true) {
            // 向右遍历索引
            while (node.right.data != Integer.MAX_VALUE && node.right.data <= data) {
                node = node.right;
            }
            // 如果向右遍历已经满足条件， 则向下遍历下一层
            if (node.down == null) {
                break;
            }
            node = node.down;
        }
        return node;
    }

    public void insert(int data) {
        Node preNode = findNode(data);
        if (preNode.data == data) {
            System.out.println("已有节点");
            return;
        }
        Node node = new Node(data);
        appendNode(preNode, node);
        int currentLevel = 0;
        Random random = new Random();
        // 随机决定节点是否晋升
        while (random.nextDouble() < PROMOTE_RATE) {
            // 如果当前层数已经是最高层， 需要增加一层
            if (currentLevel == maxLevel) {
                addLevel();
            }
            // 找到上一层的前置节点
            while (preNode.up == null) {
                preNode = preNode.left;
            }
            preNode = preNode.up;
            // 新晋节点插入上一层
            Node upperNode = new Node(data);
            appendNode(preNode, upperNode);
            upperNode.down = node;
            node.up = upperNode;
            // 这里赋值是为了后面可能继续向上晋升
            node = upperNode;
            currentLevel++;
        }
    }

    private void appendNode(Node preNode, Node newNode) {
        newNode.left = preNode;
        newNode.right = preNode.right;
        preNode.right.left = newNode;
        preNode.right = newNode;
    }

    private void addLevel() {
        maxLevel++;
        Node p1 = new Node(Integer.MIN_VALUE);
        Node p2 = new Node(Integer.MAX_VALUE);
        p1.right = p2;
        p2.left = p1;
        p1.down = head;
        head.up = p1;
        p2.down = tail;
        tail.up = p2;
        head = p1;
        tail = p2;
    }

    public boolean remove(int data) {
        Node removeNode = search(data);
        if (removeNode == null) {
            return  false;
        }
        int currentLevel = 0;
        while (removeNode != null) {
            removeNode.right.left = removeNode.left;
            removeNode.left.right = removeNode.right;
            // 如果当前不是最底层， 并且当前层只有带删除节点一个节点， 就删除当前层
            if (currentLevel !=0 && removeNode.left.data == Integer.MIN_VALUE && removeNode.right.data == Integer.MAX_VALUE) {
                removeLevel(removeNode.left);
            } else {
                currentLevel++;
            }
            removeNode = removeNode.up;
        }
        return true;
    }

    private void removeLevel(Node leftNode) {
        Node rightNode = leftNode.right;
        // 如果删除层是最高层
        if (leftNode.up == null) {
            leftNode.down.up = null;
            rightNode.down.up = null;
        } else {
            leftNode.up.down = leftNode.down;
            leftNode.down.up = leftNode.up;
            rightNode.up.down = rightNode.down;
            rightNode.down.up = rightNode.up;
        }
        maxLevel--;
    }

    public void printList() {
        Node node=head;
        while (node.down != null) {
            node = node.down;
        }
        while (node.right.data != Integer.MAX_VALUE) {
            System.out.print(node.right.data + " ");
            node = node.right;
        }
        System.out.println();
    }

    /**
     * 跳表节点
     */
    public class Node {
        // 存储数据
        public int data;
        //跳表结点的前后和上下都有指针
        public Node up, down, left, right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        SkipList list=new SkipList();
        list.insert(50);
        list.insert(15);
        list.insert(13);
        list.insert(20);
        list.insert(100);
        list.insert(75);
        list.insert(99);
        list.insert(76);
        list.insert(83);
        list.insert(65);
        list.printList();
        list.remove(50);
        list.search(50);
    }
}