package com.haobin.datastructure.collection.list;



/**
 * @Author: HaoBin
 * @Date 2017/12/15 9:19
 */
public class MyArrayList {

    Object[] objs = new Object[4];

    private int size = 0;

    public int size() {
        return size;
    }

    public void add(Object value) {
        if(size >= objs.length) {
            // arrayList 扩容算法
            Object[] temp = new Object[(size * 3) / 2 + 1];
            // 将原来的值给新的扩容后的数组
            for (int i = 0; i < objs.length; i++) {
                temp[i] = objs[i];
            }
            this.objs = temp;
        }
        objs[size] = value;
        size++;
    }

    public void set(int index, Object value) throws Exception{
        // 判断是否越界
        if(index < 0 && index >= size) {
            throw new Exception("out of length");
        }
        this.objs[index] = value;
    }

    public Object get(int index) throws Exception {
        if(index < 0 && index >= size) {
            throw new Exception("out of length");
        }
        return objs[index];
    }

    public void clear() {
        // 控制size来限制MyArrayList长度
        this.size = 0;
    }

    public void remove(int index) throws Exception{
        if(index < 0 && index >= size) {
            throw new Exception("out of length");
        }
        for (int i = index + 1; i < size; i++) {
            objs[i-1] = objs[i];
        }
        size--;
    }
}
