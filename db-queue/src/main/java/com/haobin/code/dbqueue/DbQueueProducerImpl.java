/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.code.dbqueue;

import com.haobin.code.queue.ProduceException;
import com.haobin.code.queue.QueueProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 *
 * @author HaoBin
 * @version $Id: DbQueueProducerImpl.java, v0.1 2019/1/28 22:42 HaoBin 
 */
@Component
public class DbQueueProducerImpl implements QueueProducer {

    @Autowired
    private DbQueueMessageDAO dbQueueMessageDAO;

    @Override
    public void produce(String topic, String message) throws ProduceException {
        try {
            dbQueueMessageDAO.save(DbQueueMessageDO.newMessage(topic, message));
        } catch (Exception e) {
            throw new ProduceException(e.getMessage(), e);
        }
    }
}
