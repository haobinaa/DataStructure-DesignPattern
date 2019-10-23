package com.haobin.codeBlock.IOModel;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author: HaoBin
 * @create: 2019/10/23 13:52
 * @description: 传统IO模型
 **/
public class ClassicServerLoop implements Runnable {

    private int PORT = 8080;
    public void run() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            while (!Thread.interrupted()) {
                // 这里可以换成线程池
                new Thread(new Handler(serverSocket.accept())).start();
            }
        } catch (IOException ex) {

        }
    }

    static class Handler implements Runnable {
        final Socket socket;
        Handler(Socket s) {
            socket = s;
        }

        public void run() {
            try {
                byte[] input = new byte[1024];
                socket.getInputStream().read(input);
                byte[] output = process(input);
                socket.getOutputStream().write(output);
            }catch (IOException ex) {

            }
        }

        private byte[] process(byte[] data) {
            System.out.println("process data");
            return "finish".getBytes();
        }
    }
}
