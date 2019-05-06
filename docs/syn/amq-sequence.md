### amq 保证消费顺序性

如果一个队列一个消费者的时候，是可以保证按照顺序分发消息，但是一个队列多个消费者。就不是很好保证消费者的处理顺序，activeMQ的处理方法有：
1. exclusive consumer(独占消费者)
2. messageGroups

#### exclusive consumer

用Exclusive Consumer，broker会从queue中，一次发送消息给一个消费者，这样就避免了多个消费者并发消费的问题，从而保证顺序，配置如下：
```
queue = new ActiveMQQueue("TEST.QUEUE?consumer.exclusive=true");
consumer = session.createConsumer(queue);
```

独占消费者的特点：
- 当在接收信息的时候有一个或者多个备份接收消息者和一个独占消息者的同时接收时候，无论两者创建先后，在接收的时候，均为独占消息者接收
- 当在接收信息的时候，有多个独占消费者的时候，只有一个独占消费者可以接收到消息
- 当有多个备份消息者和多个独占消费者的时候，当所有的独占消费者均close的时候，只有一个备份消费者接到到消息
- 当主消费者挂了话，会立刻启用故障切换转移到下一台消费者继续消费

独占消息就是在有多个消费者同时消费一个queue时，可以保证只有一个消费者可以消费消息，这样虽然保证了消息的顺序问题，不过也带来了一个问题，就是这个queue的所有消息将只会在这一个主消费者上消费，其他消费者将闲置，达不到负载均衡分配


#### messageGroups

Message Groups特性是一种负载均衡的机制。在一个消息被分发到consumer之前，broker首先检查消息JMSXGroupID属性。如果存在，那么broker会检查是否有某个consumer拥有这个message group。如果没有，那么broker会选择一个consumer，并将它关联到这个message group。此后，这个consumer会接收这个message group的所有消息，直到：
- Consumer 被关闭
- Message group被关闭，并通过发送一个消息，并设置这个消息的JMSXGroupSeq为-1

配置如下:
```
bytesMessage.setStringProperty("JMSXGroupID", "constact-20100000002");
bytesMessage.setIntProperty("JMSXGroupSeq", -1);
```

这样，同一个queue钟，拥有同样的JMSXGroupID的消息就会发往同一个消费者，不同分组的又会发给不同的消费者并行消费

![](../../images/sync/amq-msg-group.png)



### 参考资料
- [如何解决MQ消费顺序问题](https://segmentfault.com/a/1190000014512075)