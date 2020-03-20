package com.haobin.codeBlock.writefileio;

import java.io.File;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

/**
 * @Author HaoBin
 * @Create 2020/3/20 9:34
 * @Description: 文件io实践
 **/
public class FileIOPractice {


    public static void main(String[] args) throws Exception {
        mappedByteBufferWrite();
    }

    /**
     * FileChannel 读写
     */
    public static void fileChannelReadWrite() throws Exception {
        FileChannel fileChannel = new RandomAccessFile(new File("db.data"), "rw").getChannel();
        // 写
        byte[] data = new byte[1024 * 4];
        long position = 1024L;
        // 指定 position 写入 4KB 数据
        fileChannel.write(ByteBuffer.wrap(data), position);
        // 直接向当前文件指针写入 4KB 数据
        fileChannel.write(ByteBuffer.wrap(data));
        fileChannel.write(ByteBuffer.wrap(data));
        System.out.println(fileChannel.size()/1024);

        // 读
        ByteBuffer buffer = ByteBuffer.allocate(1024 * 4);
        fileChannel.read(buffer, position);
        fileChannel.read(buffer);
    }

    public static void mappedByteBufferWrite() throws Exception {
        RandomAccessFile rac = new RandomAccessFile(new File("db.data"), "rw");
        int _KB = 1024;
        /**
         * MMAP 映射文件， 这里映射的是堆外内存
         * MMAP 有两个重要方法:
         * 1. load, 加载文件到内存。mmap 映射的时候，os并没有直接读取文件，是通过缺页异常读磁盘
         * 2. force， 强制刷盘。如果不强制刷盘，操作系统也会不定时刷新(pageCache)
         *
         * MMAP 只有针对大文件的操作才有意义(一般一次1.5G左右)， mmap 的过程很耗费系统资源
         */
        MappedByteBuffer mappedByteBuffer = rac.getChannel().map(MapMode.READ_WRITE,0,  8*_KB);
        byte[] data = new byte[1024];
        data = "hello world".getBytes();
        // 写入
        mappedByteBuffer.put(data);
        // 读
        mappedByteBuffer.get(data);

    }
}
