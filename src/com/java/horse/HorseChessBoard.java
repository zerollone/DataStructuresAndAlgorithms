package com.java.horse;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/**
 * 马踏棋盘算法
 */
public class HorseChessBoard {

    private static int X;   // 棋盘的行
    private static int Y;   // 棋盘的列
    private static boolean visited[];   // 标记棋盘的各个位置是否被访问过
    private static boolean finished;    // 如果为 true，则表示成功走完棋盘

    public static void main(String[] args) {
        X = 8;
        Y = 8;
        visited = new boolean[X * Y];
        int[][] chessBoard = new int[X][Y];
        int row = 1;
        int column = 1;
        long start = System.currentTimeMillis();
        traversalChessBoard(chessBoard, row - 1, column - 1, 1);
        long end = System.currentTimeMillis();
        System.out.println("算法耗时：" + (end -start) + "ms");
        System.out.println("========= 马踏棋盘的走法 =========");
        for (int[] line : chessBoard){
            System.out.println(Arrays.toString(line));
        }
    }

    /**
     * 完成马踏棋盘算法
     *
     * @param chessBoard 棋盘
     * @param row        马儿所在的行
     * @param column     马儿所在的列
     * @param step       马尔走的第几步
     */
    public static void traversalChessBoard(int[][] chessBoard, int row, int column, int step) {
        // 将马儿走到位置设置成第几步
        chessBoard[row][column] = step;
        // 将马儿走过的位置进行标记
        visited[row * X + column] = true;
        // 和 next() 函数的横纵坐标想对应
        Point point = new Point(column, row);
        ArrayList<Point> ps = next(point);
        // 对ps进行非递减排序，减少回溯的次数
        // 进行优化
        sort(ps);
        // 遍历 ps 走下一步
        while (!ps.isEmpty()) {
            Point cur = ps.remove(0);
            // 判断该点是否已经被访问，没有被访问则回溯走下一步
            if (!visited[cur.y * X + cur.x]) {
                traversalChessBoard(chessBoard, cur.y, cur.x, step + 1);
            }
        }

        // 判断马儿是否完成了任务，使用 step 来进行比较，没有达到棋盘的格子数量则没有完成任务，说明当前位置走不通，并将其设置为 0
        if (step < X * Y && !finished){
            chessBoard[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            finished = true;
        }
    }

    /**
     * 根据当前位置，计算马儿还可以走哪些位置，并放入到一个集合中
     *
     * @param curPoint
     * @return
     */
    public static ArrayList<Point> next(Point curPoint) {
        ArrayList<Point> ps = new ArrayList<>();
        Point point = new Point();
        // 判断是否八个位置中有几个位置可以走
        if ((point.x = curPoint.x - 2) >= 0 && (point.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(point));
        }

        if ((point.x = curPoint.x - 1) >= 0 && (point.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(point));
        }

        if ((point.x = curPoint.x + 1) < X && (point.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(point));
        }

        if ((point.x = curPoint.x + 2) < X && (point.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(point));
        }

        if ((point.x = curPoint.x + 2) < X && (point.y = curPoint.y + 1) < Y) {
            ps.add(new Point(point));
        }

        if ((point.x = curPoint.x + 1) < X && (point.y = curPoint.y + 2) < Y) {
            ps.add(new Point(point));
        }

        if ((point.x = curPoint.x - 1) >= 0 && (point.y = curPoint.y + 2) < Y) {
            ps.add(new Point(point));
        }

        if ((point.x = curPoint.x - 2) >= 0 && (point.y = curPoint.y + 1) < Y) {
            ps.add(new Point(point));
        }

        return ps;
    }

    /**
     * 这是对算法进行优化
     *
     * 根据ps中所有位置得出的该位置的下一步的所有可选选择位置，并进行非递减排序，减少回溯的次数
     * @param ps
     */
    public static void sort(ArrayList<Point> ps){
        ps.sort(new Comparator<Point>() {
            @Override
            public int compare(Point o1, Point o2) {
                // 获取 o1 的下一步的所有位置个数
                int count1 = next(o1).size();
                // 获取 o2 的下一步的所有位置个数
                int count2 = next(o2).size();
                if (count1 < count2){
                    return -1;
                }else if(count1 == count2){
                    return 0;
                }else{
                    return 1;
                }
            }
        });
    }
}










