/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.code.queue;

/**
 * @author HaoBin
 * @version $Id: QueueProducer.java, v0.1 2019/1/28 22:18 HaoBin
 */
public interface QueueProducer {

    void produce(String topic, String message) throws ProduceException;
}
