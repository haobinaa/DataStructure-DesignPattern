package com.haobin.codeBlock.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.YieldingWaitStrategy;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;

import java.util.concurrent.Executors;

/**
 * @Date 2022/2/24 8:04 PM
 * @author: leobhao
 */
public class DisruptorT {

    public static final int RING_SIZE = 16;

    public static void main(String[] args) {
        Disruptor<ValueEvent> disruptor = new Disruptor<ValueEvent>(
                ValueEvent::new,
                RING_SIZE,
                Executors.defaultThreadFactory(),
                ProducerType.SINGLE,
                new YieldingWaitStrategy()
        );
        disruptor.handleEventsWith((event, sequences, endOfBatch) -> {
            System.out.println(event.getValue());
        });
        disruptor.start();
        RingBuffer<ValueEvent> ringBuffer = disruptor.getRingBuffer();
        long sequence = ringBuffer.next();
        System.out.println(sequence);
        ValueEvent valueEvent = ringBuffer.get(sequence);
        valueEvent.setValue(sequence);
        ringBuffer.publish(sequence);
    }
}
