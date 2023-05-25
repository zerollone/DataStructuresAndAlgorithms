package com.java.linkedlist;

public class JosephusProblem {
    public static void main(String[] args) {
        CircleSingleLinkedList circleSingleLinkedList = new CircleSingleLinkedList();
        // 添加节点
        circleSingleLinkedList.addBoy(5);
        // 遍历节点
        circleSingleLinkedList.list();

        // 出圈
        circleSingleLinkedList.countBoy(1, 2, 5);
    }
}

// 创建环形单向链表
class CircleSingleLinkedList{

    // 创建first节点
    private Boy first = null;

    // 添加节点，构建成一个环形的链表
    public void addBoy(int nums){
        if (nums < 1){
            System.out.println("添加节点的数值不对");
        }
        Boy curBoy = null;  // 辅助指针，帮助构建环形链表
        // 使用for创建环形链表
        for (int i = 1; i <= nums; i++){
            // 根据编号创建节点
            Boy boy = new Boy(i);
            if (i == 1){
                first = boy;
                first.setNext(first);
                curBoy = first;
            } else {
                 curBoy.setNext(boy);
                 boy.setNext(first);
                 curBoy = boy;
            }
        }
    }

    // 遍历环形链表
    public void list(){
        if (first == null){
            System.out.println("链表为空");
            return;
        }
        // first不能动，使用辅助指针完成遍历
        Boy curBoy = first;
        while (true){
            System.out.println("节点：" + curBoy.getNo());
            if (curBoy.getNext() == first){
                // 遍历完毕
                break;
            }
            curBoy = curBoy.getNext();
        }
    }

    /**
     * 根据输入，计算出出圈的顺序
     * @param startNo   表示从第几个节点开始数数
     * @param countNum  表示数几下
     * @param nums      表示最初有多少节点在圈中参与
     */
    public void countBoy(int startNo, int countNum, int nums){
        if (first == null || startNo < 1 || startNo > nums){
            System.out.println("参数有误");
            return;
        }
        // 创建辅助指针，指向最后一个节点
        Boy helper = first;
        while (true){
            if (helper.getNext() == first){
                break;
            }
            helper = helper.getNext();
        }

        // 出圈前，让节点到第k个节点位置
        for (int i = 0; i < startNo - 1; i++){
            first = first.getNext();
            helper = helper.getNext();
        }

        // 出圈
        while (true){
            if (helper == first){
                // 最后一个节点
                System.out.println("出圈节点：" + first.getNo());
                break;
            }
            // 让节点移动至下一次报数的位置
            for (int i = 0; i < countNum - 1; i++){
                first = first.getNext();
                helper = helper.getNext();
            }
            // 报数节点出圈
            System.out.println("出圈节点：" + first.getNo());
            first = first.getNext();
            helper.setNext(first);
        }
    }
}


// 创建boy类，表示节点
class Boy{
    private int no;
    private Boy next;

    public Boy(int no){
        this.no = no;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Boy getNext() {
        return next;
    }

    public void setNext(Boy next) {
        this.next = next;
    }
}