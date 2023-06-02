package com.java.search;

import java.util.Arrays;

/**
 * 斐波那契查找
 */
public class FibonacciSearch {

    public static int maxSize = 20;

    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        System.out.println("下标是：" + fibonacciSearch(arr, 10));
    }

    // 使用非递归方法得到一个斐波那契数列
    public static int[] fibonacci() {
        int[] f = new int[maxSize];
        f[0] = 1;
        f[1] = 1;
        for (int i = 2; i < maxSize; i++) {
            f[i] = f[i - 1] + f[i - 2];
        }
        return f;
    }

    /**
     * 斐波那契查找算法
     *
     * @param arr 查找的数组
     * @param key 查找的值
     * @return 返回下标
     */
    public static int fibonacciSearch(int[] arr, int key) {
        int low = 0;
        int high = arr.length - 1;
        int k = 0;              // 表示斐波那契分割数组的下标
        int mid = 0;            // 存放 mid 值
        int f[] = fibonacci();  // 获取到斐波那契数列
        // 获取到斐波那契分割数值的下标
        while (high > f[k] - 1) {
            k++;
        }

        // f[k] 的值可能大于 arr 数组的长度，因此需要使用Arrays类，构造一个新的数组，并指向 arr[]，不足的部分使用0填充
        int[] temp = Arrays.copyOf(arr, f[k]);
        // 将 arr 数组使用 0 填充的部分修改为 arr 数组中最后一个值
        for (int i = high + 1; i < temp.length; i++) {
            temp[i] = arr[high];
        }

        // 使用 while 来循环处理，找到 key
        while (low <= high) {
            mid = low + f[k - 1] - 1;
            if (key < temp[mid]) {
                high = mid - 1;
                k--;
            } else if (key > temp[mid]) {
                low = mid + 1;
                k -= 2;
            } else {
                if (mid <= high) {
                    return mid;
                } else {
                    return high;
                }
            }
        }
        return -1;
    }
}










