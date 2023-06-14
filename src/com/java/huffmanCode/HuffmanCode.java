package com.java.huffmanCode;

import java.io.*;
import java.util.*;

public class HuffmanCode {
    public static void main(String[] args) {
//        String str = "i like like like java do you like a java";
//        byte[] bytes = str.getBytes(); // 得到每个字符的字节数
//        byte[] zip = zipByHuffman(bytes);
//
//        // 解压
//        System.out.println("========= 解压 ========");
//        byte[] decode = decode(huffmanCodes, zip);
//        System.out.println("解压后的数据：" + new String(decode));

        // 测试压缩文件
//        String path = "D:\\aa\\000\\a.txt";
//        String topath = "D:\\aa\\000\\to11.zip";
//        zipFile(path,topath);
//        System.out.println("压缩文件成功！！！");

        // 测试解压
        String path = "D:\\aa\\000\\to.zip";
        String topath = "D:\\aa\\000\\2.jpeg";
        unZipFile(path, topath);
        System.out.println("解压成功");
    }

    /**
     * 使用赫夫曼编码进行压缩
     */
    private static byte[] zipByHuffman(byte[] bytes) {
        // 统计字符
        List<Node> nodes = getNodes(bytes);
        // 创建赫夫曼树
        Node huffmanTreeNode = createHuffmanTree(nodes);
        // 前序遍历
        // huffmanTreeNode.preOrder();
        // 查看生成的赫夫曼编码
        Map<Byte, String> huffmanCode = getHuffmanCode(huffmanTreeNode);
        // 压缩后的赫夫曼编码
        byte[] zip = zip(bytes, huffmanCode);
        return zip;
    }

    /**
     * 统计字符串中各个字符出现的次数
     *
     * @param bytes 需要统计的字符串
     * @return 返回字符数统计
     */
    private static List<Node> getNodes(byte[] bytes) {
        ArrayList<Node> nodes = new ArrayList<>();
        Map<Byte, Integer> map = new HashMap<>();
        // 遍历bytes数组，统计每个字符的出现次数
        for (byte b : bytes) {
            Integer count = map.get(b);
            if (count == null) {
                map.put(b, 1);
            } else {
                map.put(b, count + 1);
            }
        }
        // 将map集合的中数据转换为node对象
        for (Map.Entry<Byte, Integer> entry : map.entrySet()) {
            nodes.add(new Node(entry.getKey(), entry.getValue()));
        }
        return nodes;
    }

