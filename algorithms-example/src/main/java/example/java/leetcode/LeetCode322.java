package example.java.leetcode;

import java.util.Arrays;

/**
 * 题目:
 * 找零, 给定硬币的面值, 给定总数, 求最少的硬币数
 * 如, coins=[1,2,5,7,10], 金额为14
 *
 * 思考:
 * dp[i]表示金额为i时, 找零的最小个数
 * dp[i] = min(dp[i-1], dp[i-2], dp[i-5], dp[i-7], dp[i-10]) + 1
 */
public class LeetCode322 {
    public static int coinChange(int[] coins, int amount) {
        if (amount <= 0) return 0;
        if (coins == null || coins.length <= 0) return 0;

        int len = coins.length;
        int[] dp = new int[amount+1];  // dp[i]表示金额i找零时的最小个数
        Arrays.fill(dp, -1);  // 默认-1表示不可匹配
        dp[0] = 0;

        for (int coin=1; coin<=amount; coin++) {
            int min = Integer.MAX_VALUE;
            for (int j=0; j<len; j++) {
                int tmp = coin - coins[j];
                if (tmp >= 0 && dp[tmp] != -1 && dp[tmp] < min)
                    min = dp[tmp];
            }
            if (min == Integer.MAX_VALUE) dp[coin] = -1;  // 未找到
            else dp[coin] = min + 1;  // 更新第i的值
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        int[] coins2 = {1, 2, 5};
        int amount2 = 11;
        System.out.println(coinChange(coins2, amount2));  // 3

        int[] coins3 = {2};
        int amount3 = 3;
        System.out.println(coinChange(coins3, amount3));  // -1

        int[] coins = {1, 2, 5, 7, 10};
        int amount = 14;
        System.out.println(coinChange(coins, amount));  // 2

    }
}
