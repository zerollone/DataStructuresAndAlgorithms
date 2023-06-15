package com.java.tree;

/**
 * 二叉排序树
 */
public class BinarySortTreeDemo {

    public static void main(String[] args) {
        int[] arr = {7,3,10,12,5,1,9,0};
        BinarySortTree binarySortTree = new BinarySortTree();
        for (int i = 0; i < arr.length; i++) {
            binarySortTree.add(new Node_sort(arr[i]));
        }
        // 中序遍历
        binarySortTree.infixOrder();

        // 测试删除叶子节点
        binarySortTree.delNode(2);
        binarySortTree.delNode(5);
        binarySortTree.delNode(9);
        binarySortTree.delNode(12);
        binarySortTree.delNode(7);
        binarySortTree.delNode(3);
        binarySortTree.delNode(10);
        binarySortTree.delNode(1);
        binarySortTree.delNode(0);
        System.out.println("删除后");
        binarySortTree.infixOrder();
    }
}

/**
 * 创建二叉排序树
 */
class BinarySortTree{
    private Node_sort root;

    // 添加节点的方法
    public void add(Node_sort node){
        if (root == null){
            root = node;    // root 为空，则直接让root指向node
        } else {
            root.add(node);
        }
    }

    // 中序遍历
    public void infixOrder(){
        if (root != null){
            root.infixOrder();
        } else {
            System.out.println("二叉树为空");
        }
    }

    // 查找要删除的节点
    public Node_sort search(int value){
        if (root == null){
            return null;
        } else {
            return root.search(value);
        }
    }

    // 查找要删除的节点的父节点
    public Node_sort searchParent(int value){
        if (root == null){
            return null;
        }else {
            return root.searchParent(value);
        }
    }

    // 返回以 node 为根节点的二叉树的最小节点的值
    public int delMin(Node_sort node){
        Node_sort target = node;
        // 循环查找左子节点，就会找到最小值
        while (target.left != null){
            target = target.left;
        }
        // 此时 target 指向了最小节点，删除最小节点
        delNode(target.value);
        return target.value;
    }

    // 删除节点
    public void delNode(int value){
         if (root == null){
             return;
         }else {
             // 先去找到需要删除的节点
             Node_sort targetNode = search(value);
             // 如果没有找到需要删除的节点
             if (targetNode == null){
                 return;
             }
             // 如果当前二叉树只有一个节点
             if (root.left == null && root.right == null){
                 root = null;
                 return;
             }
             // 之后去找到 targetNode 的父节点
             Node_sort parent = searchParent(value);

             // 如果要删除的节点是叶子节点
             if (targetNode.left == null && targetNode.right == null){
                 // 判断 targetNode 是父节点的右子节点还是右子节点
                 if (parent.left != null && parent.left.value == value){
                     parent.left = null; //  是左子节点，置空
                 } else if (parent.right != null && parent.right.value == value){
                     parent.right = null;//  是右子节点，置空
                 }
             } else if (targetNode.left != null && targetNode.right != null){
                 // 删除有两个子树的节点
                int minValue = delMin(targetNode.right);
                targetNode.value = minValue;
             } else {
                 // 删除只有一颗子树的节点
                 // 如果要删除的节点有左子节点
                 if (targetNode.left != null){
                     if (parent != null){
                         // 如果 targetNode 是 parent 的左子节点
                         if (parent.left.value == value){
                             parent.left = targetNode.left;
                         } else {// 如果 targetNode 是 parent 的右子节点
                             parent.right = targetNode.left;
                         }
                     } else {
                         root = targetNode.left;
                     }
                 } else { // 如果要删除的节点有右子节点
                     if (parent != null){
                         // 如果 targetNode 是 parent 的左子节点
                         if (parent.left.value == value){
                             parent.left = targetNode.right;
                         }else {    // 如果 targetNode 是 parent 的右子节点
                             parent.right = targetNode.right;
                         }
                     } else {
                         root = targetNode.right;
                     }
                 }
             }
         }
    }
}

/**
 * 创建节点
 */
class Node_sort{
    int value;
    Node_sort left;
    Node_sort right;

    public Node_sort(int value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Node_sort{" +
                "value=" + value +
                '}';
    }

    /**
     * 递归的方式去添加节点
     */
    public void add(Node_sort node){
        if (node == null){
            return;
        }
        // 判断传入的节点的值和当前子树的根节点的值的关系
        if (node.value < this.value){
            // 如果当前节点左子节点为null
            if (this.left == null){
                this.left = node;
            }else {
                this.left.add(node);
            }
        } else {
            // 如果当前节点右子节点为null
            if (this.right == null){
                this.right = node;
            }else {
                this.right.add(node);
            }
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder(){
        if (this.left != null){
            this.left.infixOrder();
        }
        System.out.println(this);
        if (this.right != null){
            this.right.infixOrder();
        }
    }

    /**
     * 查找需要删除的节点
     * @param value 想要删除的值
     * @return  找到则返回该节点，否则返回null
     */
    public Node_sort search(int value){
        if (value == this.value){       // 找到该节点
            return this;
        } else if (value < this.value){ // 值小于当前节点，向左子树递归查找
            // 如果左子树为空，则返回null
            if (this.left == null){
                return null;
            }
            return this.left.search(value);
        } else {                        // 值大于当前节点，向右子树递归查找
            if (this.right == null){
                return null;
            }
            return this.right.search(value);
        }
    }

    /**
     * 查找要删除节点的父节点
     * @param value 要查找的节点的值
     * @return
     */
    public Node_sort searchParent(int value){
        // 如果当前节点就是要删除的节点的父节点，就返回
        if ((this.left != null && this.left.value == value) ||
                (this.right != null && this.right.value == value)){
            return this;
        } else {
            // 如果查找的值小于当前节点的值，并且当前节点的左子节点不为空
            if (value < this.value && this.left != null){
                return this.left.searchParent(value);
            } else if (value >= this.value && this.right != null){
                return this.right.searchParent(value);
            }else {
                return null;    // 没有找到父节点
            }
        }
    }
}