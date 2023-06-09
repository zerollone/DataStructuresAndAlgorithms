package com.java.tree;

/**
 * 二叉树
 */
public class BinaryTree {
    public static void main(String[] args) {
        // 创建一颗二叉树
        BinaryTrees binaryTrees = new BinaryTrees();
        // 创建节点
        HeroNode root = new HeroNode(1, "宋江");
        HeroNode node2 = new HeroNode(2, "吴用");
        HeroNode node3 = new HeroNode(3, "卢俊义");
        HeroNode node4 = new HeroNode(4, "林冲");
        HeroNode node5 = new HeroNode(5, "关胜");

        // 手动创建二叉树
        root.setLeft(node2);
        root.setRight(node3);
        node3.setRight(node4);
        node3.setLeft(node5);
        binaryTrees.setRoot(root);

        // 测试前序遍历
//        System.out.println("======== 前序遍历：========");
//        binaryTrees.preOrder();
//
//        // 测试中序遍历
//        System.out.println("======== 中序遍历：========");
//        binaryTrees.infixOrder();
//
//        // 测试后序遍历
//        System.out.println("======== 后序遍历：========");
//        binaryTrees.postOrder();
//
//        // 测试前序遍历查找
//        System.out.println("======== 前序遍历查找：========");
//        binaryTrees.preOrderSearch(5);
//
//        // 测试中序遍历查找
//        System.out.println("======== 中序遍历查找：========");
//        binaryTrees.infixOrderSearch(5);
//
//        // 测试后序遍历查找
//        System.out.println("======== 后序遍历查找：========");
//        binaryTrees.postOrderSearch(15);

        // 删除节点测试
        System.out.println("======== 前序遍历：========");
        binaryTrees.preOrder();
        binaryTrees.delNode(30);
        System.out.println("======== 删除后前序遍历：========");
        binaryTrees.preOrder();
    }

}

/**
 * 定义二叉树
 */
class BinaryTrees {
    private HeroNode root;

    public void setRoot(HeroNode root) {
        this.root = root;
    }

    // 前序遍历
    public void preOrder() {
        if (this.root != null) {
            this.root.preOrder();
        } else {
            System.out.println("当前二叉树为空！");
        }
    }

    // 中序遍历
    public void infixOrder() {
        if (this.root != null) {
            this.root.infixOrder();
        } else {
            System.out.println("当前二叉树为空！");
        }
    }

    // 后序遍历
    public void postOrder() {
        if (this.root != null) {
            this.root.postOrder();
        } else {
            System.out.println("当前二叉树为空！");
        }
    }

    // 前序遍历查找
    public void preOrderSearch(int no) {
        if (root != null) {
            HeroNode res = this.root.preOrderSearch(no);
            if (res != null) {
                System.out.println("前序遍历查找已找到，节点信息：" + res);
            } else {
                System.out.println("未找到该节点！！！");
            }
        } else {
            System.out.println("二叉树为空，无法查找！！！");
        }
    }

    // 中序遍历查找
    public void infixOrderSearch(int no) {
        if (root != null) {
            HeroNode res = this.root.infixOrderSearch(no);
            if (res != null) {
                System.out.println("中序遍历查找已找到，节点信息：" + res);
            } else {
                System.out.println("未找到该节点！！！");
            }
        } else {
            System.out.println("二叉树为空，无法查找！！！");
        }
    }

    // 后序遍历查找
    public void postOrderSearch(int no) {
        if (root != null) {
            HeroNode res = this.root.postOrderSearch(no);
            if (res != null) {
                System.out.println("后序遍历查找已找到，节点信息：" + res);
            } else {
                System.out.println("未找到该节点！！！");
            }
        } else {
            System.out.println("二叉树为空，无法查找！！！");
        }
    }

    // 删除节点
    public void delNode(int no) {
        if (root != null) {
            if (root.getNo() == no) {
                root = null;
            } else {
                root.delNode(no);
            }
        } else {
            System.out.println("二叉树为空，不能进行删除");
        }
    }
}

/**
 * 创建 HeroNode 结点
 */
class HeroNode {
    private int no;
    private String name;
    private HeroNode left;
    private HeroNode right;

