package com.java.sort;

import java.util.Arrays;

/**
 * 希尔排序
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        shellSort_change(arr);
        System.out.println(Arrays.toString(arr));
    }

    // 希尔排序使用交换法
    public static void shellSort_change(int[] arr){
         int temp = 0;
         // 此for循环表示希尔排序需要的轮次
         for (int gap = arr.length / 2; gap > 0; gap /= 2){
             // 此for循环表示需要循环 gap 组
             for (int i = gap; i < arr.length; i++){
                 // 此循环表示遍历各组中所有的元素
                 for (int j = i - gap; j >= 0; j-= gap){
                     if (arr[j] > arr[j + gap]){
                         temp = arr[j];
                         arr[j] = arr[j + gap];
                         arr[j + gap] = temp;
                     }
                 }
             }
         }
    }
}
