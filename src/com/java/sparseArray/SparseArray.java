package com.java.sparseArray;

/**
 * 稀疏数组
 */
public class SparseArray {

    public static void main(String[] args){
        // 创建一个原始的二维数组  11 * 11
        // 0：没有棋子，1：黑子，2：篮子
        int chessArr1[][] = new int[11][10];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;

        // 输出原始的二维数组
        System.out.println("原始的二维数组~~~~~");
        for (int[] item : chessArr1){
            for (int data : item){
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        // 二维数组转稀疏数组
        int[][] sparseArr = toSparseArr(chessArr1);

        // 稀疏数组恢复二维数组
        sparseArrToChess(sparseArr);

    }

    /**
     * 将二维数组转换为稀疏数组
     */
    public static int[][] toSparseArr(int[][] chessArr1){
        // 1、先遍历数组，得到非0数据的个数
        System.out.println("====================================================");
        System.out.println("数组的行数：" + chessArr1.length);
        System.out.println("数组的列数：" + chessArr1[0].length);
        int sum = 0;
        for (int i = 0; i < chessArr1.length; i++){
            for (int j = 0; j < chessArr1[0].length; j++){
                if (chessArr1[i][j] != 0){
                    sum++;
                }
            }
        }
        System.out.println("sum = " + sum);

        // 2、创建对应的稀疏数组
        int sparseArr2[][] = new int[sum+1][3];
        // 稀疏数组赋值
        sparseArr2[0][0] = chessArr1.length;
        sparseArr2[0][1] = chessArr1[0].length;
        sparseArr2[0][2] = sum;

        // 3、遍历二维数组，将非0的值存放在 sparseArr2 中
        int flag = 1;
        for (int i = 0; i < chessArr1.length; i++){
            for (int j = 0; j < chessArr1[0].length; j++){
                if (chessArr1[i][j] != 0){
                    sparseArr2[flag][0] = i;
                    sparseArr2[flag][1] = j;
                    sparseArr2[flag][2] = chessArr1[i][j];
                    flag++;
                }
            }
        }

        // 4、输出稀疏数组
        System.out.println("====================================================");
        System.out.println("得到的稀疏数组 ~~~~~");
        for (int[] item : sparseArr2){
            for (int data : item){
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
        return sparseArr2;
    }

    /**
     *将稀疏数组恢复成原始的二维数组
     */
    public static void sparseArrToChess(int[][] sparseArr2){
        // 稀疏数组的值赋给二维数组
        int chessArr2[][] = new int[sparseArr2[0][0]][sparseArr2[0][1]];
        for (int i = 1; i < sparseArr2.length; i++){
            chessArr2[sparseArr2[i][0]][sparseArr2[i][1]] = sparseArr2[i][2];
        }

        System.out.println("====================================================");
        System.out.println("恢复的二维数组~~~~~");
        for (int[] item : chessArr2){
            for (int data : item){
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
