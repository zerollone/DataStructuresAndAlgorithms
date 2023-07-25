package com.java.prim;

import java.util.Arrays;

/**
 * 普利姆算法
 */
public class PrimAlgorithm {

    public static void main(String[] args) {
        char[] data = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int vertex = data.length;
        // 创建邻接矩阵
        int[][] weight = new int[][]{
                {100, 5, 7, 100, 100, 100, 2},
                {5, 100, 100, 9, 100, 100, 3},
                {7, 100, 100, 100, 8, 100, 100},
                {100, 9, 100, 100, 100, 4, 100},
                {100, 100, 8, 100, 100, 5, 4},
                {100, 100, 100, 4, 5, 100, 6},
                {2, 3, 100, 100, 4, 6, 100}
        };
        // 创建 MGraph 对象
        MGraph graph = new MGraph(vertex);
        // 创建 最小生成树 对象
        MinTree minTree = new MinTree();
        // 生产图
        minTree.createGraph(graph, vertex, data, weight);
        // 查看图
        minTree.showGraph(graph);
        System.out.println("路径");
        minTree.prim(graph, 0);
    }
}

// 创建最小生成树
class MinTree {
    /**
     * 创建图的邻接矩阵
     *
     * @param graph  图对象
     * @param vertex  图对应的顶点个数
     * @param data   图的各个顶点的值
     * @param weight 图的邻接矩阵
     */
    public void createGraph(MGraph graph, int vertex, char[] data, int[][] weight) {
        int i, j;
        for (i = 0; i < vertex; i++) {
            graph.data[i] = data[i];
            for (j = 0; j < vertex; j++) {
                graph.weight[i][j] = weight[i][j];
            }
        }
    }

    /**
     * 显示图的邻接矩阵
     */
    public void showGraph(MGraph graph) {
        for (int[] line : graph.weight) {
            System.out.println(Arrays.toString(line));
        }
    }

    /**
     * 编写prim算法，得到最小生成树
     *
     * @param graph
     * @param v
     */
    public void prim(MGraph graph, int v) {
        // 标记顶点是否被访问过
        int visited[] = new int[graph.vertex];
        // 将当前节点标记为已访问
        visited[v] = 1;
        // h1 和 h2 记录两个顶点的下标
        int h1 = 1;
        int h2 = 2;
        // 临时值
        int minWeight = 100;
        // k 从 1 开始，因为根据普利姆算法有 n 个节点有 n-1 条边
        for (int k = 1; k < graph.vertex; k++) {
            // i 表示被访问过的节点
            for (int i = 0; i < graph.vertex; i++) {
                // j 表示还没有被访问的节点
                for (int j = 0; j < graph.vertex; j++) {
                    // 循环判断和 i 节点相邻节点权值最小的 j 节点
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        // 替换 minWeight
                        minWeight = graph.weight[i][j];
                        h1 = i;
                        h2 = j;
                    }
                }
            }
            System.out.println("<" + graph.data[h1] + ", " + graph.data[h2] + ">, 权值：" + graph.weight[h1][h2]);
            visited[h2] = 1;
            minWeight = 100;
        }
    }
}

class MGraph {
    int vertex;      // 表示图的节点个数
    char[] data;    // 表示存放节点数据
    int[][] weight; // 存放边，邻接矩阵

    public MGraph(int vertex) {
        this.vertex = vertex;
        data = new char[vertex];
        weight = new int[vertex][vertex];
    }
}


















