package com.java.algorithm;

import java.util.Arrays;

/**
 * 使用动态规划算法解决背包问题
 */
public class DongTaiGuiHua {
    public static void main(String[] args) {
        int[] w = {1, 4, 3};                // 物品的重量
        int[] val = {1500, 3000, 2000};     // 物品的价值
        int m = 4;                          // 背包的容量
        int n = val.length;                 // 物品的个数

        // 创建二维数组
        int[][] v = new int[n + 1][m + 1];
        // 表示存放的物品
        int[][] path = new int[n + 1][m + 1];

        // 动态规划处理
        for (int i = 1; i < v.length; i++) {
            for (int j = 1; j < v[0].length; j++) {
                if (w[i - 1] > j) {
                    v[i][j] = v[i - 1][j];
                } else {
//                    v[i][j] = Math.max(v[i-1][j], val[i-1]+v[i-1][j-w[i-1]]);
                    int max1 = v[i - 1][j];
                    int max2 = val[i - 1] + v[i - 1][j - w[i - 1]];
                    if (max1 > max2) {
                        v[i][j] = max1;
                    } else {
                        v[i][j] = max2;
                        path[i][j] = 1;
                    }
                }
            }
        }

        // 打印数组
        for (int[] arr : v) {
            System.out.println(Arrays.toString(arr));
        }

        // 遍历得到那些商品放入背包
        System.out.println("=============");
        for (int[] arr : path) {
            System.out.println(Arrays.toString(arr));
        }
        int i = path.length - 1;   // 3
        int j = path[0].length - 1; // 4
        while (i > 0 && j > 0) {
            if (path[i][j] == 1) {
                System.out.println("第" + i + "个商品放入背包");
                j -= w[i - 1];
            }
            i--;
        }
    }

}
