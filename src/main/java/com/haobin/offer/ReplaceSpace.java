package com.haobin.offer;

/**
 * @author: HaoBin
 * @create: 2019/9/18 10:23
 * @description: 5-替换空格
 * 题目描述: 将一个字符串中的空格替换成 "%20"
 * eg:
 * Input:
 * "A B"
 *
 * Output:
 * "A%20B"
 **/
public class ReplaceSpace {

    /**
     * 思路：题目要求在原字符串上修改，如果采用遍历，则每次都需要后移两个字符，效率很低
     *
     * 改变思路：在字符串尾部填充任意字符，使得字符串的长度等于替换之后的长度。
     * 因为一个空格要替换成三个字符（%20），因此当遍历到一个空格时，需要在尾部填充两个任意字符
     */
    public static void main(String[] args) {
        StringBuffer str = new StringBuffer("A B");
        System.out.println(replaceSpace(str));
    }

    public static String replaceSpace(StringBuffer str) {
        int p1 = str.length() - 1; // p1指向原字符串末尾
        for (int i = 0; i <= p1; i++) {
            if (str.charAt(i) == ' ') {
                str.append("  "); // 如果遇到空格则在尾部填充两个空格(或任意字符)
            }
        }
        int p2 = str.length() - 1; // p2指向填充后的字符的尾部
        // p1,p2从后往前遍历
        while (p1 >= 0 && p2 >p1) {
            char c = str.charAt(p1--);
            if (c == ' ') {
                // 如果遇到空格则p2填充%02(p2是逆序的，所以也要逆序填充)
                str.setCharAt(p2--, '0');
                str.setCharAt(p2--, '2');
                str.setCharAt(p2--, '%');
            } else {
                // 如果不是空格，则将p2填充p1的内容
                str.setCharAt(p2--, c);
            }
        }
        return str.toString();
    }
}
