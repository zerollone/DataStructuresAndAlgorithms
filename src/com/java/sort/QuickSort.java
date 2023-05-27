package com.java.sort;

import java.util.Arrays;

/**
 * 快速排序
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = {-9, 78, 0, 23, -567, 70};
        quickSort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));

        System.out.println("---------测试快速排序算法运行时间--------");
        int num = 8000000;
        int[] arrs = new int[num];
        for (int i = 0; i < arrs.length; i++) {
            arrs[i] = (int) (Math.random() * num * 100);
        }
        long start = System.currentTimeMillis();
        // shellSort_change(arrs);     // 交换法
        quickSort(arrs, 0, arrs.length - 1);     // 移位法
        long end = System.currentTimeMillis();
        System.out.println(num + "个数的快速排序算法时间：" + (end - start) + " ms");
    }

    public static void quickSort(int[] arr, int left, int right) {
        int l = left;                   // 左下标
        int r = right;                  // 右下标
        int pivot = arr[(l + r) / 2];   // 中轴值
        int temp = 0;                   // 临时变量
        /**
         * 左边索引小于右边的索引就一直进行循环
         * while循环的目的是让比 pivot 值小的放在左边，比 pivot 大的放在右边
         */
        while (l < r) {
            // 在 pivot 的左边一直找，找到大于等于 pivot 值才退出
            while (arr[l] < pivot) {
                l++;
            }
            // 在 pivot 的右边一直找，找到小于等于 pivot 值才退出
            while (arr[r] > pivot) {
                r--;
            }
            // 如果 l >= r 则表示数值已经找好
            if (l >= r) {
                break;
            }

            // 寻找到合适的数进行交换，即 pivot 右边的数小于 pivot的值，左边的数大于 pivot 的值
            temp = arr[l];
            arr[l] = arr[r];
            arr[r] = temp;
            // 如果交换完后，发现 arr[l] == pivot，则 r--，进行前移
            if (arr[l] == pivot) {
                r--;
            }
            // 如果交换完后，发现 arr[r] == pivot，则 r++，进行后移
            if (arr[r] == pivot) {
                l++;
            }
        }

        // 如果 l == r，必须 l++，r--，否则会出现栈溢出
        if (l == r) {
            l++;
            r--;
        }
        // 向左递归
        if (left < r) {
            quickSort(arr, left, r);
        }
        // 向右递归
        if (right > l) {
            quickSort(arr, l, right);
        }
    }
}
