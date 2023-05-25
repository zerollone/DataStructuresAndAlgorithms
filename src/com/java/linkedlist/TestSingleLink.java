package com.java.linkedlist;

public class TestSingleLink {
    public static void main(String[] args) {
        // 定义节点
        HeroNode heroNode1 = new HeroNode(1, "宋江", "及时雨", null);
        HeroNode heroNode2 = new HeroNode(2, "卢俊义", "玉麒麟", null);
        HeroNode heroNode3 = new HeroNode(3, "吴用", "智多星", null);
        HeroNode heroNode4 = new HeroNode(4, "林冲", "豹子头", null);

        // 创建链表
        SingleLinkedList singleLinkedList = new SingleLinkedList();

        // 链表中加入节点
        singleLinkedList.addByOrder(heroNode1);
        singleLinkedList.addByOrder(heroNode4);
        singleLinkedList.addByOrder(heroNode3);
        singleLinkedList.addByOrder(heroNode2);
        // 查看链表
        singleLinkedList.show();
        System.out.println("-------修改节点数据-------");
        HeroNode heroNode5 = new HeroNode(4, "11林冲", "11豹子头", null);
        singleLinkedList.update(heroNode5);
        singleLinkedList.show();
        System.out.println("-------删除节点数据-------");
//        singleLinkedList.deleteNode(heroNode1);
//        singleLinkedList.deleteNode(heroNode4);

        singleLinkedList.show();

        // 获取链表长度
        System.out.print("链表长度：");
        System.out.println(singleLinkedList.getLength(singleLinkedList.getHead()));

        System.out.println("-------倒数第1个节点-------");
        HeroNode desc = singleLinkedList.getDesc(singleLinkedList.getHead(), 1);
        System.out.println(desc);


//        System.out.println("-------链表反转-------");
//        SingleLinkedList.reverseList(singleLinkedList.getHead());
//        singleLinkedList.show();

        System.out.println("-------逆序打印单链表-------");
        SingleLinkedList.reversePrint(singleLinkedList.getHead());


    }

}
