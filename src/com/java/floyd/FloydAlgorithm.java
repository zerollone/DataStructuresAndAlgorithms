package com.java.floyd;

import java.lang.reflect.Array;
import java.util.Arrays;

public class FloydAlgorithm {
    private static final int M = 65535;

    public static void main(String[] args) {
        char[] vertex = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int matrix[][] = {
                {0, 5, 7, M, M, M, 2},
                {5, 0, M, 9, M, M, 3},
                {7, M, 0, M, 8, M, M},
                {M, 9, M, 0, M, 4, M},
                {M, M, 8, M, 0, 5, 4},
                {M, M, M, 4, 5, 0, 6},
                {2, 3, M, M, 4, 6, 0}
        };

        Graph graph = new Graph(vertex, matrix);
        graph.floyd();
        graph.show();
    }
}

class Graph {
    private char[] vertex;  // 存放顶点的数组
    private int[][] dis;    // 保存从各个顶点出发到其他顶点的距离，最后的结果也保存在该数组中
    private int[][] pre;    // 保存到达目的顶点的前驱顶点

    /**
     * 构造器
     *
     * @param vertex 顶点数组
     * @param matrix 邻接矩阵
     */
    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[vertex.length][vertex.length];

        // 对 pre 数组进行初始化，存放的就是前驱节点的下标
        for (int i = 0; i < vertex.length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    public void show() {
        // 显示输出 pre 数组
        System.out.println("========  pre 数组  =========");
        for (int[] line : this.pre) {
            System.out.println(Arrays.toString(line));
        }
        System.out.println("========  dis 数组  =========");
        // 显示输出 dis 数组
        for (int[] line : dis) {
            System.out.println(Arrays.toString(line));
        }
    }

    public void floyd(){
        // 变量保存距离
        int len = 0;
        // 从中间顶点遍历，k 就是中间顶点的下标
        for (int k = 0; k < dis.length; k++){
            // 从 i 顶点开始出发
            for (int i = 0; i < dis.length; i++){
                // 从 j 顶点开始出发
                for (int j = 0; j < dis.length; j++){
                    // 求出从 i 顶点出发，经过中间 k 顶点，到达 j 顶点的距离
                    len = dis[i][k] + dis[k][j];
                    // 如果len小于 dis[i][j]
                    if (len < dis[i][j]){
                        // 更新距离
                        dis[i][j] = len;
                        // 更新前驱节点
                        pre[i][j] = pre[k][j];
                    }
                }
            }
        }
    }
}











