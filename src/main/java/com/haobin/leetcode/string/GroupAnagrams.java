package com.haobin.leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Author HaoBin
 * @Create 2020/2/18 16:03
 * @Description: 字母异位词分组
 *
 * 给定一个字符串数组，将字母异位词组合在一起。字母异位词指字母相同，但排列不同的字符串。
 *
 * 示例:
 *
 * 输入: ["eat", "tea", "tan", "ate", "nat", "bat"],
 * 输出:
 * [
 *   ["ate","eat","tea"],
 *   ["nat","tan"],
 *   ["bat"]
 * ]
 *
 **/
public class GroupAnagrams {


    /**
     * 暴力法
     * 遍历每个字符串分别与其他字符串比较
     * 使用一个used数组，保证每个相同的字符串只比较一次
     */
    public List<List<String>> groupAnagrams1(String[] strs) {
        List<List<String>> ans = new ArrayList<>();
        boolean[] used = new boolean[strs.length];
        for (int i = 0; i < strs.length; i++) {
            List<String> temp = null;
            if (!used[i]) {
                temp = new ArrayList<String>();
                temp.add(strs[i]);
                //两两比较判断字符串是否符合
                for (int j = i + 1; j < strs.length; j++) {
                    if (equals(strs[i], strs[j])) {
                        used[j] = true;
                        temp.add(strs[j]);
                    }
                }
            }
            if (temp != null) {
                ans.add(temp);

            }
        }
        return ans;
    }

    /**
     * 比较两个字符串是否相同
     * 用hashmap记录1个字符串每个字符的出现的次数，遍历第二个字符串每个字符出现就减一。最后判断每个key对应的value是否为0
     */
    private boolean equals(String string1, String string2) {
        Map<Character, Integer> hash = new HashMap<>();
        //记录第一个字符串每个字符出现的次数，进行累加
        for (int i = 0; i < string1.length(); i++) {
            if (hash.containsKey(string1.charAt(i))) {
                hash.put(string1.charAt(i), hash.get(string1.charAt(i)) + 1);
            } else {
                hash.put(string1.charAt(i), 1);
            }
        }
        //记录第一个字符串每个字符出现的次数，将之前的每次减 1
        for (int i = 0; i < string2.length(); i++) {
            if (hash.containsKey(string2.charAt(i))) {
                hash.put(string2.charAt(i), hash.get(string2.charAt(i)) - 1);
            } else {
                return false;
            }
        }
        //判断每个字符的次数是不是 0 ，不是的话直接返回 false
        Set<Character> set = hash.keySet();
        for (char c : set) {
            if (hash.get(c) != 0) {
                return false;
            }
        }
        return true;
    }


    /**
     * 排序法
     * 将每个字符串排序，这样异位词都会有同样的key
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        HashMap<String, List<String>> hash = new HashMap<>();
        for (int i = 0; i < strs.length; i++) {
            char[] s_arr = strs[i].toCharArray();
            //排序
            Arrays.sort(s_arr);
            //映射到 key
            String key = String.valueOf(s_arr);
            //添加到对应的类中
            if (hash.containsKey(key)) {
                hash.get(key).add(strs[i]);
            } else {
                List<String> temp = new ArrayList<String>();
                temp.add(strs[i]);
                hash.put(key, temp);
            }

        }
        return new ArrayList<List<String>>(hash.values());
    }


}
