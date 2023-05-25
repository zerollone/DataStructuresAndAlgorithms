package com.java.queue;

import java.util.Scanner;

public class ArrayQueueDemo {

    public static void main(String[] args) {
        ArrayQueue arrayQueue = new ArrayQueue(3);
        char key = ' ';     // 接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s(show)：显示队列");
            System.out.println("a(add)：添加数据到队列");
            System.out.println("g(get)：从队列取出数据");
            System.out.println("h(head)：查看队列头的数据");
            System.out.println("e(exit)：退出程序");
            key = scanner.next().charAt(0);     // 接收一个字符
            switch (key){
                case 's':
                    arrayQueue.showQueue();
                    break;
                case 'a':
                    System.out.print("请输入一个数字：");
                    int value = scanner.nextInt();
                    arrayQueue.addQueue(value);
                    break;
                case 'g':
                    try {
                        int result = arrayQueue.getQueue();
                        System.out.println("取出的数据是：" + result);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'h':
                    try {
                        int result = arrayQueue.getFrontData();
                        System.out.println("队列头数据是：" + result);
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 'e':
                    scanner.close();
                    System.out.println("程序结束");
                    loop = false;
                default:
                    System.out.println("输入错误，请重新输入");
                    break;
            }
        }
    }
}


// 使用数组模拟队列
class ArrayQueue{
    private int maxSize;    // 数组最大容量
    private int front;      // 队列头
    private int rear;       // 队列尾
    private int[] array;    // 该数组用于存储数据，模拟队列

    // 模拟队列的构造器
    public ArrayQueue(int arraySize){
        maxSize = arraySize;
        front = -1;     // 指向队列头部，分析出front是指向队列头的前一个位置
        rear = -1;      // 指向队列尾部，指向队列尾部的数据（即队列的最后一个位置）
        array = new int[maxSize];
    }

    // 判断队列是否已满
    public Boolean isFull(){
        return rear == maxSize - 1;
    }

    // 判断队列是否为空
    public boolean isEmpty(){
        return front == rear;
    }

    // 添加数据到队列
    public void addQueue(int n){
        if (isFull()){
            System.out.println("队列已满，无法添加数据");
            return;
        }
        rear++;
        array[rear] = n;  // array[++rear]
    }

    // 获取队列数据，出队列
    public int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，无法获取数据...");
        }
        front++;
        return array[front];
    }

    // 遍历队列数据
    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列为空，没有数据...");
            return;
        }
        for (int i = 0; i < array.length; i++){
            System.out.printf("array[%d] = %d ", i ,array[i]);
        }
        System.out.println();
    }

    // 显示队列的头数据，不是取出数据
    public int getFrontData(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，头数据为空");
        }
        int i = array[++front];
        front--;
        return i;
    }
}