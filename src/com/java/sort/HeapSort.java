package com.java.sort;

import java.util.Arrays;

public class HeapSort {
    public static void main(String[] args) {
        int[] arr = {4, 6, 8, 5, 9, -1, 56, 40, 111, 1};
        heapSort(arr);
        System.out.println(Arrays.toString(arr));

        System.out.println("---------测试堆排序算法运行时间--------");
        int num = 8000000;
        int[] arrs = new int[num];
        for (int i = 0; i < arrs.length; i++) {
            arrs[i] = (int) (Math.random() * num * 100);
        }
        long start = System.currentTimeMillis();
        int[] temps = new int[num];
        heapSort(arrs);
        long end = System.currentTimeMillis();
        System.out.println(num + "个数的堆排序算法时间：" + (end - start) + " ms");
    }

    /**
     * 堆排序的方法实现
     */
    public static void heapSort(int[] arr) {
        // 将无序序列构建成一个推，根据升序变成大顶堆
        for (int i = arr.length / 2 - 1; i >= 0; i--) {
            adjustHeap(arr, i, arr.length);
        }

        // 将堆顶元素与末尾元素进行交换，将最大元素沉到数组末端
        // 重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行直到整个序列有序
        int temp = 0;
        for (int j = arr.length - 1; j > 0; j--) {
            temp = arr[j];
            arr[j] = arr[0];
            arr[0] = temp;
            adjustHeap(arr, 0, j);
        }
    }

    /**
     * 将一个数组（顺序存储二叉树）修改成一个大顶堆二叉树
     */
    /**
     * 将一个数组（顺序存储二叉树）修改成一个大顶堆二叉树
     *
     * @param arr    待调整的数组
     * @param i      表示非叶子节点在数组中的索引，通过改变 i 的值来实现大顶堆或小顶堆
     * @param length 表示对多少个元素进行调整排序，length 在逐渐减少
     */
    public static void adjustHeap(int[] arr, int i, int length) {
        // 取出当前元素的值，保存在临时变量
        int temp = arr[i];

        // k = i * 2 + 1; 表示 k 是 i 的左子节点
        for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {
            if (k + 1 < length && arr[k] < arr[k + 1]) {
                // 表示左子节点小于右子节点，切换到最大值的子节点位置
                k++;
            }
            if (arr[k] > temp) {     // 子节点大于父节点进行如下操作
                arr[i] = arr[k];    // 将子节点的值赋给当前节点的值
                i = k;              // 将 i 指向 k，继续进行循环
            } else {
                break;
            }
        }
        // 当 for 循环结束后，已经将以 i 为 父节点的树的最大值放在了最顶部，形成了局部大顶堆
        arr[i] = temp;
    }
}
