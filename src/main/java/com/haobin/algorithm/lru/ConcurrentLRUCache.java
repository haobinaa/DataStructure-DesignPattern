package com.haobin.algorithm.lru;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Author HaoBin
 * @Create 2020/4/23 9:39
 * @Description: 多线程环境LRU
 **/
public class ConcurrentLRUCache<K, V> {

    private final int maxCapacity;
    private ConcurrentHashMap<K, V> cacheMap;
    private ConcurrentLinkedQueue<K> keys;

    private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
    private Lock writeLock = readWriteLock.writeLock();
    private Lock readLock = readWriteLock.readLock();

    public ConcurrentLRUCache(int maxCapacity) {
        this.maxCapacity = maxCapacity;
        cacheMap = new ConcurrentHashMap<>(maxCapacity);
        keys = new ConcurrentLinkedQueue<>();
    }

    public V put(K key, V value) {
        writeLock.lock();
        try {
            if (cacheMap.containsKey(key)) {
                moveToTailOfQueue(key);
                cacheMap.put(key, value);
                return value;
            }
            if (cacheMap.size() == maxCapacity) {
                removeOldestKey();
            }
            // 如果不在缓存中，则入队并添加到缓存
            keys.add(key);
            cacheMap.put(key, value);
            return value;
        } finally {
            writeLock.unlock();
        }
    }

    public V get(K key) {
        readLock.lock();
        try {
            if (cacheMap.containsKey(key)) {
                moveToTailOfQueue(key);
                return cacheMap.get(key);
            }
            return null;
        } finally {
            readLock.unlock();
        }
    }

    public V remove(K key) {
        writeLock.lock();
        try {
            if (cacheMap.containsKey(key)) {
                keys.remove(key);
                return cacheMap.remove(key);
            }
            return null;
        } finally {
            writeLock.unlock();
        }
    }

    /**
     * 重新添加到队尾
     */
    private void moveToTailOfQueue(K key) {
        keys.remove(key);
        keys.add(key);
    }

    /**
     * 移除队列头部的元素，并从缓存中移除(淘汰最近最久不使用)
     */
    private void removeOldestKey() {
        K oldestKey = keys.poll();
        if (oldestKey != null) {
            cacheMap.remove(oldestKey);
        }
    }

    public int size() {
        return cacheMap.size();
    }
}
