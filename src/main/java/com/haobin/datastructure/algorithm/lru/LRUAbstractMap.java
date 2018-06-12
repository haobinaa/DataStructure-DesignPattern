/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.datastructure.algorithm.lru;

import java.util.Set;
import java.util.concurrent.ExecutorService;

/**
 *
 *
 * @author HaoBin
 * @version $Id: LRUAbstractMap.java, v0.1 2018/6/11 20:28 HaoBin 
 */
public class LRUAbstractMap extends java.util.AbstractMap {
    /**
     * 检查是否超期线程
     */
    private ExecutorService checkTimePool ;

    @Override
    public Set<Entry> entrySet() {
        return null;
    }
}