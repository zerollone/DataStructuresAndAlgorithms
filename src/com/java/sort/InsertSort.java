package com.java.sort;

import java.util.Arrays;

/**
 * 插入排序
 */
public class InsertSort {

    public static void main(String[] args) {
        int[] arr = {101,34,119,1,-1,89};
        insertSort(arr);
        System.out.println(Arrays.toString(arr));

        System.out.println("---------测试插入排序算法运行时间--------");
        int num = 80000;
        int[] arrs = new int[num];
        for (int i = 0; i < arrs.length; i++){
            arrs[i] = (int) (Math.random() * num * 100);
        }
        long start = System.currentTimeMillis();
        insertSort(arrs);
        long end = System.currentTimeMillis();
        System.out.println(num + "个数的插入排序算法时间：" + (end - start) + " ms");
    }

    public static void insertSort(int[] arr){
        for (int i = 1; i < arr.length; i++){
            int value = arr[i];     // 定义待插入的数
            int index = i - 1;      // 定义待插入的数的前一个值的索引

            /**
             * 1、index >= 0 保证在给 value 值找插入位置时，不会越界
             * 2、value < arr[index] 表示待插入的数还没有找到插入的位置
             * 3、arr[index + 1] = arr[index] 表示将大于待插入的数进行后移
             */
            while (index >= 0 && value < arr[index]){
                arr[index + 1] = arr[index];
                index--;
            }
            /**
             * 当退出 while 循环后，说明插入的位置已经找到，插入的位置为 index + 1
             */
            arr[index + 1] = value;
        }
    }
}
