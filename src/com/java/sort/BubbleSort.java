package com.java.sort;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 冒泡排序
 */
public class BubbleSort {

    public static void main(String[] args) {
        int[] arr = {3, 9, -1, 10, -2};

        int temp = 0;   // 临时变量
        for (int i = 1; i < arr.length; i++){
            for (int j = 0; j < arr.length - i; j++){
                if (arr[j] > arr[j + 1]){
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("排序后：" + Arrays.toString(arr));
        optimizeBubble(arr);
        System.out.println("优化排序后：" + Arrays.toString(arr));

        System.out.println("---------测试冒泡算法运行时间--------");
        int num = 800;
        int[] arrs = new int[num];
        for (int i = 0; i < arrs.length; i++){
            arrs[i] = (int) (Math.random() * num);
        }
        long start = System.currentTimeMillis();
        optimizeBubble(arrs);
        long end = System.currentTimeMillis();
        System.out.println(num + "个数的冒泡算法排序时间：" + (end - start) + " ms");
    }

    public static void optimizeBubble(int[] arr){
        int temp = 0;   // 临时变量
        boolean flag = false; // 标识变量，表示是否进行过交换
        for (int i = 1; i < arr.length; i++){
            for (int j = 0; j < arr.length - i; j++){
                if (arr[j] > arr[j + 1]){
                    temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    flag = true;
                }
            }
            if (!flag){         // 在一趟排序中，一次交换都没有发生过
                break;
            }else {
                flag = true;    // 重置 flag，进行下一个循环的判断，如果没有效果将失效
            }
        }
    }
}
