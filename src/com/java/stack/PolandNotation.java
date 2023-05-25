package com.java.stack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

/**
 * 逆波兰表达式
 */
public class PolandNotation {
    public static void main(String[] args) {

        String exp = "15+((2+3)*4)-50";
        List<String> list = getListFromMedialExp(exp);
        System.out.println(list);
        List<String> list1 = suffixExpList(list);
        System.out.println(list1);
        int calculate = calculate(list1);
        System.out.println(calculate);


        // (3 + 4) * 5 - 6 对应的后缀表达式是：3 4 + 5 * 6 -
        // String exp = "30 4 + 5 * 6 -";
//        String exp = "4 5 * 8 - 60 + 8 2 / +";
//        List<String> stringList = getListString(exp);
//        int result = calculate(stringList);
//        System.out.println("运算结果：" + result);
    }

    /**
     * 将一个逆波兰表达式依次将数据和运算符放入到 ArrayList 中
     */
    public static List<String> getListString(String exp) {
        String[] strings = exp.split(" ");
        List<String> list = new ArrayList<String>();
        for (String s : strings) {
            list.add(s);
        }
        // list.addAll(Arrays.asList(strings));
        return list;
    }

    /**
     * 逆波兰表达式的运算
     */
    public static int calculate(List<String> str) {
        // 创建一个栈，存放数字
        Stack<String> stack = new Stack<>();

        // 遍历逆波兰表达式
        for (String item : str) {
            // 正则表达式匹配数字，多位数
            if (item.matches("\\d+")) {
                // 入栈
                stack.push(item);
            } else {
                // 判断是运算符
                // pop 出两个数字进行运算，之后将结果入栈
                int num1 = Integer.parseInt(stack.pop());
                int num2 = Integer.parseInt(stack.pop());
                int result = 0;
                if (item.equals("+")) {
                    result = num1 + num2;
                } else if (item.equals("-")) {
                    result = num2 - num1;
                } else if (item.equals("*")) {
                    result = num1 * num2;
                } else if (item.equals("/")) {
                    result = num2 / num1;
                } else {
                    throw new RuntimeException("运算符有误！");
                }

                stack.push("" + result);
            }
        }
        // 将最后的运算结果返回
        return Integer.parseInt(stack.pop());
    }

    /**
     * 将中缀表达式转成对应的 list
     */
    public static List<String> getListFromMedialExp(String str){
        List<String> list = new ArrayList<String>();
        int i = 0;  // 指针，遍历中缀表达式字符串
        String strs = ""; // 用于多位数的拼接
        char c;
        while (i < str.length()){
            // 非数字，直接加入到 list
            if ((c = str.charAt(i)) < 48 || (c = str.charAt(i)) > 57){
                list.add("" + c);
                i++;
            } else {
                // 数字进行拼接
                strs = "";
                while (i < str.length() && (c = str.charAt(i)) >= 48 && (c = str.charAt(i)) <= 57){
                    strs += c;
                    i++;
                }
                list.add(strs);
            }
        }
        return list;
    }

    /**
     * 将中缀表达式对应的list 转为 后缀表达式的list
     */
    public static List<String> suffixExpList(List<String> medialExp){
        Stack<String> s1 = new Stack<String>();     // 符号栈
        List<String> s2 = new ArrayList<String>();  // 数字栈，使用list代替栈，因为该栈没有pop操作

        // 遍历中缀表达式list
        for (String item : medialExp){
            if (item.matches("\\d+")){
                s2.add(item);
            } else if (item.equals("(")){
                s1.push(item);
            } else if (item.equals(")")){
                // 如果是右括号 ")"，则依次弹出s1栈顶的运算符，并压入s2中，直到遇到左括号为止，并将左括号丢弃
                while (!s1.peek().equals("(")){
                    s2.add(s1.pop());
                }
                s1.pop();
            } else {
                // 当item的优先级小于等于 s1 栈顶运算符，将 s1 栈顶的运算符弹出并加入到 s2中，反复比较直到大于s1栈顶运算符
                while (s1.size() !=0 && operationValue(item) <= operationValue(s1.peek())){
                    s2.add(s1.pop());
                }
                // 将 item 压入栈
                s1.push(item);
            }
        }
        // 将s1中剩余的运算符加入到 s2 中
        while (s1.size() != 0){
            s2.add(s1.pop());
        }
        return s2;
    }

    /**
     * 给出运算符的等级
     */
    public static int operationValue(String operation){
        int res = 0;
        switch (operation){
            case "+":
                res = 1;
                break;
            case "-":
                res = 1;
                break;
            case "*":
                res = 2;
                break;
            case "/":
                res = 2;
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return res;
    }
}





