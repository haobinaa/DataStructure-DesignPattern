/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.code.queue;

/**
 *
 *
 * @author HaoBin
 * @version $Id: MessageHandler.java, v0.1 2019/1/28 21:52 HaoBin 
 */
public interface MessageHandler {
    void consume(String message);
}