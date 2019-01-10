/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.algorithm.lru;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 *
 * @author HaoBin
 * @version $Id: LRULinkedMap.java, v0.1 2019/1/10 11:30 HaoBin 
 */
public class LRULinkedMap<K, V> {
    /**
     * 最大缓存大小
     */
    private int cacheSize;

    private LinkedHashMap<K,V> cacheMap ;


    public LRULinkedMap(int cacheSize) {
        this.cacheSize = cacheSize;

        cacheMap = new LinkedHashMap(16,0.75F,true){
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                if (cacheSize + 1 == cacheMap.size()){
                    return true ;
                }else {
                    return false ;
                }
            }
        };
    }

    public void put(K key,V value){
        cacheMap.put(key,value) ;
    }

    public V get(K key){
        return cacheMap.get(key) ;
    }


    public Collection<Entry<K, V>> getAll() {
        return new ArrayList<Entry<K, V>>(cacheMap.entrySet());
    }
}