package example.java.offer;

import java.util.Arrays;

/**
 * 题目:
 * 在字符串中找出第一个只出现一次的字符。
 *
 * 思路:
 * 用一个数组来存储字符的出现情况, 参考 {@link Offer55}
 */
public class Offer35 {
    public static char firstNotRepeatingChar(String str) {
        // -1表示此字符未出现, -2表示此字符出现至少两次, 0及0以上表示此字符只出现一次, 出现时的索引
        int[] seq = new int[256];
        Arrays.fill(seq, -1);

        for (int i=0; i<str.length(); i++) {
            char c = str.charAt(i);
            if (seq[c] == -1) seq[c] = i;
            else seq[c] = -2;
        }

        int min = Integer.MAX_VALUE;
        char c = (char)-1;
        for (int i=0; i<seq.length; i++) {
            if (seq[i] == -1 || seq[i] == -2) continue;
            if (seq[i] < min) {
                min = seq[i];
                c = (char)i;
            }
        }
        return c;
    }

    public static void main(String[] args) {
        System.out.println(firstNotRepeatingChar("google"));  // l
        System.out.println(firstNotRepeatingChar("aabccdbd"));  // -1
        System.out.println(firstNotRepeatingChar("abcdefg"));  // a
        System.out.println(firstNotRepeatingChar("gfedcba"));  // g
        System.out.println(firstNotRepeatingChar("zgfedcba"));  // z
    }
}
