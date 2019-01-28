/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.code.dbqueue;

import com.haobin.code.queue.MessageHandler;
import com.haobin.code.queue.QueueConsumer;
import com.haobin.code.utils.HostUtils;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import javax.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StopWatch;

/**
 *
 *
 * @author HaoBin
 * @version $Id: DbQueueConsumerImpl.java, v0.1 2019/1/28 22:42 HaoBin 
 */
@Component
public class DbQueueConsumerImpl implements QueueConsumer {

    private static final Logger logger = LoggerFactory.getLogger(DbQueueConsumerImpl.class);
    @Autowired
    private DbQueueMessageDAO dbQueueMessageDAO;
    @Value("${dbqueue.interval.seconds:5}")
    private int intervalSeconds;
    private boolean destroyed = false;
    @Override
    public void consume(String topic, MessageHandler messageHandler) {
        consume(1, topic, messageHandler);
    }

    @Override
    public void consume(int threadNum, String topic, MessageHandler messageHandler) {
        // FIXME 这里使用的无界队列，当任务过多时，有可能会耗尽系统线程资源，产生OOM
        ExecutorService executorService = new ThreadPoolExecutor(5, 5, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>());

        AtomicInteger count =new AtomicInteger(0);
        new Thread(() -> {
            String locker = HostUtils.getServerIp() + "_" + HostUtils.getPid() + "_" + Thread.currentThread().getId();
            StopWatch watch = new StopWatch("locker:"+locker);
            while (!destroyed) {
                //String keyWord = DialectSqlUtil.findKeyWord(OPERATE.LIMIT,dataSourceMetaService.getDataBaseType());
                //查出尝试需要锁的消息
                List<DbQueueMessageDO> messages= dbQueueMessageDAO.findByStatusAndTopic(1,topic,PageRequest
                        .of(0,threadNum));
                if(CollectionUtils.isEmpty(messages)){
                    if(watch.isRunning()) {
                        logger.info(watch.prettyPrint());
                        watch.stop();
                    }
                    try {
                        TimeUnit.SECONDS.sleep(intervalSeconds);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e.getMessage(), e);
                    }
                    continue;
                }
                //根据查出来的id进行锁，可能未锁到有消息
                Set<Integer> tryLockIds = messages.stream().map(DbQueueMessageDO::getId).collect(Collectors.toSet());
                int lockedMessages = dbQueueMessageDAO.lockTasks(topic,locker,tryLockIds);
                if(lockedMessages==0){
                    continue;
                }
                //设置开始取消息时间
                if(!watch.isRunning()){
                    watch.start("获取消息");
                }
                List<DbQueueMessageDO> dbQueueMessageDOS = dbQueueMessageDAO.selectLockedMessage(topic, locker);
                dbQueueMessageDOS.forEach((dbQueueMessage) -> {
                    //状态修改成执行状态。保证同一个消息不会被消费多次
                    dbQueueMessage.setStatus(DbQueueMessageDO.STATUS_EXECUTING);
                    dbQueueMessageDAO.save(dbQueueMessage);
                    executorService.submit(() -> {
                        try {
                            messageHandler.consume(dbQueueMessage.getMessage());
                            dbQueueMessageDAO.delete(dbQueueMessage);
                        } catch (Exception e) {
                            e.printStackTrace();
                            String errorMsg = e.getMessage();
                            errorMsg = null != errorMsg && errorMsg.length() > 100 ? errorMsg.substring(0, 100) : errorMsg;
                            dbQueueMessageDAO.updateStatus(dbQueueMessage.getId(), DbQueueMessageDO.STATUS_FAILED, errorMsg);
                        }
                        if(logger.isDebugEnabled()) {
                            logger.info("消费消息：{},当前消费数量：{}", dbQueueMessage.getMessage(), count.incrementAndGet());
                        }
                    });
                });
            }
        }, "thread-dbqueue-taskmessage-" + topic).start();

    }


    @PreDestroy
    public void close() {
        logger.warn("正在关闭队列的消费者");
        destroyed = true;
    }
}