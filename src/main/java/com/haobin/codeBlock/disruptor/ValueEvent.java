package com.haobin.codeBlock.disruptor;

import com.lmax.disruptor.EventFactory;

/**
 * @Date 2022/2/24 8:23 PM
 * @author: leobhao
 */
public class ValueEvent {

    private long value;

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public final static EventFactory<ValueEvent> EVENT_FACTORY = new EventFactory<ValueEvent>()
    {
        public ValueEvent newInstance()
        {
            return new ValueEvent();
        }
    };
}
