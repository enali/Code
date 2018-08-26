package example.java.offer;

/**
 * 题目:
 * 给一个数组，返回它的最大连续子序列的和，
 *
 * 思路:
 * dp[i]表示包含第i个数的连续序列的最大的和, 则如果dp[i] > 0, 则dp[i+1] = ary[i+1] + dp[i];
 * 否则等于ary[i+1]
 *
 * 同: {@link example.java.leetcode.LeetCode53}
 */
public class Offer31 {
    public static int maxSubArray(int[] ary) {
        int[] dp = new int[ary.length];
        dp[0] = ary[0];
        for (int i=1; i<ary.length; i++) {
            dp[i] = dp[i-1] > 0 ? dp[i-1]+ary[i] : ary[i];
        }
        return dp[ary.length-1];
    }

    public static void main(String[] args) {
        int[] ary1 = {5, -3, 4, 2};
        System.out.println(maxSubArray(ary1));
        int[] ary2 = {5, -6, 4, 2};
        System.out.println(maxSubArray(ary2));
    }
}