    /**
     * 通过 list 创建对应的赫夫曼树
     */
    private static Node createHuffmanTree(List<Node> nodes) {
        while (nodes.size() > 1) {
            Collections.sort(nodes);
            Node leftNode = nodes.get(0);
            Node rightNode = nodes.get(1);
            // 创建新的二叉树，该二叉树的根节点没有data，只有权值
            Node parent = new Node(null, leftNode.weight + rightNode.weight);
            parent.left = leftNode;
            parent.right = rightNode;
            // 删除已经处理过的节点
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            // 将新的二叉树插入 nodes 中
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    /**
     * 前序遍历方法
     */
    private static void preOrder(Node node) {
        if (node != null) {
            node.preOrder();
        } else {
            System.out.println("赫夫曼树为空！！！");
        }
    }

    // 将赫夫曼编码存放再map集合中
    static Map<Byte, String> huffmanCodes = new HashMap<>();
    // 定义StringBuilder存储某个叶子节点的路径
    static StringBuilder stringBuilder = new StringBuilder();

    /**
     * 重载 getHuffmanCode() 函数，对生成赫夫曼编码进行封装
     */
    private static Map<Byte, String> getHuffmanCode(Node node) {
        if (node == null) {
            return null;
        }
        // 处理左子树
        getHuffmanCode(node.left, "0", stringBuilder);
        // 处理右子树
        getHuffmanCode(node.right, "1", stringBuilder);
        return huffmanCodes;
    }

    /**
     * 生成赫夫曼树对应的赫夫曼编码
     *
     * @param node          传入的赫夫曼树
     * @param code          路径参数：赫夫曼树向左走表示0，向右走表示1
     * @param stringBuilder 用于拼接路径
     */
    private static void getHuffmanCode(Node node, String code, StringBuilder stringBuilder) {
        StringBuilder stringBuilder1 = new StringBuilder(stringBuilder);
        // 将code 加入到 StringBuilder1 中
        stringBuilder1.append(code);
        // 如果 node == null 则不进行处理
        if (node != null) {
            // 判断当前 node 是非叶子节点还是叶子节点
            if (node.data == null) {
                // 非叶子节点，向左进行递归
                getHuffmanCode(node.left, "0", stringBuilder1);
                // 向右进行递归
                getHuffmanCode(node.right, "1", stringBuilder1);
            } else {
                // 叶子节点，将编码进行拼接
                huffmanCodes.put(node.data, stringBuilder1.toString());
            }
        }
    }

    /**
     * 将字符串对应的 byte[] 数组通过生成的赫夫曼编码表编码后，返回一个压缩后的 byte[] 数组
     *
     * @param bytes        需要压缩的原始字符串的字节码
     * @param huffmanCodes 赫夫曼编码表
     * @return 返回处理后的 byte[] 数组，由二进制数据取八位进行处理
     */
    private static byte[] zip(byte[] bytes, Map<Byte, String> huffmanCodes) {
        // 利用赫夫曼编码将byte[]数组生成对应的字符串
        StringBuilder stringBuilder = new StringBuilder();
        // 遍历 byte[] 数组
        for (byte b : bytes) {
            stringBuilder.append(huffmanCodes.get(b));
        }
        System.out.println("生成后的赫夫曼编码长度：" + stringBuilder.length());

        // 统计 stringBuilder 转成 byte[] 数组需要的空间
        // int len = (stringBuilder.length() + 7) / 8
        int length = stringBuilder.length();
        int len = 0;
        if (length % 8 == 0) {
            len = length / 8;
        } else {
            len = length / 8 + 1;
        }

        // 创建存放压缩数据的byte[]数组
        byte[] huffmanBytes = new byte[len];
        String str = "";
        int index = 0;
        for (int i = 0; i < length; i += 8) {
            if ((i + 8) > length) {
                str = stringBuilder.substring(i);
            } else {
                str = stringBuilder.substring(i, i + 8);
            }
            huffmanBytes[index] = (byte) Integer.parseInt(str, 2);
            index++;
        }
        return huffmanBytes;
    }

    /**
     * 解压赫夫曼编码后的数据
     *
     * @param huffmanCodes 赫夫曼编码表
     * @param huffmanBytes 赫夫曼编码后的数据
     * @return 解压后的 byte[] 数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        StringBuilder stringBuilder = new StringBuilder();
        // 将 byte数组转成二进制的字符串
        boolean flag;
        for (int i = 0; i < huffmanBytes.length; i++) {
            flag = (i == huffmanBytes.length - 1);  // 判断是否是最后一个字节，不是最后一个需要补高位
            stringBuilder.append(byteToBitString(!flag, huffmanBytes[i]));
        }

        // 将赫夫曼编码表的键值对进行反转
        Map<String, Byte> map = new HashMap<>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        // 解码
        List<Byte> list = new ArrayList<>();
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;
            boolean flag_d = true;
            Byte b = null;

            // 逐个字符进行比较
            while (flag_d) {
                // i 保持不动，让 count 移动，循环进行匹配字符
                String key = stringBuilder.substring(i, i + count);
                b = map.get(key);
                if (b == null) { // 没有匹配到
                    count++;
                } else {
                    flag_d = false;
                }
            }
            list.add(b);
            i += count;
        }
        // 当 for 循环结束后，list中存放的就是所有字符ASCII码
        // 将list中的数据放入byte[]数组中
        byte[] b = new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    /**
     * 将一个 byte 转换成一个二进制的字符串
     *
     * @param flag 是否需要补高位
     * @param b    传入的byte
     * @return
     */
    private static String byteToBitString(boolean flag, byte b) {
        // 将 b 转成 int 类型
        int temp = b;
        if (flag) {
            temp |= 256; // 如果是正数，需要补高位
        }
        // 返回 temp 对应的二进制的补码
        String s = Integer.toBinaryString(temp);
        if (flag) {
            return s.substring(s.length() - 8);
        } else {
            return s;
        }
    }

    /**
     * 压缩文件
     *
     * @param sourceFile 需要压缩的文件的路径
     * @param toFile     压缩后的文件的路径
     */
    public static void zipFile(String sourceFile, String toFile) {
        // 创建输出流
        OutputStream os = null;
        ObjectOutputStream oos = null;
        // 创建文件的输入流
        FileInputStream is = null;
        try {
            // 创建文件的输入流
            is = new FileInputStream(sourceFile);
            // 创建一个和源文件大小一样的 byte[]
            byte[] b = new byte[is.available()];
            // 读取文件
            is.read(b);
            // 直接对源文件压缩
            byte[] huffmanBytes = zipByHuffman(b);
            // 创建文件的输出流，存放压缩文件
            os = new FileOutputStream(toFile);
            // 创建一个和文件输出流关联的 ObjectOutputStream
            oos = new ObjectOutputStream(os);
            // 把赫夫曼编码后的字节数组写入压缩文件
            oos.writeObject(huffmanBytes);
            // 同时以对象流的方法写入赫夫曼编码写入压缩文件，以便后续的解码
            oos.writeObject(huffmanCodes);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                is.close();
                oos.close();
                os.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 对压缩文件进行解压
     *
     * @param zipFile 准备解压的文件
     * @param file    将文件解压的路径
     */
    public static void unZipFile(String zipFile, String file) {
        // 定义文件输入流
        InputStream is = null;
        // 定义一个对象输入流
        ObjectInputStream ois = null;
        // 定义文件的输出流
        OutputStream os = null;
        try {
            // 创建文件输入流
            is = new FileInputStream(zipFile);
            // 创建一个和 is 关联的对象输入流
            ois = new ObjectInputStream(is);
            // 读取 byte 数组
            byte[] huffmanBytes = (byte[]) ois.readObject();
            // 读取赫夫曼编码表
            Map<Byte, String> huffmanCodes = (Map<Byte, String>) ois.readObject();

            // 解码
            byte[] decode = decode(huffmanCodes, huffmanBytes);
            // 将解码后的数据写入文件
            os = new FileOutputStream(file);
            os.write(decode);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                os.close();
                ois.close();
                is.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}

/**
 * 创建 Node 节点，存放数据和权值
 */
class Node implements Comparable<Node> {
    Byte data;  // 存放数据
    int weight; // 存放权值
    Node left;
    Node right;

    public Node(Byte data, int weight) {
        this.data = data;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Node{" +
                "data=" + data +
                ", weight=" + weight +
                '}';
    }

    @Override
    public int compareTo(Node o) {
        // 从小到大进行排序
        return this.weight - o.weight;
    }

    // 前序遍历
    public void preOrder() {
        System.out.println(this);
        if (this.left != null) {
            this.left.preOrder();
        }
        if (this.right != null) {
            this.right.preOrder();
        }
    }
}




