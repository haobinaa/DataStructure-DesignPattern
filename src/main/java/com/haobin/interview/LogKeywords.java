/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.interview;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 * 根据关键字读取日志文件，按出现次数排序打印关键字
 *
 * @author HaoBin
 * @version $Id: LogKeywords.java, v0.1 2018/12/19 9:59 HaoBin 
 */
public class LogKeywords {

    public static void main(String[] args) throws Exception{
        File file = new File(LogKeywords.class.getClassLoader().getResource("project.log").getFile());
        String[] keyword = {"springframework", "data"};
        long start = System.currentTimeMillis();
        BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
        // 需要查找的关键字
        System.out.println(Arrays.toString(keyword));
        //该map的key为关键字，value为关键字出现次数
        Map<String, Integer> map = new HashMap<String, Integer>();

        String result = null;
        //按行读取日志文件，对每一行分别计算关键字次数，累加进map
        while((result = bufferedReader.readLine()) != null){
            //由于split方法有一个特性，就是会忽略待分割对象结尾的一个或多个分割符，所以如果分割符（关键词）位于一行结尾，就无法进行统计
            //在此处我们给每行文本结尾添加一个自定义结束符"`"（注意，结束符不能干扰到关键词！）
            result += "`";
            for(int i = 0; i < keyword.length; i++ ){
                //通过split方法实现搜索关键字次数功能
                int count = (result.split(keyword[i])).length - 1;
                map.put(keyword[i], map.get(keyword[i]) == null ? count : (map.get(keyword[i]) + count));
            }
        }

        //将上面map的每个Entry存入list
        ArrayList<Entry<String, Integer>> entryArrayList = new ArrayList<Map.Entry<String, Integer>>();

        for (Map.Entry<String, Integer> m : map.entrySet()){
            entryArrayList.add(m);
        }

        //自定义list的比较器，根据value从大到小排列Entry元素
        Collections.sort(entryArrayList, new Comparator<Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o2.getValue() - o1.getValue();
            }
        });

        for (Map.Entry<String, Integer> m : entryArrayList){
            System.out.println(m.getKey() + ":" + m.getValue());
        }

        long end = System.currentTimeMillis();
        System.out.println("搜索耗时：" + (end - start) + " ms");
    }
}