package com.java.queue;

import java.util.Scanner;

public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArrayQueue arrayQueue = new CircleArrayQueue(3);
        char key = ' ';     // 接收用户输入
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop){
            System.out.println("s(show)：显示队列");
            System.out.println("a(add)：添加数据到队列");
            System.out.println("g(get)：从队列取出数据");
            System.out.println("h(head)：查看队列头的数据");
            System.out.println("y(head)：有效数据个数");
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
                case 'y':
                    int i = arrayQueue.size();
                    System.out.println("有效个数是：" + i);
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

//class CircleArrayQueue{
//    private int maxSize;    // 数组最大容量
//    // front 变量的含义：front指向队列的第一个元素，也就是arr[front]，front的初始值为0
//    private int front;      // 队列头
//    // rear 变量的含义：rear指向队列的最后一个元素的最后一个位置，空出一个空间做缓冲，rear的初始值为0
//    private int rear;       // 队列尾
//    private int[] array;    // 该数组用于存储数据，模拟队列
//
//    // 模拟队列的构造器
//    public CircleArrayQueue(int arraySize){
//        maxSize = arraySize;
//        front = 0;     // 指向队列头部，分析出front是指向队列头的前一个位置
//        rear = 0;      // 指向队列尾部，指向队列尾部的数据（即队列的最后一个位置）
//        array = new int[maxSize];
//    }
//
//    // 判断队列是否已满
//    public Boolean isFull(){
//        return (rear + 1) % maxSize == front;
//    }
//
//    // 判断队列是否为空
//    public boolean isEmpty(){
//        return front == rear;
//    }
//
//    // 添加数据到队列
//    public void addQueue(int n){
//        if (isFull()){
//            System.out.println("队列已满，无法添加数据");
//            return;
//        }
//        array[rear] = n;
//        // 将rear后移，必须考虑取模，环形队列可以会数组越界
//        rear = (rear + 1) % maxSize;
//    }
//
//    // 获取队列数据，出队列
//    public int getQueue(){
//        if (isEmpty()){
//            throw new RuntimeException("队列为空，无法获取数据...");
//        }
//        int i = array[front];
//        front = (front + 1) % maxSize;
//        return i;
//    }
//
//    // 遍历队列数据
//    public void showQueue(){
//        if (isEmpty()){
//            System.out.println("队列为空，没有数据...");
//            return;
//        }
//        // 重要，环形队列循环查看数据
//        for (int i = front; i < front + size(); i++){
//            System.out.printf("array[%d] = %d\n", i % maxSize ,array[i % maxSize]);
//        }
//        System.out.println();
//    }
//
//    // 求出当前数组的有效数据个数
//    public int size(){
//        return (rear + maxSize - front) % maxSize;
//    }
//
//    // 显示队列的头数据，不是取出数据
//    public int getFrontData(){
//        if (isEmpty()){
//            throw new RuntimeException("队列为空，头数据为空");
//        }
//        return array[front];
//    }
//}

class CircleArrayQueue{
    private int maxSize;    // 数组最大容量
    private int front;      // 队列头
    private int rear;       // 队列尾
    private int[] array;    // 该数组用于存储数据，模拟队列

    // 模拟队列的构造器
    public CircleArrayQueue(int arraySize){
        maxSize = arraySize;
        front = -1;     // 指向队列头部，分析出front是指向队列头的前一个位置
        rear = -1;      // 指向队列尾部，指向队列尾部的数据（即队列的最后一个位置）
        array = new int[maxSize];
    }

    // 标志位  0 表示 rear == front 为空，1 表示 rear == front 为满
    int i = 0;

    // 判断队列是否已满
    public Boolean isFull(){
        if (rear == -1){
            return false;
        }
        if (rear + 1 == maxSize && front == -1){
            return true;
        }
        if (i == 1 && rear == front){
            return true;
        }
        return false;
    }

    // 判断队列是否为空
    public boolean isEmpty(){
        return i == 0 && front == rear;
    }

    // 添加数据到队列
    public void addQueue(int n){
        if (isFull()){
            System.out.println("队列已满，无法添加数据");
            return;
        }
        // 将rear后移，必须考虑取模，环形队列可以会数组越界
        if (++rear >= maxSize){
            i = 1;
            rear %= maxSize;
        }
        array[rear] = n;
    }

    // 获取队列数据，出队列
    public int getQueue(){
        if (isEmpty()){
            throw new RuntimeException("队列为空，无法获取数据...");
        }
        if (++front == maxSize){
            i = 0;
            front = -1;
        }
        return array[front];
    }

    // 遍历队列数据
    public void showQueue(){
        if (isEmpty()){
            System.out.println("队列为空，没有数据...");
            return;
        }
        if (front == -1){
            for (int i = front + 1; i <= rear; i++){
                System.out.printf("array[%d] = %d\n", i % maxSize ,array[i % maxSize]);
            }
        }else{
            for (int i = front + 1; i <= front + size(); i++){
                System.out.printf("array[%d] = %d\n", i % maxSize ,array[i % maxSize]);
            }
        }
//        for (int i = 0; i < array.length; i++){
//            System.out.printf("array[%d] = %d\n", i % maxSize ,array[i % maxSize]);
//        }
        System.out.println();
    }

    // 求出当前数组的有效数据个数
    public int size(){
        if (front == -1){
            return rear+1;
        }
        if (rear == front && i == 1){
            return maxSize;
        }
        return (rear + maxSize - front) % maxSize;
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