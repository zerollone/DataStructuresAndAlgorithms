package com.java.sort;

import java.util.Arrays;

public class SelectSort {

    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1, -1, 90, 123};
        selectSort(arr);
        System.out.println("排序后：" + Arrays.toString(arr));

        System.out.println("---------测试选择排序算法运行时间--------");
        int num = 80000;
        int[] arrs = new int[num];
        for (int i = 0; i < arrs.length; i++){
            arrs[i] = (int) (Math.random() * num);
        }
        long start = System.currentTimeMillis();
        selectSort(arrs);
        long end = System.currentTimeMillis();
        System.out.println(num + "个数的冒泡算法排序时间：" + (end - start) + " ms");
    }

    public static void selectSort(int[] arr) {
        int index = 0;
        int min = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            index = i;
            min = arr[i];
            for (int j = i + 1; j < arr.length - 1; j++) {
                if (min > arr[j]) {
                    min = arr[j];
                    index = j;
                }
            }
            if (index != i) {    // 如果本轮最初比较值是最小值，则不进行交换
                arr[index] = arr[i];
                arr[i] = min;
            }
        }
    }
}
