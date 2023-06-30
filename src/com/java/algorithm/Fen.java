package com.java.algorithm;

/**
 * 分治算法实现汉诺塔
 */
public class Fen {

    public static void main(String[] args) {
        hanoiTower(5, 'A', 'B', 'C');
    }

    public static void hanoiTower(int num, char a, char b, char c) {
        // 如果只有一个盘
        if (num == 1){
            System.out.println("第1个盘从 " + a + "->" + c);
        } else {
            // 如果有多个盘，则总是可以将其看出只有两个盘，一个盘是最下面的盘，一个的上面的所有盘
            // 1. 先把最上面的所有盘 A -> B,
            hanoiTower(num - 1, a, c, b);
            // 2. 把最下面的盘 A -> C
            System.out.println("第" + num + "个盘从 " + a + "->" + c);
            // 3. 把B塔的所有盘 B -> c
            hanoiTower(num - 1, b, a, c);
        }
    }
}
