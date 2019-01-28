/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.code.queue;

/**
 *
 *
 * @author HaoBin
 * @version $Id: QueueConsumer.java, v0.1 2019/1/28 22:14 HaoBin 
 */
public interface QueueConsumer {

    /**
     * 消费队列里面的消息，默认线程数为1
     *
     * @param topic          要消费的topic
     * @param messageHandler 消息的处理逻辑
     */
    void consume(String topic, MessageHandler messageHandler);

    /**
     * @param threadNum      消费的线程数，必须大于等于1
     * @param topic          要消费的topic
     * @param messageHandler 消息的处理逻辑
     */
    void consume(int threadNum, String topic, MessageHandler messageHandler);
}