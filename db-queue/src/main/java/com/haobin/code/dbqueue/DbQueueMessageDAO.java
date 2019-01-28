/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.code.dbqueue;

import java.util.Collection;
import java.util.List;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author HaoBin
 * @version $Id: DbQueueMessageDAO.java, v0.1 2019/1/28 22:21 HaoBin
 */
public interface DbQueueMessageDAO extends JpaRepository<DbQueueMessageDO, Integer> {

    @Query(value = "SELECT  d FROM  DbQueueMessageDO  d where d.status=2 AND  d.topic = ?1 AND d.locker = ?2 ")
    List<DbQueueMessageDO> selectLockedMessage(String topic, String locker);


    List<DbQueueMessageDO> findByStatusAndTopic(int status, String topic, Pageable pageable);

    @Modifying
    @Query("update DbQueueMessageDO t set t.status=2,t.locker=?2 where t.status=1 and t.topic=?1 and t.id in(?3)")
    int lockTasks(String topic, String locker, Collection<Integer> tryLockIds);

    @Query("UPDATE DbQueueMessageDO t SET t.status=1 WHERE t.status=2 AND SUBTIME(gmtModified,'01:00:00')<now()")
    int unlockTasks();

    @Query("UPDATE DbQueueMessageDO t SET t.status=?2,t.errorMsg=?3 WHERE id=?1")
    @Modifying
    int updateStatus(int id, int status, String errorMsg);

    @Query("SELECT t from DbQueueMessageDO t where t.status=1 and t.topic=?1")
    List<DbQueueMessageDO> getRecord(Pageable pageable);
}
