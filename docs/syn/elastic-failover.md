## elastic 失效转移

如果一个任务节点宕机后，则一次任务调度期间，一部分数据将不会被处理，为了解决由于任务节点宕机引起任务一个调度周期的一次任务执行部分数据未处理，可以设置开启故障失效转移，将本次任务转移到其他正常的节点上执行。

失效转移也是通过监听器来处理的,在`failoverListenerManager`

### 失效转移监听器

`failoverListenerManager#start`:
```
public void start() {
    addDataListener(new JobCrashedJobListener());
    addDataListener(new FailoverSettingsChangedJobListener());
}
```
- JobCrashedJobListener: Job实例宕机事件监听器
- FailoverSettingsChangedJobListener： 失效转移配置变化事件监听器

### JobCrashedJobListener(作业实例监听)

```
protected void dataChanged(final String path, final Type eventType, final String data) {
    // 如果配置了失效转移
    // 并且event是namespace/jobname/instances下面的节点被删除
    if (isFailoverEnabled() && Type.NODE_REMOVED == eventType && instanceNode.isInstancePath(path)) {
        // 获取宕机节点的instanceId
        String jobInstanceId = path.substring(instanceNode.getInstanceFullPath().length() + 1); 
        // 如果被删除节点id与当前实例id相同则忽略
        if (jobInstanceId.equals(JobRegistry.getInstance().getJobInstance(jobName).getJobInstanceId())) {
            return;
        }
        // 根据instanceId获取宕机job节点的分片项合集
        List<Integer> failoverItems = failoverService.getFailoverItems(jobInstanceId);
        if (!failoverItems.isEmpty()) {
            for (int each : failoverItems) {
                failoverService.setCrashedFailoverFlag(each);
                failoverService.failoverIfNecessary();
            }
        } else {
            for (int each : shardingService.getShardingItems(jobInstanceId)) {
                failoverService.setCrashedFailoverFlag(each);
                failoverService.failoverIfNecessary();
            }
        }
    }
```
