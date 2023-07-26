package com.java.kruskal;

import java.util.Arrays;

public class KruskalCase {

    private int edgeNum;        // 边的个数
    private char[] vertex;      // 顶点数组
    private int[][] matrix;     // 邻接矩阵
    private static final int maxSum = Integer.MAX_VALUE;

    public static void main(String[] args) {
        char[] vertex = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int matrix[][] = {
                {0, 12, maxSum, maxSum, maxSum, 16, 14},
                {12, 0, 10, maxSum, maxSum, 7, maxSum},
                {maxSum, 10, 0, 3, 5, 6, maxSum},
                {maxSum, maxSum, 3, 0, 4, maxSum, maxSum},
                {maxSum, maxSum, 5, 4, 0, 2, 8},
                {16, 7, 6, maxSum, 2, 0, 9},
                {14, maxSum, maxSum, maxSum, 8, 9, 0}
        };
        KruskalCase kruskalCase = new KruskalCase(vertex, matrix);
        kruskalCase.printf();
        kruskalCase.kruskal();
    }

    // 构造器
    public KruskalCase(char[] vertex, int[][] matrix) {
        // 初始化顶点数和边的个数
        int vLength = vertex.length;
        // 初始化顶点
        this.vertex = new char[vLength];
        for (int i = 0; i < vertex.length; i++) {
            this.vertex[i] = vertex[i];
        }

        // 初始化边
        this.matrix = new int[vLength][vLength];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                this.matrix[i][j] = matrix[i][j];
            }
        }

        // 统计边的个数
        for (int i = 0; i < matrix.length; i++) {
            for (int j = i + 1; j < matrix[0].length; j++) {
                if (matrix[i][j] != maxSum) {
                    edgeNum++;
                }
            }
        }
    }

    // 编写算法
    public void kruskal(){
        int index = 0;
        // 用于保存 已有最小生成树 中的每个顶点在最小生成树中的终点
        int[] ends = new int[edgeNum];
        // 创建结果数组，保存最后的最小生成树
        EData[] result = new EData[vertex.length-1];
        // 获取图中所有边的集合
        EData[] edges = getEdges();
        // 按照边的权值大小进行排序
        sortEdge(edges);
        // 遍历 edges 数组，将边添加到最小生成树中时，判断准备加入的边是否与已有的边形成回路
        for (int i = 0; i < edgeNum; i++){
            // 获取第 i 条边的第一个顶点
            int p1 = getPosition(edges[i].start);
            // 获取第 i 条边的第二个顶点
            int p2 = getPosition(edges[i].end);
            // 获取 p1 顶点在已有的最小生成树中的终点
            int m = getEnd(ends, p1);
            // 获取 p1 顶点在已有的最小生成树中的终点
            int n = getEnd(ends, p2);
            // 判断是否构成回路，等于则为回路
            if (m != n){
                // 不构成回路
                // 设置 m 的终点是 n
                ends[m] = n;
                // 将第 i 条边加入到result数组中
                result[index++] = edges[i];
            }
        }
        System.out.println("====== 最小生成树 =======");
        for (EData eData : result){
            System.out.println(eData);
        }
    }

    // 打印邻接矩阵
    public void printf() {
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = 0; j < this.matrix.length; j++) {
                System.out.printf("%12d", this.matrix[i][j]);
            }
            System.out.println();
        }
    }

    // 对边进行排序
    public void sortEdge(EData[] edges){
        for (int i = 0; i < edges.length - 1; i++){
            for (int j = 0; j < edges.length - 1 - i; j++){
                if (edges[j].weight > edges[j + 1].weight){
                    EData temp = edges[j];
                    edges[j] = edges[j+1];
                    edges[j+1] = temp;
                }
            }
        }
    }

    // 返回顶点的下标
    public int getPosition(char c){
        for (int i = 0; i < this.vertex.length; i++){
            if (this.vertex[i] == c){
                return i;
            }
        }
        return -1;
    }

    // 获取图的所有的边，后续需要进行遍历查找所有的边
    public EData[] getEdges(){
        int index = 0;
        EData[] edges = new EData[edgeNum];
        for (int i = 0; i < this.matrix.length; i++) {
            for (int j = i + 1; j < this.matrix[0].length; j++) {
                if (this.matrix[i][j] != maxSum){
                    edges[index++] = new EData(this.vertex[i], this.vertex[j], this.matrix[i][j]);
                }
            }
        }
        return edges;
    }

    /**
     * 获取下标为 i 顶点其对应终点的下标
     * @param ends  数组是记录各个顶点对于的终点，该数组在遍历过程中逐步形成的
     * @param i     表示传入的顶点对应的下标
     * @return
     */
    private int getEnd(int[] ends, int i){
        while (ends[i] != 0){
            i = ends[i];
        }
        return i;
    }
}

class EData{
    char start; // 边的一个点
    char end;   // 边的另一个点
    int weight; // 边的权值

    public EData(char start, char end, int weight){
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "EData{" +
                "start=" + start +
                ", end=" + end +
                ", weight=" + weight +
                '}';
    }
}

