package com.java.sort;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {
        int[] arr = {8, 4, 5, 7, 1, 3, 6, 2};
        int[] temp = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, temp);
        System.out.println(Arrays.toString(arr));

        System.out.println("---------测试归并排序算法运行时间--------");
        int num = 800000;
        int[] arrs = new int[num];
        for (int i = 0; i < arrs.length; i++) {
            arrs[i] = (int) (Math.random() * num * 100);
        }
        long start = System.currentTimeMillis();
        int[] temps = new int[num];
        mergeSort(arrs, 0, arrs.length - 1, temps);
        long end = System.currentTimeMillis();
        System.out.println(num + "个数的归并排序算法时间：" + (end - start) + " ms");
    }

    public static void mergeSort(int[] arr, int left, int right, int[] temp) {
        if (left < right) {
            int mid = (left + right) / 2;
            // 向左递归进行分解
            mergeSort(arr, left, mid, temp);
            // 向右递归进行分解
            mergeSort(arr, mid + 1, right, temp);
            // 进行合并
            mergeSort_unite(arr, left, mid, right, temp);
        }
    }

    /**
     * 合并
     *
     * @param arr   需要排序的原始数组
     * @param left  左边有序序列的初始索引
     * @param mid   中间索引
     * @param right 右边索引
     * @param temp  临时数组
     */
    public static void mergeSort_unite(int[] arr, int left, int mid, int right, int[] temp) {
        int i = left;       // 初始化 i，左边有序序列的初始索引
        int j = mid + 1;    // 初始化 j，右边有序序列的初始索引
        int t = 0;          // temp 数组的当前索引值

        // 1、将左右两边有序的数据按照规则填充到 temp 数组中，直到有一边处理完毕为止
        while (i <= mid && j <= right) {
            // 左边的数据小于右边的数据，将左边的数据填充到 temp 数组中
            if (arr[i] <= arr[j]) {
                temp[t] = arr[i];
                t++;
                i++;
            } else { // 右边的数据小于左边的数据，将右边的数据填充到 temp 数组中
                temp[t] = arr[j];
                t++;
                j++;
            }
        }

        // 2、左边有剩余的数据，将其全部填充到temp数组
        while (i <= mid) {
            temp[t] = arr[i];
            t++;
            i++;
        }

        // 3、右边有剩余的数据，将其全部填充到temp数组
        while (j <= right) {
            temp[t] = arr[j];
            t++;
            j++;
        }

        // 4、将 temp 数组拷贝到 arr 数组，注意，每一轮会根据数据的多少进行拷贝，并不是每次都是全部数据进行拷贝
        t = 0;
        int tempLeft = left;
        while (tempLeft <= right) {
            arr[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }

    }
}
