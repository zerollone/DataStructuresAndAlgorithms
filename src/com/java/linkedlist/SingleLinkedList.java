package com.java.linkedlist;

import java.util.Stack;

/**
 * 管理节点
 */
public class SingleLinkedList {

    // 初始化头节点，不存放具体数据
    private HeroNode head = new HeroNode(0,"","",null);

    public HeroNode getHead() {
        return head;
    }

    public void setHead(HeroNode head) {
        this.head = head;
    }

    /**
     * 添加节点到单链表（不考虑编号顺序）
     *      1、找到当前链表的最后节点
     *      2、将最后节点的next指向新节点
     * @param heroNode
     */
    public void add(HeroNode heroNode){
        HeroNode temp = head;   // 辅助节点 temp，用于遍历链表找到最后一个节点
        while (true){
            // 找打链表的最后，退出
            if (temp.getNext() == null){
                break;
            }
            // 没有找到最后，temp后移
            temp = temp.getNext();
        }
        // 退出while循环后，temp就指向了链表最后
        temp.setNext(heroNode);
    }

    /**
     * 添加节点到单链表（考虑编号顺序）
     * @param heroNode
     */
    public void addByOrder(HeroNode heroNode){
        HeroNode temp = head;   // 辅助节点 temp，用于遍历链表找到最后一个节点
        boolean flag = false;   // 标志添加的节点是否已存在
        while (true){
            // 找打链表的最后，退出
            if (temp.getNext() == null){
                break;
            }
            // 位置找到，就在temp的后面插入
            if (temp.getNext().getNo() > heroNode.getNo()){
                break;
            }
            // 添加的节点已存在
            if (temp.getNext().getNo() == heroNode.getNo()){
                flag = true;
                break;
            }
            // 没有找到最后，temp后移
            temp = temp.getNext();
        }
        if (flag){
            System.out.println("添加的节点已存在，不能加入，新添加的节点编号为：" + heroNode.getNo());
        }else{
            heroNode.setNext(temp.getNext());
            temp.setNext(heroNode);
        }
    }

    // 遍历链表
    public void show(){
        // 判断链表是否为空
        if (head.getNext() == null){
            System.out.println("链表为空");
        }
        // 辅助变量来遍历链表
        HeroNode temp = head.getNext();
        while (true){
            // 判断是否到链表最后
            if (temp == null){
                break;
            }
            System.out.println(temp);
            // 将temp节点后移
            temp = temp.getNext();
        }
    }

    // 修改节点
    public void update(HeroNode newNode){
        HeroNode temp = head.getNext();
        // 判断链表是否为空
        if (temp == null){
            System.out.println("链表为空，没有可修改的节点");
            return;
        }
        boolean flag = false;
        while (true){
            // 遍历完列表
            if (temp == null){
                break;
            }
            if (temp.getNo() == newNode.getNo()){
                flag = true;
                break;
            }
            temp = temp.getNext();
        }
        if (flag){
            temp.setName(newNode.getName());
            temp.setNickname(newNode.getNickname());
        }else {
            System.out.println("没有找到节点");
        }
    }

    // 删除节点
    public void deleteNode(HeroNode heroNode){
        if (head.getNext() == null){
            System.out.println("链表为空，没有可删除节点！！！");
        }
        HeroNode temp = head;
        while (true){
            // 遍历结束
            if (temp.getNext() == null){
                break;
            }
            if (temp.getNext().getNo() == heroNode.getNo()){
                temp.setNext(temp.getNext().getNext());
                break;
            }
            temp = temp.getNext();
        }
    }

    // 统计节点个数(有头节点的不算)
    public int getLength(HeroNode head){
        if (head.getNext() == null){
            System.out.println("链表为空");
        }
        HeroNode temp = head;
        int length =0;
        while (temp.getNext() != null){
            length++;
            temp = temp.getNext();
        }
        return length;
    }

    // 查找单链表中的倒数第k个节点
    public HeroNode getDesc(HeroNode head, int node){
        if (head.getNext() == null){
            System.out.println("链表为空");
        }
        int size = getLength(head);
        if (node < 1 || node > size){
            System.out.println("输入节点数错误");
            return null;
        }
        HeroNode temp = head.getNext();
        // 查找倒数第k个节点
        for (int i = 0; i < size - node; i++){
            temp = temp.getNext();
        }
        return temp;
    }

    // 将单链表反转
    public static void reverseList(HeroNode head){
        // 当前链表为空或者只有一个节点，无需反转
        if (head.getNext() == null || head.getNext().getNext() == null){
            return;
        }
        // 定义一个辅助的指针，帮助遍历原来的链表
        HeroNode current = head.getNext();
        // 指向当前节点的下一个节点
        HeroNode next = null;
        HeroNode reverseHead = new HeroNode(0,"","",null);
        // 遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead的最前端
        while (current != null){
            next = current.getNext();
            current.setNext(reverseHead.getNext());// 将current节点添加到反转链表中的第一个节点，将反转链表中的节点数据接入插入的第一个节点之后
            reverseHead.setNext(current);
            current = next;
        }
        // 头节点转换
        head.setNext(reverseHead.getNext());
    }

    // 逆序打印单链表：利用栈的先进后出的特点，实现逆序打印
    public static void reversePrint(HeroNode head){
        if (head.getNext() == null || head.getNext().getNext() == null){
            return;
        }
        // 创建栈，将链表数据压入栈
        Stack<HeroNode> stack = new Stack<HeroNode>();
        HeroNode temp = head.getNext();
        while (temp != null){
            stack.push(temp);
            temp = temp.getNext();
        }
        // 打印栈中数据
        while (stack.size() > 0){
            System.out.println(stack.pop());
        }
    }

    // 合并两个有序单链表，合并之后依然有序
}









