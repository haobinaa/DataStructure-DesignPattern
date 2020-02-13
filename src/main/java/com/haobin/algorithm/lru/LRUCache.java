/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.algorithm.lru;

import java.util.HashMap;
import java.util.Iterator;


/**
 * LRU的实现 双向链表 + HashMap 双向链表维护缓存的使用情况， HashMap存储Key到节点的映射
 *
 * @author HaoBin
 * @version $Id: LRU.java, v0.1 2019/1/10 19:42 HaoBin
 */


class LRUCache {

    // Node节点
    private class Node {

        Node pre;
        Node next;
        Integer k;
        Integer v;

        public Node(Integer k, Integer v) {
            this.k = k;
            this.v = v;
        }
    }

    private Node head;
    private Node tail;

    // 存储的Map， k->node的映射， 快速查找出Node
    private HashMap<Integer, Node> map;

    // 最大容量
    private int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>(capacity / 3 * 4);
        head = new Node(null, null);
        tail = new Node(null, null);
    }

    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        // 删除原来的位置
        unlink(node);
        // 加到链表头部
        appendHead(node);
        return node.v;
    }

    private void unlink(Node node) {
        Node pre = node.pre;
        Node next = node.next;
        // 移除node
        pre.next = next;
        next.pre = pre;
        node.pre = null;
        node.next = null;
    }

    private void appendHead(Node node) {
        if (head.next == null) {
            head.next = node;
            node.pre = head;
            tail.pre = node;
            node.next = tail;
        } else {
            Node next = head.next;
            node.next = next;
            next.pre = node;
            node.pre = head;
            head.next = node;
        }
    }

    public void put(int key, int value) {
        // 如已经有该Node， 删除原来在链表中的位置
        if (map.containsKey(key)) {
            Node node = map.get(key);
            unlink(node);
        }
        // 无Node则添加在Map中的映射关系，并放入链表头部
        Node node = new Node(key, value);
        map.put(key, node);
        appendHead(node);
        // 如果超过最大限制则淘汰链表尾部节点
        if (map.size() > capacity) {
            Node toRemove = removeTail();
            map.remove(toRemove.k);
        }
    }

    private Node removeTail() {
        // 找到尾部的前一个节点
        Node node = tail.pre;
        // 淘汰尾节点，并将尾节点的前一个节点设置为尾节点
        Node pre = node.pre;
        // 删除node
        pre.next = tail;
        tail.pre = pre;
        node.pre = null;
        node.next = null;
        return node;
    }


    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.get(1);
        lruCache.put(3, 3);
        lruCache.get(2);
        lruCache.put(4, 4);
        lruCache.get(1);
        lruCache.get(3);
        lruCache.get(4);
    }
}

