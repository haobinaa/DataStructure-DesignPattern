/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.datastructure.algorithm.lru;

import java.util.HashMap;
import java.util.Map;

/**
 * @author HaoBin
 * @version $Id: LRUMap.java, v0.1 2018/6/13 16:17 HaoBin
 */
public class LRUMap<K, V> {

    private final Map<K, V> cacheMap = new HashMap<>();

    /**
     * 最大缓存大小
     */
    private int cacheSize;

    /**
     * 节点大小
     */
    private int size;

    /**
     * 头结点
     */
    private Node<K, V> head;

    /**
     * 尾节点
     */
    private Node<K, V> tail;

    public LRUMap(int cacheSize) {
        this.cacheSize = cacheSize;
        // 头结点的下一个节点为null
        head = new Node<>();
        head.next = null;
        // 尾节点的上一个节点为null
        tail = new Node<>();
        tail.prev = null;
        // 双向链表， 头结点的上节点指向尾节点
        head.prev = tail;
        // 尾节点的下节点指向头结点
        tail.next = head;
    }

    public void put(K key, V value) {
        cacheMap.put(key, value);
        //双向链表中添加结点
        addNode(key, value);
    }

    public V get(K key) {
        Node<K, V> node = getNode(key);
        //移动到头结点
        moveToHead(node);
        return cacheMap.get(key);
    }

    private void moveToHead(Node<K, V> node) {
        //如果是最后的一个节点
        if (node.prev == null) {
            node.next.prev = null;
            tail = node.next;
            size--;
        }
        //如果是本来就是头节点 不作处理
        if (node.next == null) {
            return;
        }
        //如果处于中间节点
        if (node.prev != null && node.next != null) {
            //它的上一节点指向它的下一节点 也就删除当前节点
            node.prev.next = node.next;
            size--;
        }
        //最后在头部增加当前节点
        //注意这里需要重新 new 一个对象，不然原本的node 还有着下面的引用，会造成内存溢出。
        node = new Node<>(node.getKey(), node.getValue());
        addHead(node);
    }

    /**
     * 链表查询
     */
    private Node<K, V> getNode(K key) {
        Node<K, V> node = tail;
        while (node != null) {
            if (node.getKey().equals(key)) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    /**
     * 头插法
     */
    private void addNode(K key, V value) {
        Node<K, V> node = new Node<>(key, value);
        //容量满了删除最后一个
        if (cacheSize == size) {
            //删除尾结点
            delTail();
        }
        //写入头结点
        addHead(node);
    }

    /**
     * 添加头结点
     */
    private void addHead(Node<K, V> node) {
        //写入头结点
        head.next = node;
        node.prev = head;
        head = node;
        size++;
        //如果写入的数据大于2个 就将初始化的头尾结点删除
        if (size == 2) {
            tail.next.next.prev = null;
            tail = tail.next.next;
        }
    }

    private void delTail() {
        //把尾结点从缓存中删除
        cacheMap.remove(tail.getKey());
        //删除尾结点
        tail.next.prev = null;
        tail = tail.next;
        size--;
    }

    private class Node<K, V> {

        private K key;
        private V value;
        Node<K, V> prev;
        Node<K, V> next;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public Node() {
        }

        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node<K, V> node = tail;
        while (node != null) {
            sb.append(node.getKey()).append(":")
                    .append(node.getValue())
                    .append("-->");
            node = node.next;
        }
        return sb.toString();
    }

}