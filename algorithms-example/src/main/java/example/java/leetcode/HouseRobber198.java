package example.java.leetcode;

/**
 * 思路:
 * 用dp[i]表示遍历到第i个房间时, 最大子序列和, 则要么包括ith房间, 要么不包括,则
 * max(dp[i-2] + ary[i], dp[i-1])
 */
public class HouseRobber198 {
    public int rob(int[] nums) {
        int len = nums.length;
        if (len == 0) return 0;
        if (len == 1) return nums[0];
        int[] dp = new int[len];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i=2; i<len; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        }
        return dp[len-1];
    }
}
