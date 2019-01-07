/**
 * BrandBigData.com Inc. Copyright (c) 2018 All Rights Reserved.
 */
package com.haobin.datastructure.algorithm;

/**
 * 字符串匹配算法KMP
 *
 * 有一个字符串"BBC ABCDAB ABCDABCDABDE"，我想知道，里面是否包含另一个字符串"ABCDABD"
 *
 * 参考资料:
 * - http://www.ruanyifeng.com/blog/2013/05/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm.html(阮一峰博客，画图讲解清晰)
 * - https://www.zhihu.com/question/21923021(知乎最高赞答案，讲的很好)
 *
 * 重点为部分匹配表的生成，部分匹配值是前缀和后缀的最长共有元素的长度
 *
 * 　 －　"A"的前缀和后缀都为空集，共有元素的长度为0；
 *
 * 　　－　"AB"的前缀为[A]，后缀为[B]，共有元素的长度为0；
 *
 * 　　－　"ABC"的前缀为[A, AB]，后缀为[BC, C]，共有元素的长度0；
 *
 * 　　－　"ABCD"的前缀为[A, AB, ABC]，后缀为[BCD, CD, D]，共有元素的长度为0；
 *
 * 　　－　"ABCDA"的前缀为[A, AB, ABC, ABCD]，后缀为[BCDA, CDA, DA, A]，共有元素为"A"，长度为1；
 *
 * 　　－　"ABCDAB"的前缀为[A, AB, ABC, ABCD, ABCDA]，后缀为[BCDAB, CDAB, DAB, AB, B]，共有元素为"AB"，长度为2；
 *
 * 　　－　"ABCDABD"的前缀为[A, AB, ABC, ABCD, ABCDA, ABCDAB]，后缀为[BCDABD, CDABD, DABD, ABD, BD, D]，共有元素的长度为0。
 *
 * 那么这个部分匹配表(PMT partion match table) 应该为
 *      A B C D A B D
 *      0 0 0 0 1 2 0
 *
 * 有了部分匹配表之后，发现不匹配后不用从头开始，而是位移 已匹配字符数 - 对应部分匹配值
 * @author HaoBin
 * @version $Id: KMP.java, v0.1 2019/1/7 21:14 HaoBin 
 */
public class KMP {


    void getNext(String pattern, int[] next) {
        int j = 0;
        int k = -1;
        int len = pattern.length();
        next[0] = -1;

        while (j < len - 1) {
            if (k == -1 || pattern.charAt(k) == pattern.charAt(j)) {

                j++;
                k++;
                next[j] = k;
            } else {

                // 比较到第K个字符，说明p[0——k-1]字符串和p[j-k——j-1]字符串相等，而next[k]表示
                // p[0——k-1]的前缀和后缀的最长共有长度，所接下来可以直接比较p[next[k]]和p[j]
                k = next[k];
            }
        }
    }

    int kmp(String s, String pattern) {
        int i = 0;
        int j = 0;
        int slen = s.length();
        int plen = pattern.length();

        int[] next = new int[plen];

        getNext(pattern, next);

        while (i < slen && j < plen) {

            if (s.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            } else {
                if (next[j] == -1) {
                    i++;
                    j = 0;
                } else {
                    j = next[j];
                }

            }

            if (j == plen) {
                // 返回出现的位置
                return i - j;
            }
        }
        // 如不匹配则返回-1
        return -1;
    }

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        KMP kmp = new KMP();
        String str = "abababdafdasabcfdfeaba";
        String pattern = "abc";
        System.out.println(kmp.kmp(str, pattern));
    }


}