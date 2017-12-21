package io.haobin.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: HaoBin
 * @Date 2017/12/15 9:19
 */
public class MyArrayList {
    public static void main(String[] args) {
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");
        for (String item : list) {
            if ("".equals(item)) {
                list.remove(item);
                List<String> tempList = list;
            }
        }

        for (String item : list) {
            System.out.println(item);
        }
    }
}
