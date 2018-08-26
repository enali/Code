package example.java.offer;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 题目:
 * 输入一个字符串, 按字典序打印出该字符串中字符的所有排列, 例如输入字符串abc, 则打印出由字符a, b, c所有排列出的所有字符串:
 * abc, acb, bac, bca, cab, cba. 可能有重复字符
 */
public class Offer28 {
    public static ArrayList<String> permutation(String str) {
        ArrayList<String> rst = new ArrayList<>();
        if (str == null || str.isEmpty()) return rst;

        char[] ch = str.toCharArray();
        Arrays.sort(ch);  // 有序的字符数组
        boolean[] used = new boolean[str.length()];
        StringBuilder sb = new StringBuilder();
        dfs(ch, used, sb, rst);
        return rst;
    }

    // 可以这样理解, 有n个空位, 每个空位都可能是str的每个字符, 但又要求每个空位不能重复占用str的字符
    // used记录字符的使用情况
    private static void dfs(char[] ch, boolean[] used, StringBuilder sb, ArrayList<String> rst) {
        if (sb.length() == ch.length) {
            rst.add(sb.toString());
            return;
        }
        // 在当前空位, 遍历所有字符
        for (int i=0; i<ch.length; i++) {
            if (used[i]) continue;  // 此字符已经在前面的空位上使用过了
            // 一个空位接着一个空位, 从头遍历str的每个元素, 正常情况下, 对于重复元素, i-1一定先于i被使用
            // 魔法: 对于重复元素, 若i跟i-1相同, 但i-1没使用, 表示当前空位, i-1已经遍历过了, 而i跟i-1元素相同, 不能再用此空位
            if (i > 0 && ch[i] == ch[i-1] && !used[i-1]) continue;
            used[i] = true;
            sb.append(ch[i]);
            dfs(ch, used, sb, rst);  // 深搜
            // 回溯
            used[i] = false;
            sb.deleteCharAt(sb.length()-1);
        }
    }

    public static void main(String[] args) {
        String str = "abbbc";
        System.out.println(permutation(str));
    }
}
