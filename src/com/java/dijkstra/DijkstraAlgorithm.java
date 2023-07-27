package com.java.dijkstra;

import java.util.Arrays;

public class DijkstraAlgorithm {

    private static final int M = 65535;

    public static void main(String[] args) {
        char[] vertex = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int matrix[][] = {
                {M, 5, 7, M, M, M, 2},
                {5, M, M, 9, M, M, 3},
                {7, M, M, M, 8, M, M},
                {M, 9, M, M, M, 4, M},
                {M, M, 8, M, M, 5, 4},
                {M, M, M, 4, 5, M, 6},
                {2, 3, M, M, 4, 6, M}
        };
        Graph graph = new Graph(vertex, matrix);
        graph.show();
        graph.dijkstra(6);
        graph.showDijkstra();
    }

}

class VisitedVertex {
    // 记录各个顶点是否访问过，1 表示访问过，0 表示未访问，将会进行动态更新
    public int[] already_arr;
    // 每个下标对应的值为前一个顶点的下标，将会进行动态更新
    public int[] pre_visited;
    // 记录出发顶点到其他所有顶点的距离，
    public int[] dis;

    /**
     * 构造器
     *
     * @param length 表示顶点的个数
     * @param index  出发顶点对应的下标
     */
    public VisitedVertex(int length, int index) {
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];
        // 初始化 dis 数组
        Arrays.fill(dis, 65535);
        // 设置出发顶点的距离为0
        this.dis[index] = 0;
        this.already_arr[index] = 1;
    }

    /**
     * 判断 index 这个顶点是否被访问过
     *
     * @param index
     * @return
     */
    public boolean in(int index) {
        return already_arr[index] == 1;
    }

    /**
     * 更新出发顶点到 index 顶点的距离
     *
     * @param index
     * @param value
     */
    public void updateDis(int index, int value) {
        dis[index] = value;
    }

    /**
     * 更新 pre 顶点的前驱顶点为 index 顶点
     *
     * @param pre
     * @param index
     */
    public void updatePre(int pre, int index) {
        pre_visited[pre] = index;
    }

    /**
     * 返回出发顶点到index顶点的距离
     *
     * @param index
     * @return
     */
    public int getDis(int index) {
        return dis[index];
    }

    /**
     * 继续选择并返回新的访问顶点，比如 G 访问完后，将 A 作为新的访问顶点
     *
     * @return
     */
    public int updateArr() {
        int min = 65535;
        int index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if (already_arr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        // 更新 index 顶点被访问
        already_arr[index] = 1;
        return index;
    }

    public void show(){
        System.out.println("=================================");
        System.out.println(Arrays.toString(already_arr));
        System.out.println(Arrays.toString(pre_visited));
        System.out.println(Arrays.toString(dis));
    }
}

class Graph {
    private char[] vertex;
    private int[][] matrix;

    private VisitedVertex vv;

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    // 显示图
    public void show() {
        for (int[] line : matrix) {
            System.out.println(Arrays.toString(line));
        }
    }

    // 显示最后结果
    public void showDijkstra(){
        vv.show();
    }

    /**
     * 迪杰斯特拉算法实现
     *
     * @param index
     */
    public void dijkstra(int index) {
        vv = new VisitedVertex(vertex.length, index);
        update(index);
        for (int i = 1; i < vertex.length; i++) {
            // 选择并返回新的访问节点
            index = vv.updateArr();
            update(index);
        }
    }

    /**
     * 更新 index 下标顶点到周围顶点的距离和周围顶点的前驱顶点
     *
     * @param index
     */
    public void update(int index) {
        int len = 0;
        // 遍历邻接矩阵 index 所在行的数据
        for (int i = 0; i < matrix[index].length; i++) {
            // 出发顶点到index顶点的距离 + index 顶点到 i 顶点的距离之后
            len = vv.getDis(index) + matrix[index][i];
            // 如果 i 顶点没有被访问过，并 len 小于出发顶点到 i 顶点的距离
            if (!vv.in(i) && len < vv.getDis(i)) {
                // 更新 i 顶点的前驱为 index 顶点
                vv.updatePre(i, index);
                // 更新出发顶点到 i 顶点的距离
                vv.updateDis(i, len);
            }
        }
    }
}





