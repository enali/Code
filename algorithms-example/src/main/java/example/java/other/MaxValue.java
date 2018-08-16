package example.java.other;

/**
 * leetCode 198:
 *
 * 题目:
 * 求数据中, 不相邻元素组成的子序列和最大为多少?
 *
 * 思路:
 * 用dp[i]表示遍历到第i个元素时, 最大子序列和, 则要么包括ith元素, 要么不包括,则
 * max(dp[i-2] + ary[i], dp[i-1])
 *
 * DP问题最大的特点是, 它可以从最小的子问题一点点推到最终结果.
 */
public class MaxValue {
    public static int getMaxValue(int[] ary) {
        int len = ary.length;
        int[] dp = new int[len];
        dp[0] = ary[0];
        dp[1] = Math.max(ary[0], ary[1]);
        for (int i=2; i<len; i++) {
            dp[i] = Math.max(dp[i-2] + ary[i], dp[i-1]);
        }
        return dp[len-1];
    }

    public static void main(String[] args) {
        int[] ary = {5, 2, 6, 3, 1, 7};
        System.out.println(getMaxValue(ary));
    }
}
