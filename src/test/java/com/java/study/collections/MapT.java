package com.java.study.collections;

import java.util.HashMap;
import java.util.Map;
import org.junit.Test;

/**
 * @Author HaoBin
 * @Create 2020/5/25 9:12
 * @Description:
 **/
public class MapT {


    /**
     * 双括号初始化， 可能导致内存泄露(匿名内部类问题)
     */
    @Test
    public void anonymousInnerClass() {
        Map<String, String> hashMap = new HashMap(){{
            put("key1", "value1");
            put("key2", "value2");
            put("key3", "value3");
        }};
        hashMap.entrySet().forEach(item -> {
            System.out.println(item.getKey());
        });
    }




}
