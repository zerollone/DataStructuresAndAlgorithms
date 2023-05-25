package com.java.stack;

public class Calculator {
    public static void main(String[] args) {
        String exp = "700+2*6-400";
        // 创建两个栈，数字栈，符号栈
        CalcStack numStack = new CalcStack(10);
        CalcStack operatorStack = new CalcStack(10);
        // 定义所需变量
        int index = 0;      // 用于遍历获取exp的值
        char operator = ' ';   // 用于获取符号栈的值
        int num1 = 0;
        int num2 = 0;
        int result = 0;
        String num = "";
        char c = ' ';
        // 循环扫描计算字符串
        while (true){
            // 获取exp中的每一个字符
            c = exp.substring(index,index + 1).charAt(0);
            // 如果是运算符
            if (operatorStack.isOperator(c)){
                // 判断当前符号栈是否为空
                if (!operatorStack.isEmpty()){
                    // 不为空，与符号栈的操作符进行比较，如果当前操作符的优先级小于等于栈中的操作符，就从数字栈中pop出两个数
                    // 在从符号栈中pop出一个符号进行运算，将得到的结果push到数字栈，将当前符号入符号栈
                    if (operatorStack.priority(c) <= operatorStack.priority(operatorStack.peek())){
                        num1 = numStack.pop();
                        num2 = numStack.pop();
                        operator = (char)operatorStack.pop();
                        result = numStack.computer(num1,num2,operator);
                        numStack.push(result);
                        operatorStack.push(c);
                    }else {
                        // 如果操作符大于取出的操作符，则直接入栈
                        operatorStack.push(c);
                    }
                }else {
                    // 符号栈为空，直接入栈
                    operatorStack.push(c);
                }
            }else {
                // 判断是否为多位数，首先需要查看当前字符的后一个字符是否为操作符，如果是则入栈，否则继续扫描
                num += c;
                // 判断是否是exp的最后一位
                if (index == exp.length() -1){
                    numStack.push(Integer.parseInt(num));
                }else {
                    // 判断下一个字符是否为操作符，是操作符则将现在的字符串转为数字入栈
                    if (operatorStack.isOperator(exp.substring(index+1,index+2).charAt(0))){
                        numStack.push(Integer.parseInt(num));
                        num = "";
                    }
                }
            }
            index++;
            if (index >= exp.length()){
                break;
            }
        }
        // 当表达式扫描完成后，只剩下同级的运算符号，顺序的从数字栈和符号栈pop出想要的数字和符号进行运算
        while (true){
            if (operatorStack.isEmpty()){
                break;
            }
            num1 = numStack.pop();
            num2 = numStack.pop();
            operator = (char)operatorStack.pop();
            result = numStack.computer(num1,num2,operator);
            numStack.push(result);
        }
        System.out.printf("表达式%s = %d",exp,numStack.pop());
    }
}

// 定义一个栈结构
class CalcStack{
    private int maxSize;    // 设置栈的大小
    private int[] stack;    // 数组模拟栈，栈数据放在数组中
    private int top = -1;   // top表示栈顶

    // 构造器
    public CalcStack(int maxSize){
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
        if (isFull()){
            System.out.println("栈满，无法新增数据");
            return;
        }
        top++;
        stack[top] = value;
    }

    // 出栈  pop
    public int pop(){
        if (isEmpty()){
            throw new RuntimeException("栈空，没有数据");
        }
        return stack[top--];
    }

    // 获取栈顶的值
    public int peek(){
        return stack[top];
    }

    // 遍历，从栈顶开始遍历
    public void show(){
        if (isEmpty()){
            System.out.println("栈空，没有数据");
            return;
        }
        for (int i = top; i >= 0; i--){
            System.out.println(stack[i] + " ");
        }
    }

    // 返回运算符的优先级，优先级使用数字表示，数字越大，优先级越高
    public int priority(int operator){
        if (operator == '*' || operator == '/'){
            return 1;
        }else if (operator == '+' || operator == '-'){
            return 0;
        }else {
            return -1;
        }
    }

    // 判断是不是运算符
    public boolean isOperator(char c){
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    // 计算
    public int computer(int num1, int num2, char operator){
        if (operator == '+'){
            return num1 + num2;
        }
        if (operator == '-'){
            return num2 - num1;
        }
        if (operator == '*'){
            return num1 * num2;
        }
        if (operator == '/'){
            return num2 / num1;
        }
        return -1;
    }

}









