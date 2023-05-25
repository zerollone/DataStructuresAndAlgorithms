package com.java.linkedlist;

import java.util.Stack;

public class TestStack {
    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();

        // 入栈
        stack.add("tom");
        stack.add("jack");
        stack.add("alice");

        // 出栈
        while (stack.size()>0){
            System.out.println(stack.pop());
        }
    }
}
