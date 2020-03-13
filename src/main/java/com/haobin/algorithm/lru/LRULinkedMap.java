/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.algorithm.lru;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 *
 * @author HaoBin
 * @version $Id: LRULinkedMap.java, v0.1 2019/1/10 11:30 HaoBin 
 */
public class LRULinkedMap<K, V> extends LinkedHashMap<K, V>{

    private static final int MAX_ENTRIES = 3; // 最大容量

    /**
     * 覆盖 LinkedHashMap 的 removeEldesEntry ， 在节点多于 MAX_ENTRIES 就会删除最近最久未使用
     * @param eldest
     * @return
     */
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > MAX_ENTRIES;
    }

    /**
     * 使用LinkedHashMap的构造函数, 设置 accessOrder=true ， 开启LRU顺序
     */
    LRULinkedMap() {
        super(MAX_ENTRIES, 0.75f, true);
    }


    public static void main(String[] args) {
        LRULinkedMap<Integer, String> cache = new LRULinkedMap<>();
        cache.put(1, "a");
        cache.put(2, "b");
        cache.put(3, "c");
        cache.get(1);
        cache.put(4, "d");
        System.out.println(cache.keySet());
    }
}