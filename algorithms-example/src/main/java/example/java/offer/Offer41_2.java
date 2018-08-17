package example.java.offer;

import java.util.ArrayList;

/**
 * 题目:
 * 输入一个正数s，打印出所有和为s 的连续正数序列（至少两个数）。
 *
 * 思路:
 * 从最基础的序列(small)1,(big)2开始, 和如果大, 则增加左侧元素, 即序列开始元素右移, 和如果小, 则增加右侧元素
 */
public class Offer41_2 {
    public static ArrayList<ArrayList<Integer>> findContinuousSequence(int sum) {
        ArrayList<ArrayList<Integer>> rst = new ArrayList<>();
        int small = 1, big = 2;
        int tmp = small + big;
        while (small <= sum / 2) {
            if (tmp < sum) {
                big++;
                tmp += big;
            } else if (tmp > sum) {
                tmp -= small;
                small++;
            } else {
                ArrayList<Integer> item = new ArrayList<>();
                for (int i=small; i<= big; i++)
                    item.add(i);
                rst.add(item);
                tmp -= small;
                small++;
            }
        }
        return rst;
    }

    public static void main(String[] args) {
        int sum = 15;
        ArrayList<ArrayList<Integer>> rst = findContinuousSequence(sum);
        for (ArrayList<Integer> al : rst)
            System.out.println(al);
    }
}
