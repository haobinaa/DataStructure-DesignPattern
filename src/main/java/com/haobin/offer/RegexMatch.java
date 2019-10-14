package com.haobin.offer;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author: HaoBin
 * @create: 2019/10/11 9:57
 * @description: 正则表达式匹配
 * <p>
 * 题目描述:
 * 请实现一个函数用来匹配包括 '.' 和 '*' 的正则表达式。
 * 模式中的字符 '.' 表示任意一个字符，而 '*' 表示它前面的字符可以出现任意次（包含 0 次）。
 * <p>
 * 匹配是指字符串的所有字符匹配整个模式。例如:
 * 字符串 "aaa" 与模式 "a.a" 和 "ab*ac*a" 匹配，但是与 "aa.a" 和 "ab*a" 均不匹配
 * <p>
 * <p>
 * <p>
 * 解题思路：
 * <p>
 * 字符串s对于字符匹配模式的parttern中字符c，只有三种情况:
 * 1 c=='.'
 * 2 c=='*
 * 3 c==其他字符
 * <p>
 * c == '.'时，可以匹配任意字符，可以直接跳过，对于第三种情况，只要s中字符对应的c中字符相等即可
 * 对于 c == '*' 这种情况， 代表可以匹配0个或多个前面的字符，处理方式如下:
 * (1) 每次从p中拿出一共字符与s中比较， 如果该字符的后续不是 * , 那么直接与s对应的字符比较，如果匹配成功，各自后移一位
 * (2) 如果后续是 *, 分成两种情况。 如果*表示0，只需要跳过p中的两个字符继续与s比较，如果*代表多个，那么要将s后移一位继续比较
 **/
public class RegexMatch {

    public static void main(String[] args) {
        char[] str = {'a', 'b', 'b', 'c'};
        char[] parttern = {'a', 'b', '*', 'c'};
        System.out.println(match1(str, parttern));
        System.out.println(match(str, parttern));
    }


    /**
     * 递归解法
     * s: a b b c
     * p: a b * c
     * <p>
     * <p>
     * (1) p[0]不为*， 那么直接与s[0]比较，  p[0] == s[0]， 继续匹配下一个字符
     * (2) p[1]为b，但后面字符为 *，进入特殊判断流程
     * (2.1) p直接跳过b*, 与s进行匹配， 此时p的游标指向字符c， 与s[1]不匹配
     * (2.2) s游标往后一位， s[2]==p[1], 继续比较下位， s的子串 c 与p的子串 b*c 比较
     * (3) 与之前一样，先跳过b*， 此时s[3] == p[3], 匹配成功
     * (4) 两个字符串均到达结尾，判断通过
     */
    public static boolean match1(char[] str, char[] parttern) {
        if (parttern.length <= 0) {
            return str.length <= 0;
        }
        // 如果第一个字符相等，或者pattern第一个字符是. 比较下一个字符
        boolean match = (str.length > 0 && (str[0] == parttern[0]) || parttern[0] == '.');
        if (parttern.length > 1 && parttern[1] == '*') {
            // 如果是*, 直接跳过pattern两个字符
            // 或者比较str的下一个字符
            return match1(str, Arrays.copyOfRange(parttern, 2, parttern.length))
                    || (match && match1(Arrays.copyOfRange(str, 1, str.length), parttern));
        } else {
            return match
                    && match1(Arrays.copyOfRange(str, 1, str.length), Arrays.copyOfRange(parttern, 1, parttern.length));
        }
    }

    /**
     * 动态规划解法
     *
     */
    public static boolean match(char[] str, char[] pattern) {

        int m = str.length, n = pattern.length;
        boolean[][] dp = new boolean[m + 1][n + 1];

        dp[0][0] = true;
        for (int i = 1; i <= n; i++)
            if (pattern[i - 1] == '*')
                dp[0][i] = dp[0][i - 2];

        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++)
                if (str[i - 1] == pattern[j - 1] || pattern[j - 1] == '.')
                    dp[i][j] = dp[i - 1][j - 1];
                else if (pattern[j - 1] == '*')
                    if (pattern[j - 2] == str[i - 1] || pattern[j - 2] == '.') {
                        dp[i][j] |= dp[i][j - 1];
                        dp[i][j] |= dp[i - 1][j];
                        dp[i][j] |= dp[i][j - 2];
                    } else
                        dp[i][j] = dp[i][j - 2];

        return dp[m][n];
    }
}
