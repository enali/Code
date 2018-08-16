package example.java.leetcode;

/**
 * 思路:
 * 动态规划最主要的四点:
 * * 原问题与子问题. 问题是最大的连续序列和, 那子问题就是如果数组只有1个元素, 或2个元素呢
 * * 问题的状态: 最直观的就是元素和
 * * 初始状态: 1个元素时就是本身, 2个元素时找最大的
 * * 状态转移方程: 如果知道前i-1个元素的最大值, 那么加上第i个元素, 要么前i-1个元素的最大值, 要么再加上ith个元素, 要么就只有ith个元素
 *
 * maxSubArray(int[] ary, int i)定义为, 以A[i]为结尾元素的最大子序列和
 * d[i] = (d[i-1] > 0 ? d[i-1] : 0) + A[i], 找所有d[i]中最大值
 */
public class MaximumSubarray53 {
    public int maxSubArray(int[] nums) {
        if (nums.length < 1) return 0;

        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];

        for (int i=1; i<nums.length; i++) {
            dp[i] = nums[i] + (dp[i-1] > 0 ? dp[i-1] : 0);
            if (max < dp[i]) max = dp[i];
        }
        return max;
    }
}
