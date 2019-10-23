package com.haobin.codeBlock.IOModel;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author: HaoBin
 * @create: 2019/10/23 8:49
 * @description: 单线程 reactor 模式
 *
 * reactor 和 handler 在一个线程里面
 *
 *
 *#####################################
 * reactor 模型各个组件
 * reactor: 负责响应事件，将事件分发给绑定了该事件的handler
 * handler: 绑定了某类事件的处理器， 负责执行对事件的处理
 * Acceptor: 特殊的Handler， 绑定了 connect 事件， 客户端的 connect 事件会分发给 Acceptor
 **/
public class SingleReactor implements Runnable{

    private Selector selector;
    private ServerSocketChannel serverSocket;

    public SingleReactor(int port) throws Exception {
        selector = Selector.open();
        serverSocket= ServerSocketChannel.open();
        serverSocket.socket().bind(new InetSocketAddress(port));
        // 设置成非阻塞
        serverSocket.configureBlocking(false);
        // 只关注 accept 事件
        SelectionKey selectionKey = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        selectionKey.attach(new Acceptr());
        System.out.println("Listen port:" + port);
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()) {
                selector.select(); // 阻塞至通道就绪
                Set<SelectionKey> selectionKeys = selector.selectedKeys(); // 就绪通道 SelectionKey 集合
                Iterator<SelectionKey> it = selectionKeys.iterator();
                // 遍历就绪事件，分发给对应处理器
                while (it.hasNext()) {
                    SelectionKey selectedKey = it.next();
                    dispatch(selectedKey);
                }
                // 清空就绪通道
                selectionKeys.clear();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void dispatch(SelectionKey k) {
        // 获取 key 对应的处理器
        Runnable r = (Runnable) (k.attachment());
        if (r != null) {
            r.run();
        }
    }

    class Acceptr implements Runnable {
        @Override
        public void run() {
            try {
                // 接收连接，非阻塞模式没有连接直接返回null
                SocketChannel socket = serverSocket.accept();
                if (socket != null) {
                    socket.write(ByteBuffer.wrap("single reactor".getBytes()));
                    System.out.println("Accept and handler - " + socket.socket().getLocalSocketAddress());
                    // handler 处理
                    new BasicHandler(selector, socket);
                }
            } catch (IOException ioex) {
                ioex.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        try {
            Thread th = new Thread(new SingleReactor(10393));
            th.setName("SingleReactor");
            th.start();
            th.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
