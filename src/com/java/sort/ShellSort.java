package com.java.sort;

import java.util.Arrays;

/**
 * 希尔排序
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = {8, 9, 1, 7, 2, 3, 5, 4, 6, 0};
        // shellSort_change(arr);
        shellSort_insert(arr);
        System.out.println(Arrays.toString(arr));

        System.out.println("---------测试希尔排序算法运行时间--------");
        int num = 8000000;
        int[] arrs = new int[num];
        for (int i = 0; i < arrs.length; i++) {
            arrs[i] = (int) (Math.random() * num * 100);
        }
        long start = System.currentTimeMillis();
        // shellSort_change(arrs);     // 交换法
        shellSort_insert(arrs);     // 移位法
        long end = System.currentTimeMillis();
        System.out.println(num + "个数的希尔排序算法时间：" + (end - start) + " ms");
    }

    // 希尔排序使用交换法
    public static void shellSort_change(int[] arr) {
        int temp = 0;
        // 此for循环表示希尔排序需要的轮次
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 此for循环表示循环每一个元素，
            for (int i = gap; i < arr.length; i++) {
                // 此循环表示分组后遍历各组中所有的元素
                for (int j = i - gap; j >= 0; j -= gap) {
                    // 当前元素与同一分组中后一位元素进行比较
                    if (arr[j] > arr[j + gap]) {
                        temp = arr[j];
                        arr[j] = arr[j + gap];
                        arr[j + gap] = temp;
                    }
                }
            }
        }
    }

    // 希尔排序使用移位法
    public static void shellSort_insert(int[] arr) {
        int value;
        int index;
        for (int gap = arr.length / 2; gap > 0; gap /= 2) {
            // 从gap元素开始，对属于同一组的元素进行直接插入排序
            for (int i = gap; i < arr.length; i++) {
                value = arr[i];
                index = i;
                if (arr[index] < arr[index - gap]) {
                    while (index - gap >= 0 && value < arr[index - gap]) {
                        arr[index] = arr[index - gap];
                        index -= gap;
                    }
                    // 退出循环后，value值即找到位置
                    arr[index] = value;
                }
            }
        }
    }
}
