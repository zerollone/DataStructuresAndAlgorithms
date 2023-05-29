package com.java.search;

/**
 * 线性查找，暴力穷举
 */
public class SeqSearch {

    public static void main(String[] args) {
        int[] arr = {5, 6, 89, 3, 45, 12, 88, 43, 65, 34};
        int search = seqSearch(arr, 6);
        if (search != -1) {
            System.out.println("查找到该数，其下标为：" + search);
        } else {
            System.out.println("不存在此数");
        }
    }

    public static int seqSearch(int[] arr, int index) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == index) {
                return i;
            }
        }
        return -1;
    }
}
