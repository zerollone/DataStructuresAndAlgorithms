package com.java.search;

/**
 * 插值查找算法
 */
public class InsertValueSearch {

    public static void main(String[] args) {
        int[] arr = new int[100];
        for (int i = 0; i < 100; i++) {
            arr[i] = i + 1;
        }

        int result = insertValueSearch(arr, 0, arr.length - 1, 100);
        System.out.println("查找结果：" + result);
    }

    public static int insertValueSearch(int[] arr, int left, int right, int index) {
        if (left > right || index > arr[arr.length - 1] || index < arr[0]) {
            return -1;
        }

        int mid = left + (right - left) * (index - arr[left]) / (arr[right] - arr[left]);
        int midIndex = arr[mid];

        if (index > midIndex) {
            return insertValueSearch(arr, mid + 1, right, index);
        } else if (index < midIndex) {
            return insertValueSearch(arr, left, mid - 1, index);
        } else {
            return mid;
        }
    }
}
