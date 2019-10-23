package com.haobin.codeBlock.IOModel;

import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;

/**
 * @author: HaoBin
 * @create: 2019/10/23 15:16
 * @description: 单线程reactor handler 处理器
 * <p>
 * 在单线程reactor中， IO读写以及业务处理均由reactor线程完成
 **/
public class BasicHandler implements Runnable {

    private static final int INPUT_SIZE = 1024;
    private static final int OUTPUT_SIZE = 1024;

    public SocketChannel socket;
    public SelectionKey selectionKey;
    ByteBuffer input = ByteBuffer.allocate(INPUT_SIZE);
    ByteBuffer output = ByteBuffer.allocate(OUTPUT_SIZE);

    // 定义服务的逻辑状态
    static final int READING = 0;
    static final int SENDING = 1;
    static final int CLOSED = 2;
    int state = READING;


    public BasicHandler(SocketChannel socket) {
        this.socket = socket;
    }

    public BasicHandler(Selector selector, SocketChannel sc) throws IOException {
        socket = sc;
        sc.configureBlocking(false); // 非阻塞
        // 注册到selector上，但不订阅任何事件
        selectionKey = socket.register(selector, 0);
        // 绑定要处理事件
        selectionKey.interestOps(SelectionKey.OP_READ);
        // 处理事件对应的程序
        selectionKey.attach(this);
        // 唤醒selector
        selector.wakeup();
    }

    @Override
    public void run() {
        try {
            if (state == READING)
                read(); // 此时通道已经准备好读取字节
            else if (state == SENDING)
                send(); // 此时通道已经准备好写入字节
        } catch (IOException ex) {
            // 关闭连接
            try {
                selectionKey.channel().close();
            } catch (IOException ignore) {
            }
        }
    }

    protected void read() throws IOException {
        input.clear(); // 清空接收缓冲区
        int n = socket.read(input);
        if (inputIsComplete(n)) {// 如果读取了完整的数据
            process();
            // 待发送的数据已经放入发送缓冲区中

            // 更改服务的逻辑状态以及要处理的事件类型
            selectionKey.interestOps(SelectionKey.OP_WRITE);
        }
    }

    // 缓存每次读取的内容
    StringBuilder request = new StringBuilder();
    /**
     * 当读取到 \r\n 时表示结束
     * @param bytes 读取的字节数，-1 通常是连接被关闭，0 非阻塞模式可能返回
     * @throws IOException
     */
    protected boolean inputIsComplete(int bytes) throws IOException {
        if (bytes > 0) {
            input.flip(); // 切换成读取模式
            while (input.hasRemaining()) {
                byte ch = input.get();

                if (ch == 3) { // ctrl+c 关闭连接
                    state = CLOSED;
                    return true;
                } else if (ch == '\r') { // continue
                } else if (ch == '\n') {
                    // 读取到了 \r\n 读取结束
                    state = SENDING;
                    return true;
                } else {
                    request.append((char)ch);
                }
            }
        } else if (bytes == -1) {
            // -1 客户端关闭了连接
            throw new EOFException();
        } else {} // bytes == 0 继续读取
        return false;
    }

    /**
     * 根据业务处理结果，判断如何响应
     * @throws EOFException 用户输入 ctrl+c 主动关闭
     */
    protected void process() throws EOFException {
        if (state == CLOSED) {
            throw new EOFException();
        } else if (state == SENDING) {
            String requestContent = request.toString(); // 请求内容
            byte[] response = requestContent.getBytes(StandardCharsets.UTF_8);
            output.put(response);
        }
    }

    protected void send() throws IOException {
        int written = -1;
        output.flip();// 切换到读取模式，判断是否有数据要发送
        if (output.hasRemaining()) {
            written = socket.write(output);
        }

        // 检查连接是否处理完毕，是否断开连接
        if (outputIsComplete(written)) {
            selectionKey.channel().close();
        } else {
            // 否则继续读取
            state = READING;
            // 把提示发到界面
            socket.write(ByteBuffer.wrap("\r\nreactor> ".getBytes()));
            selectionKey.interestOps(SelectionKey.OP_READ);
        }

    }

    /**
     * 当用户输入了一个空行，表示连接可以关闭了
     */
    protected boolean outputIsComplete(int written) {
        if (written <= 0) {
            // 用户只敲了个回车， 断开连接
            return true;
        }

        // 清空旧数据，接着处理后续的请求
        output.clear();
        request.delete(0, request.length());
        return false;
    }
}
