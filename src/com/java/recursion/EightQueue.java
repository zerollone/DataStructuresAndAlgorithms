package com.java.recursion;

/**
 * 1. 第一个皇后先放第一行第一列
 * 2. 第二个皇后放在第二行第一列、然后判断是否可行， 如果不行，继续放在第二列、第三列、依次把所有列都放完，直到找到一个合适的位置
 * 3. 继续第三个皇后，还是第一列、第二列……直到第8个皇后也能放在一个不冲突的位置，这算是找到了一个正确解
 * 4. 当得到一个正确解时，在栈回退到上一个栈时，就会开始回溯，即将第一个皇后放到第一列的所有正确解全部得到.
 * 5. 然后回头继续第一个皇后放第二列，后面继续循环执行 1,2,3,4的步骤
 *
 * 说明：理论上应该创建一个二维数组来表示棋盘，但是实际上可以通过算法，用一个一维数组即可解决问题.
 *      arr[8] = {0 , 4, 7, 5, 2, 6, 1, 3}表示一个正确放置八皇后的解。对应 arr 的下标表示第几行，也表示第几个皇后，
 *      arr[i] = val , 该式子表示第 i+1 个皇后放在第 i+1 行的第 val+1 列。
 *      例如：arr[2] = 7，表示第三个皇后应该放在第三行第八列。
 */
public class EightQueue {

    // 定义max 表示皇后的个数，使用一维数组保存皇后放置的位置
    int max = 8;
    int[] arr = new int[max];
    static int amount = 0;      // 统计有多少种解法

    public static void main(String[] args) {
        EightQueue eightQueue = new EightQueue();
        eightQueue.check(0);    // 从0（即第一个皇后）开始递归到第八个皇后
    }

    /**
     * 输出所有皇后摆放的位置
     */
    public void show(){
        amount++;
        System.out.printf("第" + amount + "中解法：");
        for (int i = 0; i < arr.length; i++){
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    /**
     * 检查当前皇后摆放位置是否和前面的皇后位置冲突
     */
    public boolean judge(int n){
        for (int i = 0; i < n; i++){
            // 前一个式子判断是否在同一列，后一个式子判断两个点是否在一条斜线上，利用斜率判断
            if (arr[i] == arr[n] || Math.abs(n - i) == Math.abs(arr[n] - arr[i])){
                return false;
            }
        }
        return true;
    }

    /**
     * 放置第 n个皇后
     */
    public void check(int n){
        if (n == max){
            // 此时所有皇后都已经放好
            show();
            return;
        }

        // 依次放入皇后，并判断皇后位置是否冲突
        for (int i = 0; i < max; i++){
            arr[n] = i;     // 将当前皇后的位置放在第一列，并依次进行循环，直到放到最后一列
            if (judge(n)){
                // 判断位置是否和前面放置的位置的冲突，如果不冲突，就寻找下一个皇后的位置，即进行递归操作
                check(n + 1);
            }
            // 如果冲突，则执行for循环，将当前皇后位置向后移
        }
    }


}
