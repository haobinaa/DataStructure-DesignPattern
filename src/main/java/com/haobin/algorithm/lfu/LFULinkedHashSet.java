package com.haobin.algorithm.lfu;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;

/**
 * @Date 2022/1/25 3:11 下午
 * @author: leobhao
 */
public class LFULinkedHashSet {

    // 缓存内容
    Map<Integer, Node> cache;

    // 频次对应的双向链表
    Map<Integer, LinkedHashSet<Node>> freqMap;

    int size;

    int capacity;

    int min;

    public LFULinkedHashSet(int capacity) {
        this.cache = new HashMap<>(capacity);
        freqMap = new HashMap<>();
        this.capacity = capacity;
    }

    public int get(int key) {
        Node node = cache.get(key);
        if(node == null) {
            return -1;
        }
        freqInc(node);
        return node.value;
    }

    void freqInc(Node node) {
        // 从原freq对应的链表里移除, 并更新min
        int freq = node.freq;
        LinkedHashSet<Node> set = freqMap.get(freq);
        // 从频次列表中移除
        set.remove(node);
        if (freq == min && set.size() == 0) {
            min = freq + 1;
        }
        // 加入新freq对应的链表
        node.freq++;
        LinkedHashSet<Node> newSet = freqMap.get(freq + 1);
        if (newSet == null) {
            newSet = new LinkedHashSet<>();
            freqMap.put(freq + 1, newSet);
        }
        newSet.add(node);
    }

    public void put(int key, int value) {
        if (capacity == 0) {
            return;
        }
        Node node = cache.get(key);
        if (node != null) {
            node.value = value;
            freqInc(node);
        } else {
            if (size == capacity) {
                Node removeNode = removeNode();
                cache.remove(removeNode.key);
                size--;
            }
            Node newNode = new Node(key, value);
            cache.put(key, newNode);
            // 加入频次链表
            addNode(newNode);
            size++;
        }
    }

    Node removeNode() {
        LinkedHashSet<Node> set = freqMap.get(min);
        Node deadNode = set.iterator().next();
        set.remove(deadNode);
        return deadNode;
    }

    void addNode(Node node) {
        LinkedHashSet<Node> set = freqMap.get(1);
        if (set == null) {
            set = new LinkedHashSet<>();
            freqMap.put(1, set);
        }
        set.add(node);
        min = 1;
    }
}

class Node {
    int key;
    int value;
    // 相较于普遍的node， 多了访问频次
    int freq = 1;

    public Node() {
    }

    public Node(int key, int value) {
        this.key = key;
        this.value = value;
    }
}

