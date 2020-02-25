package com.haobin.leetcode.dfs;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author HaoBin
 * @Create 2020/2/24 15:14
 * @Description: 单词拆分
 *
 *
 * 给定一个非空字符串 s 和一个包含非空单词列表的字典 wordDict，判定 s 是否可以被空格拆分为一个或多个在字典中出现的单词。
 *
 * 说明：
 *
 * 拆分时可以重复使用字典中的单词。
 * 你可以假设字典中没有重复的单词。
 * 示例 1：
 *
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以被拆分成 "leet code"。
 * 示例 2：
 *
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以被拆分成 "apple pen apple"。
 *      注意你可以重复使用字典中的单词。
 * 示例 3：
 *
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 **/
public class WordBreak {

    /**
     *
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        return checkWord(s, new HashSet<>(wordDict), 0);
    }


    /**
     * 暴力解法
     * 检查字典单词中每一个单词的可能前缀，如果在字典中出现过，那么去掉这个前缀后剩余部分回归调用
     * 同时，如果某次函数调用中发现整个字符串都已经被拆分且在字典中出现过了，函数就返回 true
     */
    private boolean checkWord(String s, Set<String> wordDict, int start) {
        // 已经到了最后一位
        if (start == s.length()) {
            return true;
        }
        for (int end = start+1; end <= s.length(); end++) {
            // 如果字典中包含这个单词，就去掉这个部分后再次递归
            if (wordDict.contains(s.substring(start, end)) && checkWord(s, wordDict, end)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkWordMemory(String s, Set<String> wordDict, int start, Boolean[] mem) {
        if (start == s.length()) {
            return true;
        }
        if (mem[start] != null) {
            return mem[start];
        }
        for (int end = start+1; end <= s.length() ; end++) {
            if (wordDict.contains(s.substring(start, end)) && checkWordMemory(s, wordDict, end, mem)) {
                mem[start] = true;
                return true;
            }
        }
        mem[start] = false;
        return false;
    }


}
