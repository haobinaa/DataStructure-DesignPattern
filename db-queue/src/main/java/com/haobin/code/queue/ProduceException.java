/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.code.queue;

/**
 * @author HaoBin
 * @version $Id: ProduceException.java, v0.1 2019/1/28 22:17 HaoBin
 */
public class ProduceException extends RuntimeException {

    public ProduceException(String message) {
        super(message);
    }

    public ProduceException(String message, Throwable cause) {
        super(message, cause);
    }
}