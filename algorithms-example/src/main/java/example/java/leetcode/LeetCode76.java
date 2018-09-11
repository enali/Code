package example.java.leetcode;

/**
 * 题目:
 * 已知字符串S与字符串T, 求在S中的最小窗口(区间), 使得这个区间中包含了字符串T中的所有字符.
 * 例如: S="ADOBECODEBANC", T="ABC"
 * 包含T的子区间中, 有"ADOBEC", "CODEBA", "BANC"等等, 最小窗口区间是BANC
 *
 * 思考:
 * 有两个指针, left/right, 表示一个窗口, 思考left/right如何移动
 */
public class LeetCode76 {
    public static String minWindow(String s, String t) {
        if (s == null || t == null) return null;
        if (s.length() == 0 || t.length() == 0 || s.length() < t.length()) return "";
        // 为0表示字符没有出现, >0表示当前窗口字符出现的次数小于t中出现的次数, <0表示次数多于t中出现的次数
        int[] map = new int[128];
        for (int i=0; i<t.length(); i++) map[t.charAt(i)] += 1;
        int counter = t.length();  // IMP: 标记, 窗口与t是否匹配
        int left = 0, right = 0;  // 窗口的左右指针
        int len = Integer.MAX_VALUE;  // 符合条件的子串长度
        int head = 0;
        while (right < s.length()) {
            // 窗口中的字符出现在t中, 且出现次数小于t中的字符, 则因为出现将其减1, 同时counter也减1
            // 换句话说, 若counter为0, 表示当前窗口匹配
            if (map[s.charAt(right++)]-- > 0) counter--;
            while (counter == 0) {  // 当前窗口匹配
                if (right - left < len) len = right - (head = left);  // 更新长度
                // 经过前面的变化, map现在: 0表示在t中出现的字符, <0表示未在t中出现的字符, 或者出现次数多于t中的字符, 不存在>0的情况
                if (map[s.charAt(left++)]++ == 0) counter++;  // 使当前窗口不匹配
            }
        }
        return len == Integer.MAX_VALUE ? "" : s.substring(head, head+len);
    }

    public static void main(String[] args) {
        String s1 = "a";
        String t1 = "b";
        System.out.println(minWindow(s1, t1));  // ""

        String s2 = "aa";
        String t2 = "aa";
        System.out.println(minWindow(s2, t2));  // aa

        String s3 = "ADOBECODEBANC";
        String t3 = "ABC";
        System.out.println(minWindow(s3, t3));  // BANC
    }
}
