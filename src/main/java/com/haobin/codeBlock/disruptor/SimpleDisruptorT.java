package com.haobin.codeBlock.disruptor;

import com.lmax.disruptor.EventHandler;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.util.DaemonThreadFactory;

/**
 * @Date 2022/2/14 11:32 上午
 * @author: leobhao
 */
public class SimpleDisruptorT {

    public static void main(String[] args) throws Exception {
        Disruptor<Element> disruptor = new Disruptor<>(Element::new, 1024,
                DaemonThreadFactory.INSTANCE);

        disruptor.handleEventsWith(
                (EventHandler<Element>) (event, sequence, endOfBatch) -> System.out
                        .println(event.get()));

        disruptor.start();
        disruptor.publishEvent((event, sequence) -> event.set(1));
        // sleep一下 让消费者可以执行到 因为消费线程是守护线程
        Thread.sleep(1000);
    }

}
