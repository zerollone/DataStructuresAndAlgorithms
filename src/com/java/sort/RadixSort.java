package com.java.sort;

import java.util.Arrays;

/**
 * 基数排序
 */
public class RadixSort {

    public static void main(String[] args) {
        int[] arr = {53, 3, 542, 4567, 748, 14, 214};
        radixSort(arr);
        System.out.println(Arrays.toString(arr));

        System.out.println("---------测试基数排序算法运行时间--------");
        int num = 8000000;
        int[] arrs = new int[num];
        for (int i = 0; i < arrs.length; i++) {
            arrs[i] = (int) (Math.random() * num * 100);
        }
        long start = System.currentTimeMillis();
        int[] temps = new int[num];
        radixSort(arrs);
        long end = System.currentTimeMillis();
        System.out.println(num + "个数的基数排序算法时间：" + (end - start) + " ms");
    }

    public static void radixSort(int[] arr) {
        /**
         * 1、得到数组中最大的数
         */
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        /**
         * 2、得到最大数的位数
         */
        int maxLength = (max + "").length();

        /**
         * 3、定义一个二维数组，用于表示 10 个桶，每一个桶是一个一维数组
         *   为了防止数据溢出，每一个一维数组（桶）的大小定为 arr.length
         */
        int[][] bucket = new int[10][arr.length];

        /**
         * 4、为了记录每个桶中存放多少个数据，定义一个一维数组来记录各个桶的放入数据的个数
         */
        int[] bucketElementCounts = new int[10];

        /**
         * 5、从个位数循环至最高位来实现基数排序
         */
        for (int i = 0, n = 1; i < maxLength; i++, n *= 10) {
            // 针对每个元素对应的位数进行排序
            for (int j = 0; j < arr.length; j++) {
                // 取出每个元素每个轮次对应位数的值，个十百千...
                int digitOfElement = arr[j] / n % 10;
                // 将数据放入对应的桶中
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = arr[j];
                bucketElementCounts[digitOfElement]++;
            }

            int index = 0; // 用于arr数组下标的增加

            // 遍历每一个桶，并将桶中的数据放入到原数组中
            for (int k = 0; k < bucketElementCounts.length; k++) {
                // 如果桶中有数据则放入到原数组中
                if (bucketElementCounts[k] != 0) {
                    // 循环第k个桶，将数据放入原数组中
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        // 取出每个桶中的元素依次放入到 arr 中
                        arr[index++] = bucket[k][l];
                    }
                }
                // 遍历完并取出每一个桶中的数据后，需要将该桶中数据个数置为 0，以便于下一轮桶中数据个数的统计
                bucketElementCounts[k] = 0;
            }
        }
    }
}







