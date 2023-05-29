package search;

import java.util.ArrayList;
import java.util.List;

/**
 * 二分查找
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {1, 8, 10, 89, 1000, 1234};
        int result = binarySearch(arr, 0, arr.length - 1, 11);
        if (result != -1) {
            System.out.println("查找到该数，其下标为：" + result);
        } else {
            System.out.println("不存在此数");
        }

        int[] arrs = {1, 8, 10, 89, 1000, 1234, 1234, 1234};
        List<Integer> list = binarySearch_all(arrs, 0, arr.length - 1, 1234);
        if (list.size() == 0) {
            System.out.println("不存在此数");
        } else {
            System.out.println("该数的下标有：" + list.toString());
        }
    }

    /**
     * 二分查找算法
     *
     * @param arr   查找的数组
     * @param left  左边的索引
     * @param right 右边的索引
     * @param index 需要查找的数
     * @return
     */
    public static int binarySearch(int[] arr, int left, int right, int index) {

        // 如果 left > right，则说明没有找到，需要接受递归
        if (left > right) {
            return -1;
        }

        int mid = (left + right) / 2;
        int midIndex = arr[mid];
        // 所要查找的数大于中间值，则向右递归
        if (index > midIndex) {
            return binarySearch(arr, mid + 1, right, index);
        } else if (index < midIndex) {
            // 所要查找的数小于中间值，则向左递归
            return binarySearch(arr, left, mid - 1, index);
        } else {
            return mid;
        }
    }

    /**
     * 二分查找，将所有符合条件的值都查找出来
     */
    public static List<Integer> binarySearch_all(int[] arr, int left, int right, int index) {
        // 如果 left > right，则说明没有找到，需要接受递归
        if (left > right) {
            return new ArrayList<Integer>();
        }

        int mid = (left + right) / 2;
        int midIndex = arr[mid];
        // 所要查找的数大于中间值，则向右递归
        if (index > midIndex) {
            return binarySearch_all(arr, mid + 1, right, index);
        } else if (index < midIndex) {
            // 所要查找的数小于中间值，则向左递归
            return binarySearch_all(arr, left, mid - 1, index);
        } else {
            // 找到查找的数时，不进行返回，并向左向右同时进行查找
            List<Integer> list = new ArrayList<Integer>();
            list.add(mid);

            // 向左进行查找
            int tempLeft = mid - 1;
            while (true) {
                // 因为数组是有序的，所以如果 arr[tempLeft] != index，则表示所有符合条件的数已经找到
                if (tempLeft < 0 || arr[tempLeft] != index) {
                    break;
                }
                list.add(tempLeft);
                tempLeft--;
            }

            // 向右进行查找
            int tempRight = mid + 1;
            while (true) {
                if (tempRight > arr.length - 1 || arr[tempRight] != index) {
                    break;
                }
                list.add(tempRight);
                tempRight++;
            }
            return list;
        }
    }
}
