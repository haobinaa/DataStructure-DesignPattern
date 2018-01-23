package io.haobin.collection.list;

/**
 * @Author: HaoBin
 * @Date 2018/1/23 22:01
 */
public class MyLinkList {

    int size = 0;

    Node head = null;

    /**
     * 数据节点
     */
    class Node {
        Object value;
        Node next;

        Node(Object value) {
            this.value = value;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }

    public int size() {
        return this.size;
    }
    public void add(Object value) {
        Node newNode = new Node(value);
        if (this.head == null) {
            this.head = newNode;
        } else {
            // 头插法
//        newNode.next = this.head.next;
//        this.head.next = newNode;
            // 添加到尾部
            Node temp = head;
            while (temp.getNext() != null) {
                temp = temp.next;
            }
            temp.setNext(newNode);
        }
        size++;
    }

    public void set(int index, Object value) {
        Node temp = head;
        for (int i = 0;i < index; i++) {
            temp = temp.getNext();
        }
        temp.setValue(value);
    }

    public void clear() {
        this.head = null;
        this.size = 0;
    }
}
