package com.java.algorithm;

/**
 * 二叉查找算法，非递归实现
 */
public class BinarySearch {

    public static void main(String[] args) {
        int[] arr = {1,3,8,10,11,67,100};
        int i = binarySearch(arr, 1);
        System.out.println("index = " + i);
    }

    public static int binarySearch(int[] arr, int target){
        int left = 0;
        int right = arr.length - 1;
        int mid;
        while (left <= right){
            mid = (left + right) / 2;
            if (arr[mid] == target){
                return mid;
            } else if (arr[mid] > target){
                right = mid -1;
            } else {
                left = mid + 1;
            }
        }
        return -1;
    }
}
