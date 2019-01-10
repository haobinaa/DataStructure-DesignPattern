/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.algorithm.lru;

import java.util.HashMap;
import java.util.Iterator;


/**
 * LRU的实现
 * 双向链表 + HashMap
 * 双向链表维护缓存的使用情况， HashMap存储Key到节点的映射
 *
 * @author HaoBin
 * @version $Id: LRU.java, v0.1 2019/1/10 19:42 HaoBin
 */
public class SimpleLRU<K, V> implements Iterable<K> {

    private Node head;

    private Node tail;

    // 存储的Map， k->node的映射， 快速查找出Node
    private HashMap<K, Node> map;

    // 最大容量
    private int maxSize;


    // Node节点
    private class Node {
        Node pre;
        Node next;
        K k;
        V v;

        public Node(K k, V v) {
            this.k = k;
            this.v = v;
        }
    }

    public SimpleLRU(int maxSize) {
        this.maxSize = maxSize;
        this.map = new HashMap<>(maxSize * 4/3);

        head = new Node(null, null);
        tail = new Node(null, null);
    }

    public V get(K key) {
        if(!map.containsKey(key)) {
            return null;
        }

        Node node = map.get(key);
        // 删除原来的位置
        unlink(node);
        // 加到链表头部
        appendHead(node);

        return node.v;
    }

    public void put(K key, V value) {
        // 如已经有该Node， 删除原来在链表中的位置
        if(map.containsKey(key)) {
            Node node = map.get(key);
            unlink(node);
        }
        // 无Node则添加在Map中的映射关系，并放入链表头部
        Node node = new Node(key, value);
        map.put(key, node);
        appendHead(node);

        // 如果超过最大限制则淘汰链表尾部节点
        if (map.size() > maxSize) {
            Node toRemove = removeTail();
            map.remove(toRemove.k);
        }
    }

    /**
     * 从链表原来位置移除
     * @param node
     */
    private void unlink(Node node) {
        Node pre = node.pre;
        Node next = node.next;
        // 移除
        pre.next = next;
        next.pre = pre;
        node.pre = null;
        node.next = null;
    }


    /**
     * 将 node 放置到链表头部
     * @param node
     */
    private void appendHead(Node node) {
        Node next = head.next;
        node.next = next;
        next.pre = node;
        // 头节点指向node
        node.pre = head;
        head.next = node;
    }

    /**
     * 淘汰尾部节点
     * @return
     */
    private Node removeTail() {
        // 找到尾部的前一个节点
        Node node = tail.pre;
        // 淘汰尾节点，并将尾节点的前一个节点设置为尾节点
        Node pre = node.pre;
        pre.next = tail;
        node.pre = null;
        node.next = null;
        // 从map中移除映射关系
        map.remove(node.k);
        return node;
    }

    @Override
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private Node cur = head.next;

            @Override
            public boolean hasNext() {
                return cur != tail;
            }

            @Override
            public K next() {
                Node node = cur;
                cur = cur.next;
                return node.k;
            }
        };
    }
}