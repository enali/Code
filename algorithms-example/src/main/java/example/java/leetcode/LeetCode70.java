package example.java.leetcode;

/**
 * 题目:
 * 爬楼梯, 每次可向上走1阶台阶或2阶台阶, 问n阶楼梯有多少种上楼方式
 *
 * 思考:
 * 以dp[i]表示i阶楼梯的上楼方式数, 则dp[i] = d[i-1] + d[i-2], 因为最后一步要么上了1阶台阶, 要么上了2阶台阶
 * 在本质上其实是斐波那契数列
 *
 * 还可类比找零钱的问题 {@link LeetCode322}, 因为最后一个零钱必定是其中一个
 */
public class LeetCode70 {
    public static int climbStairs(int n) {
        if (n <= 0) return 0;
        int[] dp = new int[n+1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i=2; i<=n; i++)
            dp[i] = dp[i-1] + dp[i-2];
        return dp[n];
    }
}
