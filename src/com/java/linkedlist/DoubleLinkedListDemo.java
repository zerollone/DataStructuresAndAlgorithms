package com.java.linkedlist;

public class DoubleLinkedListDemo {

    public static void main(String[] args) {
        // 定义节点
        HeroNodes heroNode1 = new HeroNodes(1, "宋江", "及时雨");
        HeroNodes heroNode2 = new HeroNodes(2, "卢俊义", "玉麒麟");
        HeroNodes heroNode3 = new HeroNodes(8, "吴用", "智多星");
        HeroNodes heroNode4 = new HeroNodes(6, "林冲", "豹子头");
        // 创建双向链表
        DoubleLinkedList doubleLinkedList = new DoubleLinkedList();
        doubleLinkedList.add(heroNode1);
        doubleLinkedList.add(heroNode2);
        doubleLinkedList.add(heroNode3);
        doubleLinkedList.add(heroNode4);
        doubleLinkedList.list();

        // 顺序添加节点
        System.out.println("-------顺序添加节点-------");
        HeroNodes heroNode5 = new HeroNodes(5, "公孙胜", "入云龙");
        doubleLinkedList.addByOrder(heroNode5);
        doubleLinkedList.list();

//        System.out.println("-------修改节点数据-------");
//        HeroNodes heroNode5 = new HeroNodes(4, "风雪林冲", "豹子头+-+");
//        doubleLinkedList.update(heroNode5);
//        doubleLinkedList.list();
//
//        System.out.println("-------删除节点数据-------");
//        doubleLinkedList.del(4);
//        doubleLinkedList.list();
    }
}

// 创建双向链表的类
class DoubleLinkedList{
    // 初始化头节点
    private HeroNodes head = new HeroNodes(0,"", "");

    // 返回头节点
    public HeroNodes getHead(){
        return head;
    }

    // 遍历双向链表的方法
    public void list(){
        if (head.next == null){
            System.out.println("链表为空");
            return;
        }
        HeroNodes temp = head.next;
        while (temp != null){
            System.out.println(temp);
            temp = temp.next;
        }
    }

    // 双向链表最后添加节点
    public void add(HeroNodes heroNode){
        HeroNodes temp = head;
        while (temp.next != null){
            temp = temp.next;
        }
        temp.next = heroNode;
        heroNode.per = temp;
    }

    // 顺序添加节点
    public void addByOrder(HeroNodes heroNode){
        HeroNodes temp = head;
        // 链表为空时
        if (temp.next == null){
            temp.next = heroNode;
            heroNode.per = temp;
        }
        // 链表不为空
        while (true){
            // 插入
            if (temp.next != null){
                if (temp.next.no > heroNode.no){
                    heroNode.next = temp.next;
                    temp.next.per = heroNode;
                    temp.next = heroNode;
                    heroNode.per = temp;
                    break;
                }
                temp = temp.next;
            }else {
                // 循环到最后一个节点
                temp.next = heroNode;
                heroNode.per = temp;
                break;
            }
        }
    }

    // 修改双向链表的节点
    public void update(HeroNodes heroNode){
        if (head.next == null){
            System.out.println("双向链表为空");
        }
        HeroNodes temp = head.next;
        boolean flag = true;
        while (temp != null){
            if (temp.no == heroNode.no){
                temp.name = heroNode.name;
                temp.nickname = heroNode.nickname;
                flag = false;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            System.out.println("没有找到可修改的节点");
        }
    }

    // 删除节点
    public void del(int no){
        if (head.next == null){
            System.out.println("双向链表为空");
        }
        HeroNodes temp = head.next;
        boolean flag = false;
        while (true){
            if (temp == null){
                break;
            }
            if (temp.no == no){
                flag = true;
                break;
            }
            temp = temp.next;
        }
        // 删除
        if (flag){
            temp.per.next = temp.next;
            if (temp.next != null){
                temp.next.per = temp.per;
            }
        }else {
            System.out.println("删除的节点不存在");
        }
    }


}

// 定义双向链表的节点
class HeroNodes{
    public int no;
    public String name;
    public String nickname;
    public HeroNodes next;  // 指向下一个节点
    public HeroNodes per;   // 指向上一个节点

    public HeroNodes(int no, String name, String nickname) {
        this.no = no;
        this.name = name;
        this.nickname = nickname;
    }

    @Override
    public String toString() {
        return "HeroNodes{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}