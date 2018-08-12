package example.java.other;

import java.util.Arrays;

/**
 * 题目:
 * 求字串中最长的连续的不含重复字符的字串长度
 */
public class LongestSubStringWithoutDuplication {
    public static int longestSubStringWithoutDuplication(String str) {
        // 记录某个字符的前一个出现时的索引
        int[] preIndexs = new int[26];
        Arrays.fill(preIndexs, -1);
        int curLen = 0;
        int maxLen = 0;
        for (int curIdx=0; curIdx<str.length(); curIdx++) {
            int c = str.charAt(curIdx) - 'a';
            int preIdx = preIndexs[c];  // 此字符的前一个出现时的索引
            //                  preIdx可能特别靠前, 则curLen表明当前无重复字串并不是从那么靠前的位置开始计算的
            // 因此, 此preIdx无效, 虽然它是重复了.
            if (preIdx == -1 || curIdx - preIdx > curLen) {
                curLen++;
            } else {
                maxLen = Math.max(maxLen, curLen);
                curLen = curIdx - preIdx;
            }
            preIndexs[c] = curIdx;
        }
        maxLen = Math.max(maxLen, curLen);
        return maxLen;
    }

    public static void main(String[] args) {
        String s = "arabcacfr";
        System.out.println(longestSubStringWithoutDuplication(s));
    }
}
