package com.haobin.datastructure.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: HaoBin
 * @Date 2017/12/14 18:26
 */
public class PrintUtil {
    public static void printArray(int[] arr) {
        List<String> strings = new ArrayList<String>();
        for(int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
    }
}
