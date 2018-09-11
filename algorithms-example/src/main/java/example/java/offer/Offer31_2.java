package example.java.offer;

import java.util.ArrayList;
import java.util.Collections;

/**
 * 题目:
 * 从数列中, 找到最长的递增的数列
 *
 * 思路:
 * 以dp[i]存储以ary[i]为结尾的递增序列的长度, 则dp[j]表示, 所有小于ary[j]的元素的所有dp中的值, +1
 *
 * 同: {@link example.java.leetcode.LeetCode300}
 */
public class Offer31_2 {
    public static ArrayList<Integer> maxSubIncreaseArray(int[] ary) {
        int len = ary.length;
        // dp[i]表示, 以ary[i]为结尾的递增序列的长度
        int[] dp = new int[len];
        // path[i]表示, 从索引path[i]跳转到i, 用来记录某种顺序, 来逆序找到递增序列
        int[] path = new int[len];
        dp[0] = 1;  // 第1个数只有一个, 自然序列长度只有1
        path[0] = -1;  // 0表示, 不从任何地方跳转

        int maxIdx = 0;
        for (int i=1; i<len; i++) {
            int preIdx = 0;  // 记录以i为结尾的递增序列的前一个数的索引
            int maxTmp = 0;
            for (int j=i-1; j>=0; j--) {
                if (ary[j] < ary[i] && dp[j] > maxTmp) {
                    maxTmp = dp[j];
                    preIdx = j;
                }
            }
            dp[i] = maxTmp + 1;
            path[i] = preIdx;
            if (dp[i] > dp[maxIdx]) maxIdx = i;
        }

        ArrayList<Integer> rst = new ArrayList<>();
        while (path[maxIdx] != -1) {
            rst.add(ary[maxIdx]);
            maxIdx = path[maxIdx];
        }
        rst.add(ary[maxIdx]);  // 添加起始点

        Collections.reverse(rst);  // 逆向
        return rst;
    }

    public static void main(String[] args) {
        int[] ary = {1, 5, 8, 2, 3, 4};
        System.out.println(maxSubIncreaseArray(ary));
    }
}
