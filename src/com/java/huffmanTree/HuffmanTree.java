package com.java.huffmanTree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 创建赫夫曼树
 */
public class HuffmanTree {
    public static void main(String[] args) {
        int arr[] = {13, 7, 8, 3, 29, 6, 1}; // 67 29 38 15 7 8 23 10 4 1 3 6 13
        Node huffmanTree = createHuffmanTree(arr);
        preOrder(huffmanTree);
    }

    // 前序遍历
    public static void preOrder(Node node) {
        if (node != null) {
            node.preOrder();
        } else {
            System.out.println("空树，不能进行遍历！！！");
        }
    }

    // 创建赫夫曼树的方法
    public static Node createHuffmanTree(int[] arr) {
        List<Node> nodes = new ArrayList<Node>();
        // 遍历 arr 数组，将 arr 的每个元素构成一个 Node，并将 Node 放入到 ArrayList 中
        for (int value : arr) {
            nodes.add(new Node(value));
        }

        // 使用循环来创建赫夫曼树
        for (; nodes.size() > 1; ) {
            // 排序 从小到大
            Collections.sort(nodes);

            // 取出权值最小的节点
            Node leftNode = nodes.get(0);
            // 取出权值第二小的节点
            Node rightNode = nodes.get(1);
            // 构建一颗新的二叉树
            Node parent = new Node(leftNode.value + rightNode.value);
            parent.left = leftNode;
            parent.right = rightNode;
            // 从 nodes 中删除最小的两个节点，并将新的父节点添加进 nodes
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        // 返回赫夫曼树的根节点
        return nodes.get(0);
    }
}

/**
 * 创建节点类
 * 为了让 Node 对象持续 Collections 集合排序
 */
class Node implements Comparable<Node> {
    int value;  // 节点权值
    Node left;  // 指向左子节点
    Node right; // 指向右子节点

    public Node(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node{" +
                "value=" + value +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        // 表示从小到大排序
        return this.value - o.value;
    }

    // 前序遍历
    public void preOrder() {
//        System.out.println(this);
        // 只输出叶子节点
        if (this.right == null && this.left == null){
            System.out.println(this);
        }
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}






