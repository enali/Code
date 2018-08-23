package example.java.leetcode;

/**
 * 题目:
 * 已知一个只包括大小写字符的字符串, 求用该字符串中的字符可以生成的最长回文字符串长度
 * 如, s = "abccccddaa", 可生成的最长回文字符串长度为9, 如"dccaaaccd", "adccbccda", "acdcacdca"
 *
 * 思考:
 * 回文的意思是, 中间对称, 则当字符的个数为偶数时, 则可以全部用于构成回文, 如果个数为奇数时, 则需要将其中一个放在最中间
 * 有多个奇数字符时, 只能使用1个字符.
 */
public class LeetCode409 {
    public static int longestPalindrome(String s) {
        if (s == null) return -1;
        if (s.length() <= 1) return s.length();

        int[] charnums = new int[26 * 2];
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c >= 'a' && c <= 'z') charnums[c - 'a'] += 1;
            else if (c >= 'A' && c <= 'Z') charnums[c - 'A' + 26] += 1;
        }
        boolean hasOdd = false;
        int maxLen = 0;
        for (int i = 0; i < charnums.length; i++) {
            if (charnums[i] % 2 == 0) maxLen += charnums[i];
            else {
                hasOdd = true;
                maxLen += charnums[i] - 1;
            }
        }
        if (hasOdd) maxLen += 1;
        return maxLen;
    }

    public static void main(String[] args) {
        String s = "abccccdd";
        System.out.println(longestPalindrome(s));  // 7
    }
}
