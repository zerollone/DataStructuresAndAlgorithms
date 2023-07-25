package com.java.algorithm;

import java.util.Arrays;

public class KMP {

    public static void main(String[] args) {
        String str1 = "你好吗，abc123形象好，形象好的厉害呢";
        String str2 = "形象好的";
        System.out.println(violenceMatch(str1, str2));

        int[] next = kemNext(str2);
        System.out.println(Arrays.toString(next));
        System.out.println("index=" + kmpSearch(str1,str2,next));
    }

    /**
     *
     * @param str1  被查找的字符串
     * @param str2  需要查找的字符串
     * @param next  查找字符串的部分匹配表
     * @return  查找位置
     */
    public static int kmpSearch(String str1, String str2, int[] next){
        for (int i = 0, j = 0; i < str1.length(); i++){
            while (j > 0 && str1.charAt(i) != str2.charAt(j)){
                j = next[j];
            }

            if (str1.charAt(i) == str2.charAt(j)){
                j++;
            }
            if (j == str2.length()){
                return i - j + 1;
            }
        }
        return -1;
    }

    /**
     * 获取到一个字符串的部分匹配值表
     * @param dest
     * @return
     */
    public static int[] kemNext(String dest){
        // 创建 next 数组保存部分匹配值
        int[] next = new int[dest.length()];
        next[0] = 0;    // 如果字符串是长度为 1，则部分匹配值就是 0
        for (int i = 1, j = 0; i < dest.length(); i++){
            // 当 dest.charAt(i) != dest.charAt(j)，从 next[j-1] 中获取新的 j，直到 dest.charAt(i) == dest.charAt(j) 成立才退出，这是 KMP 算法的核心点
            while (j > 0 && dest.charAt(i) != dest.charAt(j)){
                j = next[j-1];
            }
            // 满足此条件，部分匹配值 +1
            if (dest.charAt(i) == dest.charAt(j)){
                j++;
            }
            next[i] = j;
        }
        return next;
    }

    /**
     * 暴力匹配算法
     */
    public static int violenceMatch(String str1, String str2){
        char[] s1 = str1.toCharArray();
        char[] s2 = str2.toCharArray();

        int s1Len = s1.length;
        int s2Len = s2.length;

        int i = 0;
        int j = 0;

        while (i < s1Len && j < s2Len){
            if (s1[i] == s2[j]){
                i++;
                j++;
            } else {
                i = i - (j - 1);
                j = 0;
            }
        }

        // 判断是否匹配成功
        if (j == s2Len){
            return i - j;
        }else {
            return -1;
        }
    }
}
