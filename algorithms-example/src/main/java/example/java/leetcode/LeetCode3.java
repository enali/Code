package example.java.leetcode;

import java.util.Arrays;

/**
 * 题目:
 * 已知一个字符串, 求用该字符串的无重复字符的最长子串的长度
 *
 * 注意: 子串是指连续的字符
 */
public class LeetCode3 {
    public static int lengthOfLongestSubstring(String s) {
        int[] preIdxs = new int[128];  // 存储前面出现字符的索引
        Arrays.fill(preIdxs, -1);
        int maxLen = 0;
        int curLen = 0;
        for (int curIdx=0; curIdx<s.length(); curIdx++) {
            char c = s.charAt(curIdx);
            int preIdx = preIdxs[c];
            // 若前面未出现此字符, 或前面字符的位置已经不在curLen的计算范围内了
            if (preIdx == -1 || curIdx-preIdx > curLen) {
                curLen++;
            } else {
                maxLen = Math.max(maxLen, curLen);
                curLen = curIdx - preIdx;  // 最大的魔法, 当前索引-前索引就是当前的长度
            }
            preIdxs[c] = curIdx;  // 索引永远更新
        }
        return Math.max(maxLen, curLen);
    }

    public static void main(String[] args) {
        String s1 = "abcabcbb";
        System.out.println(lengthOfLongestSubstring(s1));  // 3
        String s2 = "bbbbb";
        System.out.println(lengthOfLongestSubstring(s2));  // 1
        String s3 = "pwwkew";
        System.out.println(lengthOfLongestSubstring(s3));  // 3
    }
}
