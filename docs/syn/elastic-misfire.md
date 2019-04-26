### elastic-job 任务错过机制与事件监听器


任务在调度执行中，由于某种原因未执行完毕，下一次调度任务触发后，在同一个Job实例中，会出现两个线程处理同一个分片上的数据，这样就会造成两个线程可能处理到相同的数据。为了避免同一条数据可能会被多次执行的问题，ElasticJob引入幂等机制，确保同一条数据不会再被多个Job同时处理，也避免同一条数据在同一个Job实例的多个线程处理。

Elastic-job 通过补偿执行(misfire)和幂等(monitorExecution)解决了两个问题：
1. 保证同一个job的多个实例不会处理到相同的数据
2. 确保数据不会被多个job实例处理


### misfire执行处理

还是在作业执行的入口`AbstractElasticJobExecutor#execute->misfireIfRunning`:
```
if (jobFacade.misfireIfRunning(shardingContexts.getShardingItemParameters().keySet())) {
    // 如果当前分片被标记为了misfire并开启了时间追踪，将事件追踪保存入库
    if (shardingContexts.isAllowSendJobEvent()) {
        jobFacade.postJobStatusTraceEvent(shardingContexts.getTaskId(), State.TASK_FINISHED, String.format(
                "Previous job '%s' - shardingItems '%s' is still running, misfired job will start after previous job completed.", jobName, 
                shardingContexts.getShardingItemParameters().keySet()));
    }
    return;
}
```

#### misfireIfRunning 流程

`jobFacade#misfireIfRunning->executionService#misfireIfHasRunningItems`:
```
public boolean misfireIfHasRunningItems(final Collection<Integer> items) {
    if (!hasRunningItems(items)) {
        return false;
    }
    setMisfire(items);
    return true;
}
```
elastic-job在开启了`monitorExecution(true)`(默认为true)的情况下，作为幂等机制的考虑，在分片任务开始的时候会创建`namespace/jobname/sharding/{item}/running`节点,任务结束时会自动删除该节点。 在判断是否有分片正在运行的时候，只需要判断是否存在该节点即可。

如果存在，则调用`setMisfire`,给当前实例的所有分片创建`namespace/jobname/shading/{item}/misfire`节点:
```
public void setMisfire(final Collection<Integer> items) {
    for (int each : items) {
        jobNodeStorage.createJobNodeIfNeeded(ShardingNode.getMisfireNode(each));
    }
}
```

在回到`execute`中:
```
......
execute(shardingContexts, JobExecutionEvent.ExecutionSource.NORMAL_TRIGGER);
while (jobFacade.isExecuteMisfired(shardingContexts.getShardingItemParameters().keySet())) {
    jobFacade.clearMisfire(shardingContexts.getShardingItemParameters().keySet());
    execute(shardingContexts, JobExecutionEvent.ExecutionSource.MISFIRE);
}
......
```
在任务执行完成后检查是否存在`namespace/jobname/sharding/{item}/misfire`节点，如果存在，则首先清除misfie相关的文件，然后执行任务（同一任务不管错过多少次，都只会补偿执行一次）。


### 