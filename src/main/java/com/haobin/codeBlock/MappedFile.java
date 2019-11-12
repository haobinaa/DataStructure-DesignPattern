package com.haobin.codeBlock;

import java.io.File;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author: HaoBin
 * @create: 2019/11/11 17:38
 * @description: 定时定量刷新内存文件到磁盘
 **/
public class MappedFile {

    private String fileName;

    private String filePath;

    private File file;

    private MappedByteBuffer mappedByteBuffer;


    private FileChannel fileChannel;
}
