package com.haobin.leetcode.string;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author HaoBin
 * @Create 2020/1/20 16:41
 * @Description: 字母组合
 *
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。
 * 给出数字到字母的映射与电话按键相同(图片在：https://leetcode-cn.com/problems/letter-combinations-of-a-phone-number/)
 * 注意 1 不对应任何字母。
 *
 *
 * 示例:
 * 输入："23"
 * 输出：["ad", "ae", "af", "bd", "be", "bf", "cd", "ce", "cf"]
 **/
public class LetterCombinations {

    // 数字和字母的映射数组， 2 => "abc"
    String[] letterMap = {" ","*","abc","def","ghi","jkl","mno","pqrs","tuv","wxyz"};
    List<String> res = new ArrayList<>();


    public List<String> letterCombinations(String digits) {
        if (digits == null || digits.length() == 0) {
            return new ArrayList<>();
        }
        iterString(digits, "", 0);
        return res;
    }

    private void iterString(String str, String letter, int index) {
        // 递归终止条件， index位置到了字符串终止位置
        if (index == str.length()) {
            res.add(letter);
            return;
        }
        char c = str.charAt(index);
        // 隐式转换成数字
        int pos = c - '0';
        String mapString = letterMap[pos];
        for (int i = 0; i < mapString.length(); i++) {
            // 递归调用
            iterString(str, letter + mapString.charAt(i), index+1);
        }
    }

    public static void main(String[] args) {
        LetterCombinations obj = new LetterCombinations();
        String s = "23";
        System.out.println(Arrays.toString(obj.letterCombinations(s).toArray()));
    }
}