    public HeroNode(int no, String name) {
        this.no = no;
        this.name = name;
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

    public HeroNode getLeft() {
        return left;
    }

    public void setLeft(HeroNode left) {
        this.left = left;
    }

    public HeroNode getRight() {
        return right;
    }

    public void setRight(HeroNode right) {
        this.right = right;
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        // 先输出父节点
        System.out.println(this);
        // 递归左子树前序遍历
        if (this.left != null) {
            this.left.preOrder();
        }
        // 递归右子树前序遍历
        if (this.right != null) {
            this.right.preOrder();
        }
    }

    /**
     * 中序遍历
     */
    public void infixOrder() {
        // 递归左子树前序遍历
        if (this.left != null) {
            this.left.infixOrder();
        }
        // 输出父节点
        System.out.println(this);
        // 递归右子树前序遍历
        if (this.right != null) {
            this.right.infixOrder();
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        // 递归左子树前序遍历
        if (this.left != null) {
            this.left.postOrder();
        }
        // 递归右子树前序遍历
        if (this.right != null) {
            this.right.postOrder();
        }
        // 输出父节点
        System.out.println(this);
    }

    /**
     * 前序遍历查找
     */
    public HeroNode preOrderSearch(int no) {
        // 比较当前节点是否是查询的节点
        if (this.no == no) {
            return this;
        }
        HeroNode heroNode = null; // 定义一个空节点，判断是否查找到
        // 如果当前节点不是，则判断其左子节点是否为空，不为空则递归前序查找
        if (this.left != null) {
            heroNode = this.left.preOrderSearch(no);
        }
        // 左子树已经找到
        if (heroNode != null) {
            return heroNode;
        }
        // 如果左子节点没有找到，则判断右子节点是否为空，不为空则递归前序查找
        if (this.right != null) {
            heroNode = this.right.preOrderSearch(no);
        }
        return heroNode;
    }

    /**
     * 中序遍历
     */
    public HeroNode infixOrderSearch(int no) {
        HeroNode heroNode = null; // 定义一个空节点，判断是否查找到
        // 判断左子节点是否为空，不为空则递归前序查找
        if (this.left != null) {
            heroNode = this.left.infixOrderSearch(no);
        }
        // 左子树已经找到
        if (heroNode != null) {
            return heroNode;
        }
        // 比较当前节点是否是查询的节点
        if (this.no == no) {
            return this;
        }
        // 如果左子节点没有找到，则判断右子节点是否为空，不为空则递归前序查找
        if (this.right != null) {
            heroNode = this.right.infixOrderSearch(no);
        }
        return heroNode;
    }

    /**
     * 后序遍历
     */
    public HeroNode postOrderSearch(int no) {
        HeroNode heroNode = null; // 定义一个空节点，判断是否查找到
        // 判断左子节点是否为空，不为空则递归前序查找
        if (this.left != null) {
            heroNode = this.left.infixOrderSearch(no);
        }
        // 左子树已经找到
        if (heroNode != null) {
            return heroNode;
        }
        // 如果左子节点没有找到，则判断右子节点是否为空，不为空则递归前序查找
        if (this.right != null) {
            heroNode = this.right.infixOrderSearch(no);
        }
        // 右子树已经找到
        if (heroNode != null) {
            return heroNode;
        }
        // 比较当前节点是否是查询的节点
        if (this.no == no) {
            return this;
        }
        return heroNode;
    }

    /**
     * 递归删除节点
     * 如果删除的节点是叶子节点，则删除该节点
     * 如果删除的节点是非叶子节点，则删除该子树
     */
    public void delNode(int no) {
        // 如果当前节点的左子节点不为空，并且左子节点就是需要删除的节点，则将 this.left = null，并且返回
        if (this.left != null && this.left.no == no) {
            this.left = null;
            return;
        }
        // 如果当前节点的右子节点不为空，并且右子节点就是需要删除的节点，则将 this.right = null，并且返回
        if (this.right != null && this.right.no == no) {
            this.right = null;
            return;
        }

        // 向左子树进行递归删除
        if (this.left != null) {
            this.left.delNode(no);
        }
        // 向右子树进行递归删除
        if (this.right != null) {
            this.right.delNode(no);
        }

        // 向右子树进行递归删除
    }

}









