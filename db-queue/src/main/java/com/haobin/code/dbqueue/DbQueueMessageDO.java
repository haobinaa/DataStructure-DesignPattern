/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.code.dbqueue;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.commons.lang3.StringUtils;
import org.springframework.util.Assert;

/**
 *
 *
 * @author HaoBin
 * @version $Id: DbQueueMessageDO.java, v0.1 2019/1/28 22:26 HaoBin 
 */
@Entity
@Table(name = "db_queue_message")
public class DbQueueMessageDO implements Serializable {

    private static final long serialVersionUID = 5652213124498939276L;

    public static final int STATUS_WAITING = 1;
    public static final int STATUS_LOCKED = 2;
    public static final int STATUS_EXECUTING = 3;
    //    public static final int STATUS_FINISHED = 3;//完成状态暂时没用
    public static final int STATUS_FAILED = 4;
    //主键ID
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    //消息的topic
    private String topic;
    //消息体
    private String message;
    //加锁者
    private String locker;
    //状态 1:待执行 2:执行中 3:已完成
    private Integer status;
    //错误信息
    private String errorMsg;
    //记录创建时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(updatable = false, insertable = false)
    private LocalDateTime gmtCreate;

    //记录修改时间
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(updatable = false, insertable = false)
    private LocalDateTime gmtModified;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
        if (StringUtils.isNotEmpty(errorMsg) && errorMsg.length() > 100) {
            this.errorMsg = errorMsg.substring(0, 50);
        }
    }

    public LocalDateTime getGmtCreate() {
        return gmtCreate;
    }

    public void setGmtCreate(LocalDateTime gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    public LocalDateTime getGmtModified() {
        return gmtModified;
    }

    public void setGmtModified(LocalDateTime gmtModified) {
        this.gmtModified = gmtModified;
    }

    public String getLocker() {
        return locker;
    }

    public void setLocker(String locker) {
        this.locker = locker;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public static DbQueueMessageDO newMessage(String topic, String message) {
        Assert.notNull(topic, "topic不能为空");
        Assert.notNull(message, "message不能为空");
        DbQueueMessageDO dbQueueMessageDO = new DbQueueMessageDO();
        dbQueueMessageDO.setMessage(message);
        dbQueueMessageDO.setTopic(topic);
        dbQueueMessageDO.setStatus(STATUS_WAITING);
        return dbQueueMessageDO;
    }
}