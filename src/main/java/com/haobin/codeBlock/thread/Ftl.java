package com.haobin.codeBlock.thread;

import io.netty.util.concurrent.FastThreadLocal;

/**
 * @Date 2022/1/14 10:49 上午
 * @author: leobhao
 */
public class Ftl {


    public static void main(String[] args) {
        FastThreadLocal<String> ftl = new FastThreadLocal<>();
        ftl.set("hello");
    }
}
