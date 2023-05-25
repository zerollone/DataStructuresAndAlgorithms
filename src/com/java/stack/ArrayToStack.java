package com.java.stack;

import java.util.Scanner;

/**
 * 使用数组模拟栈
 */
public class ArrayToStack {
    public static void main(String[] args) {
        ArrayStack arrayStack = new ArrayStack(5);
        String key = "";
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);

        while (flag){
            System.out.println("show：显示栈数据");
            System.out.println("push：将数据压入栈中");
            System.out.println("pop：从栈中取出数据");
            System.out.println("请输入你的选择：");
            System.out.println("exit：退出程序");
            key = scanner.next();
            switch (key) {
                case "show":
                    try {
                        arrayStack.show();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "push":
                    System.out.print("请输入加入到栈的数据");
                    int value = scanner.nextInt();
                    arrayStack.push(value);
                    break;
                case "pop":
                    int pop = 0;
                    try {
                        pop = arrayStack.pop();
                        System.out.println("取出的数据是：" + pop);
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case "exit":
                    flag = false;
                    break;
                default:
                    System.out.println("输入有误，请重新输入");
                    break;
            }
        }
    }
}

// 定义一个栈结构
class ArrayStack{
    private int maxSize;    // 设置栈的大小
    private int[] stack;    // 数组模拟栈，栈数据放在数组中
    private int top = -1;   // top表示栈顶

    // 构造器
    public ArrayStack(int maxSize){
        this.maxSize = maxSize;
        stack = new int[maxSize];
    }

    // 判断是否栈满
    public boolean isFull(){
        return top == maxSize -1;
    }

    // 判断栈空
    public boolean isEmpty(){
        return top == -1;
    }

    // 入栈  push
    public void push(int value){
        // 判断栈是否满
        if (isFull()){
            System.out.println("栈满，无法新增数据");
            return;
        }
        top++;
        stack[top] = value;
    }

    // 出栈  pop
    public int pop(){
        // 判断栈是否空
        if (isEmpty()){
            throw new RuntimeException("栈空，没有数据");
        }
        return stack[top--];
    }

    // 遍历，从栈顶开始遍历
    public void show(){
        // 判断栈是否空
        if (isEmpty()){
            throw new RuntimeException("栈空，没有数据");
        }
        for (int i = top; i >= 0; i--){
            System.out.println(stack[i] + " ");
        }
    }
}