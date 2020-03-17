package com.haobin.codeBlock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @Author HaoBin
 * @Create 2020/3/15 11:29
 * @Description: 交替打印abc
 *
 * 用三个线程按顺序循环打印abc三个字母，比如abcabcabc
 **/
public class ThreadABC {

    public static final int THREAD_A = 0;
    public static final int THREAD_B = 1;
    public static final int THREAD_C = 2;

    private int threadCode = 0;

    static class Task implements Runnable {
        private static final ReentrantLock lock = new ReentrantLock();
        private static final Condition condition = lock.newCondition();

        private final ThreadABC threadAbc;
        private final int threadCode;

        public Task(ThreadABC threadAbc, int threadCode) {
            this.threadAbc = threadAbc;
            this.threadCode = threadCode;
        }

        @Override
        public void run() {
            for (int x = 0; x < 1000; x++) {
                runOnce();
            }
        }

        private void runOnce() {
            lock.lock();
            try {
                while (this.threadCode != threadAbc.threadCode) {
                    condition.await();
                }

                if (this.threadCode == THREAD_A) {
                    System.out.print('A');
                } else if (this.threadCode == THREAD_B) {
                    System.out.print('B');
                } else if (this.threadCode == THREAD_C) {
                    System.out.println('C');
                } else {
                    return;
                }
                threadAbc.threadCode = (threadAbc.threadCode + 1) % 3;

                condition.signalAll();

            } catch (InterruptedException ex) {
                ex.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        ThreadABC threadAbc = new ThreadABC();

        Thread threadA = new Thread(new Task(threadAbc, THREAD_A));
        Thread threadB = new Thread(new Task(threadAbc, THREAD_B));
        Thread threadC = new Thread(new Task(threadAbc, THREAD_C));

        threadA.start();
        threadB.start();
        threadC.start();
    }
}
