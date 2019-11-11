package com.haobin.codeBlock;

import org.springframework.util.StopWatch;

/**
 * @author: HaoBin
 * @create: 2019/11/11 17:20
 * @description: java 写大文件对比
 **/
public class WriteBigFileCompare {

    public static void main(String[] args) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start("test-task");
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e) {}
        stopWatch.stop();
    }
}
