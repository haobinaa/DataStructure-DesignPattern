package com.haobin.bigdata.wordcount;

/**
 * @author: HaoBin
 * @create: 2019/11/11 17:08
 * @description: 统计大文件中单词出现的频率
 *
 *
 * 功能实现:
 * 1. 根据一个英文文档小文件生成大文件
 * 2. 查询大文件中出现的不同单词
 * 3. 统计单词出现的次数
 * 4. 按首字母A-Z顺序输出对此和对应出现的次数
 *
 *
 * 实现思路:
 * 1. 生成超过 1G 的大文件
 * 2. 将大文件切割成多个小文件
 * 3. 产生多个子线程对每个小文件进行单词数据统计
 * 4. 汇总子线程的统计数目
 * 5. 按字母顺序输出单词和数目
 **/
public class WordCount {


    public static void main(String[] args) {

    }
}
