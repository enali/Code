package example.java.offer;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目:
 * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，得它们的和正好是s。如果有多对数字的和等于s，输出任意一对即可。
 *
 * 思路:
 * 因为数组是有序的, 分别从最左和最右对元素求和, 如果和大, 则最右往左移, 如果和小, 则最左往右移
 */
public class Offer41 {
    public static List<Integer> findNumbersWithSum(int[] data, int sum) {
        int i = 0, j = data.length-1;
        List<Integer> rst = new ArrayList<>();
        while (i < j) {
            int tmp = data[i] + data[j];
            if (tmp > sum) j--;
            else if (tmp < sum) i++;
            else {
                rst.add(data[i]);
                rst.add(data[j]);
                break;
            }
        }
        return rst;
    }

    public static void main(String[] args) {
        int[] ary = {1, 2, 4, 7, 11, 15};
        int sum = 15;
        System.out.println(findNumbersWithSum(ary, sum));
    }
}
