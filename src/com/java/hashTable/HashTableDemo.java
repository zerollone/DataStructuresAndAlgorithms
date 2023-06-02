package com.java.hashTable;

import java.util.Scanner;

/**
 * 哈希表的实现
 */
public class HashTableDemo {
    public static void main(String[] args) {
        HashTable hashTable = new HashTable(7);
        String key = "";
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("add: 添加雇员");
            System.out.println("show: 显示雇员");
            System.out.println("find: 查找雇员");
            System.out.println("exit: 退出系统");
            System.out.print("请输入：");
            key = scanner.next();
            switch (key) {
                case "add":
                    System.out.print("请输入id: ");
                    int id = scanner.nextInt();
                    System.out.print("请输入姓名：");
                    String name = scanner.next();
                    Emp emp = new Emp(id, name);
                    hashTable.add(emp);
                    break;
                case "show":
                    hashTable.show();
                    break;
                case "find":
                    System.out.print("请输入要查找的id：");
                    int uid = scanner.nextInt();
                    hashTable.find(uid);
                    break;
                case "exit":
                    scanner.close();
                    System.exit(0);
                    break;
                default:
                    break;
            }
        }
    }
}

/**
 * 创建HashTab 管理多条链表
 */
class HashTable {
    private EmpLinkedList[] empLinkedListArray;
    int size;

    // 构造器
    public HashTable(int size) {
        this.size = size;
        empLinkedListArray = new EmpLinkedList[size];
        // 重要！！！！！！
        // 必须初始化hash链表
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i] = new EmpLinkedList();
        }
    }

    // 添加雇员
    public void add(Emp emp) {
        // 根据员工的id，得到该员工应当添加到那条链表
        int empLinkedListNo = hashFun(emp.id);
        // 将 emp 添加到对应的链表中
        empLinkedListArray[empLinkedListNo].add(emp);
    }

    // 遍历链表
    public void show() {
        for (int i = 0; i < size; i++) {
            empLinkedListArray[i].show();
        }
    }

    // 查找
    public void find(int id) {
        int empLinkedListNo = hashFun(id);
        Emp emp = empLinkedListArray[empLinkedListNo].find(id);
        if (emp != null) {
            System.out.println("雇员id = " + emp.id + " 姓名 = " + emp.name);
        } else {
            System.out.println("在哈希表中没有找到");
        }
    }

    // 编写散列函数，使用取模的方法
    public int hashFun(int id) {
        return id % size;
    }
}

/**
 * 设置雇员
 */
class Emp {
    public int id;
    public String name;
    public Emp next;

    public Emp(int id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
}

/**
 * 创建 EmpLinkedList，表示链表
 */
class EmpLinkedList {
    // 头指针，指向第一个 Emp，
    private Emp head;

    // 添加雇员到链表中，默认为id顺序加入
    public void add(Emp emp) {
        // 添加第一个雇员
        if (head == null) {
            head = emp;
            return;
        }
        // 如果不是第一个雇员，则使用一个辅助指针，定位到链表最后
        Emp temp = head;
        while (true) {
            // 已经到链表的最后
            if (temp.next == null) {
                break;
            }
            // 进行后移
            temp = temp.next;
        }
        temp.next = emp;
    }

    // 遍历链表
    public void show() {
        if (head == null) {
            System.out.println("链表为空");
            return;
        }
        System.out.println("当前链表的信息：");
        Emp temp = head;
        while (true) {
            System.out.println("雇员id = " + temp.id + " 姓名 = " + temp.name);
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
    }

    // 根据 id 查找 雇员
    public Emp find(int id) {
        if (head == null) {
            System.out.println("链表为空");
            return null;
        }
        Emp temp = head;
        while (true) {
            if (temp.id == id) {
                break;
            }
            if (temp.next == null) {
                temp = null;
                break;
            }
            temp = temp.next;
        }
        return temp;
    }
}
