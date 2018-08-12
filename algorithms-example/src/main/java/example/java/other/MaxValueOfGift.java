package example.java.other;

/**
 * 题目:
 * 每一个格子上摆放着一个礼物, 礼物的价值不同, 从左上角开始, 只可以右走或下走一格, 求能得到的礼物的最大值.
 *
 * 思路:
 * 动态规划最主要的思想是, 要找到问题的子解, 或者要找到子问题.
 * 这个题的子问题是, 以(x, y)点为例, 其只有两个途径, 即从(x-1, y)往下走一格, 或者从(x, y-1)往右走一格.
 * 以f(x, y)表示走到(x, y)点的最大价值, 则f(x, y) = max(f(x-1, y), f(x, y-1)) + value
 */
public class MaxValueOfGift {
    public static int getMost(int[][] ary) {
        if (ary == null || ary.length <= 0 || ary[0].length <= 0) return -1;

        int cols = ary[0].length;
        int[] dp = new int[cols];  // 迭代更新
        for (int[] value: ary) {
            dp[0] += value[0];
            for (int i=1; i<cols; i++) {
                //               dp[i-1]表示f(x, y-1)的值, dp[i]表示f(x-1, y)的值
                dp[i] = Math.max(dp[i-1], dp[i]) + value[i];
            }
        }
        return dp[cols-1];
    }

    public static void main(String[] args) {
        int[][] ary = {
                {1, 10, 3, 8},
                {12, 2, 9, 6},
                {5, 7, 4, 11},
                {3, 7, 16, 5}
        };
        System.out.println(getMost(ary));
    }
}
