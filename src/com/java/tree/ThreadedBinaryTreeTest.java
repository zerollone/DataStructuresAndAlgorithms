package com.java.tree;

/**
 * 线索化二叉树
 */
public class ThreadedBinaryTreeTest {
    public static void main(String[] args) {
        Node root = new Node(1, "tom");
        Node node2 = new Node(3, "java");
        Node node3 = new Node(6, "python");
        Node node4 = new Node(8, "c++");
        Node node5 = new Node(10, "php");
        Node node6 = new Node(14, "c#");

        root.setLeft(node2);
        root.setRight(node3);
        node2.setLeft(node4);
        node2.setRight(node5);
        node3.setLeft(node6);

        ThreadedBinaryTree threadedBinaryTree = new ThreadedBinaryTree();
        threadedBinaryTree.setRoot(root);
        threadedBinaryTree.threadedNodes(root);

//        System.out.println("============ 线索化后 =============");
//        Node left = node5.getLeft();
//        Node right = node5.getRight();
//        System.out.println("10号节点的前驱节点是：" + left);
//        System.out.println("10号节点的后继节点是：" + right);

        System.out.println("============ 遍历线索化二叉树 =============");
        threadedBinaryTree.threadedList();

    }
}

/**
 * 定义 ThreadedBinaryTree，实现了线索化功能的二叉树
 */
class ThreadedBinaryTree {
    private Node root;

    // 为了实现线索化，创建一个指向当前节点的前驱节点的指针。在递归进行线索化时，pre保留前一个节点
    private Node pre = null;

    public void setRoot(Node root) {
        this.root = root;
    }

    // 编写对二叉树进行中序线索化的方法，node 表示当前需要线索化的节点
    public void threadedNodes(Node node) {
        // 如果 node == null，不能线索化
        if (node == null) {
            return;
        }

        // 1、先线索化左子树
        threadedNodes(node.getLeft());

        // 2、再线索化当前节点

        // 处理当前节点的前驱节点
        if (node.getLeft() == null) {
            node.setLeft(pre);      // 当前节点的左指针指向前驱节点
            node.setLeftType(1);    // 修改当前节点的左指针类型
        }

        // 处理后继节点
        if (pre != null && pre.getRight() == null) {
            pre.setRight(node);
            pre.setRightType(1);
        }
        //  让当前节点是下一个节点的前驱节点
        pre = node;

        // 3、最后线索化右子树
        threadedNodes(node.getRight());

    }

    // 遍历 线索化二叉树的方法
    public void threadedList() {
        Node node = root;       // 定义一个变量，存储当前遍历的节点，从root开始
        while (node != null) {
            // 循环找到 leftType == 1 的节点，该节点就是当前二叉树的第一个节点，后面随着遍历而变化
            // 当 leftType == 1时，说明当前节点按照线索化来处理节点
            while (node.getLeftType() == 0) {
                node = node.getLeft();
            }
            System.out.println(node);

            // 如果当前节点的右指针指向的是后继节点，则直接打印
            while (node.getRightType() == 1) {
                node = node.getRight();
                System.out.println(node);
            }
            // 替换遍历节点
            node = node.getRight();
        }
    }
}

/**
 * 创建 Node 结点
 */
class Node {
    private int no;
    private String name;
    private Node left;
    private Node right;

    // 1、如果 leftType == 0 表示指向的是左子树，如果是 1 则表示指向前驱节点
    // 2、如果 rightType == 0 表示指向的是右子树，如果是 1 则表示指向后继节点
    private int leftType;
    private int rightType;

    public Node(int no, String name) {
        this.no = no;
        this.name = name;
    }

    public int getLeftType() {
        return leftType;
    }

    public void setLeftType(int leftType) {
        this.leftType = leftType;
    }

    public int getRightType() {
        return rightType;
    }

    public void setRightType(int rightType) {
        this.rightType = rightType;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                '}';
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
    
}