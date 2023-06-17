package com.java.tree;

/**
 * 平衡二叉树（AVL树）
 */
public class AVLTreeTest {
    public static void main(String[] args) {
//        int[] arr = {4, 3, 6, 5, 7, 8};   // 测试左旋
//        int[] arr = {10, 12, 8, 9, 7, 6}; // 测试右旋
        int[] arr = {10, 11, 7, 6, 8, 9};   // 测试双旋
        AVLTree avlTree = new AVLTree();
        // 添加节点
        for (int i = 0; i < arr.length; i++) {
            avlTree.add(new Node_AVL(arr[i]));
        }

        // 遍历
        System.out.println("==== 遍历 ====");
        avlTree.infixOrder();

        // 查找树的高度
        System.out.println("==== 树的高度 ====");
        System.out.println("树的高度：" + avlTree.getRoot().height());
        System.out.println("树的左子树的高度：" + avlTree.getRoot().leftHeight());
        System.out.println("树的右子树的高度：" + avlTree.getRoot().rightHeight());
    }
}

/**
 * 创建 AVL 树
 */
class AVLTree {
    private Node_AVL root;

    public Node_AVL getRoot() {
        return root;
    }

    // 添加节点的方法
    public void add(Node_AVL node) {
        if (root == null) {
            root = node;    // root 为空，则直接让root指向node
        } else {
            root.add(node);
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (root != null) {
            root.infixOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

}

/**
 * 创建 Node 节点
 */
class Node_AVL {
    int value;
    Node_AVL left;
    Node_AVL right;

    public Node_AVL(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node_AVL{" +
                "value=" + value +
                '}';
    }

    /**
     * 返回左子树的高度
     */
    public int leftHeight() {
        if (left == null) {
            return 0;
        }
        return left.height();
    }

    /**
     * 返回左子树的高度
     */
    public int rightHeight() {
        if (right == null) {
            return 0;
        }
        return right.height();
    }

    /**
     * 返回以当前节点为根节点的数的高度
     */
    public int height() {
        return Math.max(left == null ? 0 : left.height(), right == null ? 0 : right.height()) + 1;
    }

    /**
     * 左旋转的方法
     */
    private void leftRotate() {
        // 创建新的节点，以当前根节点的值进行创建
        Node_AVL newNode = new Node_AVL(value);
        // 把新的节点的左子树设置成当前节点的左子树
        newNode.left = left;
        // 把新的节点的右子树设置成当前节点的右子树的左子树
        newNode.right = right.left;
        // 把当前节点的值替换成右子节点的值
        value = right.value;
        // 把当前节点的右子树设置成当前节点右子树的右子树
        right = right.right;
        // 把当前节点的左子树设置成新的节点
        left = newNode;
    }

    /**
     * 右旋转
     */
    private void rightRotate() {
        Node_AVL newNode = new Node_AVL(value);
        newNode.right = right;
        newNode.left = left.right;
        value = left.value;
        left = left.left;
        right = newNode;
    }

    /**
     * 递归的方式去添加节点
     */
    public void add(Node_AVL node) {
        if (node == null) {
            return;
        }
        // 判断传入的节点的值和当前子树的根节点的值的关系
        if (node.value < this.value) {
            // 如果当前节点左子节点为null
            if (this.left == null) {
                this.left = node;
            } else {
                this.left.add(node);
            }
        } else {
            // 如果当前节点右子节点为null
            if (this.right == null) {
                this.right = node;
            } else {
                this.right.add(node);
            }
        }

        // 当添加完一个节点后，如果右子树的高度 - 左子树的高度大于 1，则进行左旋转
        if (rightHeight() - leftHeight() > 1) {
            // 如果它的右子树的左子树高度大于它的右子树的右子树的高度，则需要进行双旋，先右旋再左旋
            if (right != null && right.leftHeight() > right.rightHeight()) {
                // 先对右子节点进行右旋转
                right.rightRotate();
                // 再对当前节点进行左旋转
                leftRotate();
            } else {
                leftRotate();
            }
            return;
        }
        // 当添加完一个节点后，如果左子树的高度 - 右子树的高度大于 1，则进行左旋转
        if (leftHeight() - rightHeight() > 1) {
            // 如果它的左子树的右子树高度大于它的左子树的左子树的高度，则需要进行双旋，先左旋再右旋
            if (left != null && left.rightHeight() > left.leftHeight()) {
                // 先对当前节点的左节点进行左旋
                left.leftRotate();
                // 在对当前节点进行右旋
                rightRotate();
            } else {
                // 直接进行右旋
                rightRotate();
            }
            return;
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        if (this.left != null) {
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

}