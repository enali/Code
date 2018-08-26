package example.java.leetcode;

/**
 * 题目:
 * 给定一个数组, 求这个数组的连续子数组中, 最大的那一段的和
 *
 * 思路:
 * maxSubArray(int[] ary, int i)定义为, 以A[i]为结尾元素的最大子序列和
 * d[i] = (d[i-1] > 0 ? d[i-1] : 0) + A[i], 找所有d[i]中最大值
 *
 * 同 {@link example.java.offer.Offer31}
 */
public class LeetCode53 {
    public static int maxSubArray(int[] nums) {
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

    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, -5, 4};
        System.out.println(maxSubArray(nums));  // 6
    }
}
