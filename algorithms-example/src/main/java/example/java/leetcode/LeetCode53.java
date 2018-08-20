package example.java.leetcode;

/**
 * 思路:
 * maxSubArray(int[] ary, int i)定义为, 以A[i]为结尾元素的最大子序列和
 * d[i] = (d[i-1] > 0 ? d[i-1] : 0) + A[i], 找所有d[i]中最大值
 */
public class LeetCode53 {
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
