package com.haobin.codeBlock.IOModel;

import java.io.EOFException;
import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: HaoBin
 * @create: 2019/10/23 16:11
 * @description: 多线程 reactor handler
 **/
public class MultiHandler extends BasicHandler {

    static ThreadPoolExecutor workPool =
            new ThreadPoolExecutor(1,
                    1,
                    60, TimeUnit.SECONDS,
                    new ArrayBlockingQueue<>(10),
                    new ThreadFactory() {
                        private AtomicInteger count = new AtomicInteger(0);
                        @Override
                        public Thread newThread(Runnable r) {
                            Thread t = new Thread(r);
                            t.setName(MultiHandler.class.getName() + count.addAndGet(1));
                            return t;
                        }
                    },
                    new ThreadPoolExecutor.AbortPolicy());

    static final int PROCESSING = 4;
    private Object lock = new Object();

    public MultiHandler(Selector selector, SocketChannel sc) throws IOException {
        super(selector, sc);
    }

    @Override
    protected void read() throws IOException {
        // 为什么要同步？Processer 线程处理时通道还有可能有读事件发生
        // 保护 input ByteBuffer 不会重置和状态的可见性
        // 应该是这样
        synchronized (lock) {
            input.clear();
            int n = socket.read(input);
            if (inputIsComplete(n)) {

                // 读取完毕后将后续的处理交给
                state = PROCESSING;
                workPool.execute(new Processer());
            }
        }
    }

    class Processer implements Runnable {
        @Override
        public void run() {
            processAndHandOff();
        }
    }

    private void processAndHandOff() {
        synchronized (lock) {
            try {
                process();
            } catch (EOFException e) {
                // 直接关闭连接
                try {
                    selectionKey.channel().close();
                } catch (IOException e1) {}
                return;
            }

            // 最后的发送还是交给 SingleReactor 线程处理
            state = SENDING;
            selectionKey.interestOps(SelectionKey.OP_WRITE);

            // 这里需要唤醒 Selector，因为当把处理交给 workpool 时，SingleReactor 线程已经阻塞在 select() 方法了， 注意
            // 此时该通道感兴趣的事件还是 OP_READ，这里将通道感兴趣的事件改为 OP_WRITE，如果不唤醒的话，就只能在
            // 下次select 返回时才能有响应了，当然了也可以在 select 方法上设置超时
            selectionKey.selector().wakeup();
        }
    }
}
