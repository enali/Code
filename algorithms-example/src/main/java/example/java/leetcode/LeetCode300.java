package example.java.leetcode;

/**
 * 题目:
 * 已知一个未排序数组, 求这个数组最长上升子序列的长度
 *
 * 思考:
 * dp[i]存储以nums[i]为结尾的上升子序列的最大长度
 *
 * 类似, 都是以ith个元素为结尾的元素序列, 可参考 {@link LeetCode53}
 */
public class LeetCode300 {
    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int len = nums.length;
        // dp[i]存储以nums[i]为结尾的上升子序列的最大长度,
        // 它等于0~i-1中所有小于nums[i]的元素 + 1, dp[k]
        // dp[i] = max(dp[k]) 要求: nums[k] < nums[i], k=0,1,2,...,i-1
        int[] dp = new int[len];
        dp[0] = 1;
        for (int i=1; i<len; i++) {
            int max = 0;
            for (int j=0; j<i; j++) {
                if (nums[j] < nums[i] && dp[j] > max) max = dp[j];
            }
            dp[i] = max + 1;
        }
        int max = dp[0];
        for (int i=1; i<len; i++)
            if (dp[i] > max) max = dp[i];
        return max;
    }

    public static void main(String[] args) {
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        System.out.println(lengthOfLIS(nums));  // 4
    }
}
