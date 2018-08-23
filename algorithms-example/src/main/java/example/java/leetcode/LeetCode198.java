package example.java.leetcode;

/**
 * 题目:
 * 在一条直线上, 有n个房屋, 每个房屋中有数量不等的财宝, 有一个盗贼希望从房屋中盗取财宝, 由于房屋中有报警器, 如果同时从相邻的两
 * 个房屋中盗取财宝就会触发报警器, 问在不触发报警器的前提下, 可以获取多少财宝.
 *
 * 思路:
 * 用dp[i]表示遍历到第i个房间时, 最大子序列和, 则要么包括ith房间, 要么不包括,则
 * max(dp[i-2] + ary[i], dp[i-1])
 */
public class LeetCode198 {
    public static int rob(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;
        if (len == 1) return nums[0];
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i=2; i<len; i++) {
            // 不包括ith房间时, 为dp[i-1], 包括时为, dp[i-2] + nums[i]
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        }
        return dp[len-1];
    }

    public static void main(String[] args) {
        int[] nums = {5, 2, 6, 3, 1, 7};
        System.out.println(rob(nums));  // 18
    }
}
