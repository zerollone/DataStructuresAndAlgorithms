package com.java.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;

public class Graph {
    private ArrayList<String> vertexList;   // 存储顶点集合
    private int[][] edges;                  // 存储图对应的零阶矩阵
    private int numOfEdges;                 // 表示边的数目
    private boolean[] isVisited;            // 已访问过的数据

    public static void main(String[] args) {
        int n = 5;
        String[] vertexs = {"A", "B", "C", "D", "E"};
        Graph graph = new Graph(n);
        // 添加顶点
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }

        // 添加边
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);

        // 显示邻接矩阵
        graph.showGraph();

//        System.out.println("深度优先");
//        graph.dfs();

        System.out.println("广度优先");
        graph.bfs();
    }

    /**
     * 构造器
     */
    public Graph(int n) {
        // 初始化矩阵
        edges = new int[n][n];
        vertexList = new ArrayList<String>(n);
        numOfEdges = 0;
        isVisited = new boolean[n];

    }

    /**
     * 插入节点
     */
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    // 图中常用方法

    /**
     * 返回节点的个数
     */
    public int getNumOfVertex() {
        return vertexList.size();
    }

    /**
     * 放回边的个数
     */
    public int getNumOfEdges() {
        return numOfEdges;
    }

    /**
     * 返回第 i 个节点对应的数据
     */
    public String getValueByIndex(int i) {
        return vertexList.get(i);
    }

    /**
     * 返回两个节点连线的权值
     */
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    /**
     * 显示图对应的矩阵
     */
    public void showGraph() {
        for (int i = 0; i < edges.length; i++) {
            for (int j = 0; j < edges[i].length; j++) {
                System.out.print(edges[i][j] + " ");
            }
            System.out.println();
        }

//        for (int[] arr : edges){
//            System.out.println(Arrays.toString(arr));
//        }
    }

    /**
     * 得到第一个邻接节点的下标
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 根据前一个邻接节点的下标来获取下一个邻接节点
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 深度优先遍历算法
     */
    private void dfs(boolean[] isVisited, int i) {
        // 访问第一个节点
        System.out.print(getValueByIndex(i) + "->");
        // 将该节点设置为以访问
        isVisited[i] = true;
        // 查找节点 i 的第一个邻接节点 w
        int w = getFirstNeighbor(i);

        // 当 w 存在时
        while (w != -1) {
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            // 如果 w 节点已经被访问过
            w = getNextNeighbor(i, w);
        }

    }

    // 对 dfs 进行重载，遍历所有的节点，并进行 dfs
    public void dfs() {
        for (int i = 0; i < getNumOfVertex(); i++) {
            if (!isVisited[i]) {
                dfs(isVisited, i);
            }
        }
    }

    /**
     * 对节点进行广度优先遍历
     */
    private void bfs(boolean[] isVisited, int i) {
        int u;  // 表示队列的头节点对应下标
        int w;  // 邻接节点 w
        // 队列，记录节点访问的顺序
        LinkedList<Object> queue = new LinkedList<>();
        // 访问节点，输出节点信息
        System.out.print(getValueByIndex(i) + "->");
        // 标记为已访问
        isVisited[i] = true;
        // 将节点加入队列
        queue.addLast(i);

        while (!queue.isEmpty()) {
            // 取出队列的头节点下标
            u = (Integer) queue.removeFirst();
            // 得到第一个邻接节点的下标 w
            w = getFirstNeighbor(u);
            // 当找到 w 时
            while (w != -1) {
                // 判断是否访问过
                if (!isVisited[w]) {
                    System.out.print(getValueByIndex(w) + "->");
                    // 标记已经访问
                    isVisited[w] = true;
                    // 入队列
                    queue.addLast(w);
                }
                // 当已被访问过后，以 u 为前驱节点，找到 w 的下一个邻接节点
                w = getNextNeighbor(u,w);
            }
        }
    }

    /**
     * 遍历所有的节点，进行广度优先搜索
     */
    public void bfs(){
        for (int i = 0; i < getNumOfVertex(); i++){
            if (!isVisited[i]){
                bfs(isVisited, i);
            }
        }
    }
}












