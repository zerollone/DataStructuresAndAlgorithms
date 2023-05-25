package com.java.recursion;

public class MiGong {

    public static void main(String[] args) {
        // 创建二维数组，模拟迷宫
        int[][] arr  = new int[8][7];
        // 使用 1 表示墙，0 表示空白
        // 设置上下墙体
        for (int i = 0 ; i < 7; i++){
            arr[0][i] = 1;
            arr[7][i] = 1;
        }
        // 设置左右墙体
        for (int i = 1; i < 7; i++){
            arr[i][0] = 1;
            arr[i][6] = 1;
        }
        // 设置内部墙体
        arr[3][1] = 1;
        arr[3][2] = 1;

        arr[4][2] = 1;
        arr[4][4] = 1;
        arr[5][2] = 1;
        arr[5][3] = 1;
        arr[5][4 ] = 1;
        System.out.println("-----迷宫地图样式-----");
        show(arr);

        setWay(arr, 1, 1);

        System.out.println("-----迷宫地图路线-----");
        show(arr);
    }

    /**
     * 打印二维数组
     * @param arr
     */
    public static void show(int[][] arr){
        for (int i = 0; i < arr.length; i++){
            for (int j = 0; j < arr[0].length; j++){
                System.out.print(arr[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * 使用递归回溯来寻找路径
     *  说明：
     *      1、map表示地图，i和j表示初始位置
     *      2、如果找到 arr[6][5] 位置，则说明正确路线找到
     *      3、数组中 0 表示没有走过，1 表示墙，2表示此路可以走，3表示该点已经走过，但是走不通
     *      4、设置行走策略：先下后右在上，最后走左边，如果此点走不通，进行回溯
     * @param arr   表示地图
     * @param i     表示出发时横坐标
     * @param j     表示出发时纵坐标
     * @return  找到路返回true，否则返回false
     */
    public static boolean setWay(int[][] arr, int i, int j){
        if (arr[6][5] == 2){
            // 表示找到终点
            return true;
        } else {
            // 表示该点还未走过
            if (arr[i][j] == 0){
                // 按照策略进行找路：下 -> 右 -> 上 -> 左
                arr[i][j] = 2;  // 假设该点可以走
                if (setWay(arr, i + 1, j)){         // 向下走
                    return true;
                } else if (setWay(arr, i + 1, j)){
                    return true;
                } else if (setWay(arr, i, j + 1)){
                    return true;
                } else if (setWay(arr, i - 1, j)){
                    return true;
                } else if (setWay(arr, i, j - 1)){
                    return true;
                } else {
                    // 表示此点是死点，走不通
                    arr[i][j] = 3;
                    return false;
                }
            } else{
                // 此时表示点位的值是 1、2、3，无论是那种情况都不需要再判断是否可走
                return false;
            }
        }
    }
}
