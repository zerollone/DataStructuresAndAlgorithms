package com.java.tree;

public class ArrBinaryTreeTest {
    public static void main(String[] args) {
        int[] arr = {1,2,3,4,5,6,7};
        ArrBinaryTree arrBinaryTree = new ArrBinaryTree(arr);
        arrBinaryTree.preOrder();
    }
}

class ArrBinaryTree{
    private int[] arr;  // 存储数据节点的数组

    public ArrBinaryTree(int[] arr){
        this.arr = arr;
    }

    // 重载 preOrder 方法
    public void preOrder(){
        this.preOrder(0);
    }

    /**
     * 实现顺序存储二叉树的前序遍历
     */
    public void preOrder(int index){
        // 如果数组为空，或者 arr.length == 0，则直接返回
        if(arr == null || arr.length == 0){
            System.out.println("数组为空，不能实现二叉树的前序遍历");
        }
        System.out.println(arr[index]); // 输出当前元素
        // 向左递归遍历
        if ((index * 2 + 1) < arr.length){
            preOrder(2 * index + 1);
        }
        // 向右递归遍历
        if ((index * 2 + 2) < arr.length){
            preOrder(2 * index + 2);
        }
    }
}
