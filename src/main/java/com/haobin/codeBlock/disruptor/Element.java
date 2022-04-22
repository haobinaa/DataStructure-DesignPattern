package com.haobin.codeBlock.disruptor;

import static io.netty.util.internal.shaded.org.jctools.util.UnsafeAccess.UNSAFE;

/**
 * @Date 2022/2/24 7:59 PM
 * @author: leobhao
 */
class Element {

    private int value;

    public int get() {
        return value;
    }

    public void set(int value) {
        this.value = value;
    }


    public static void main(String[] args) {
        System.out.println(UNSAFE.arrayIndexScale(int[].class));
    }
}
