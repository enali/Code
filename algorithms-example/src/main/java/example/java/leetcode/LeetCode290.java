package example.java.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * 题目:
 * 已知字符串pattern与字符串str, 确认str是否与pattern匹配. str与pattern匹配代表字符串str中的单词与pattern中的字符一一对应.
 * 都只包含小写字母
 * 例如: pattern = "abba", str="dog cat cat dog"匹配
 * pattern = "abba", str="dog cat cat fish"不匹配
 * pattern = "aaaa", str="dog cat cat dog"不匹配
 * pattern = "abba", str="dog dog dog dog"不匹配
 *
 * 思考:
 * 这里的映射关系是双向的
 */
public class LeetCode290 {
    public static boolean wordPattern(String pattern, String str) {
        String[] map = new String[26];  // char -> str
        Map<String, Character> str2char = new HashMap<>();  // str -> char
        String[] tmp = str.split("\\s+");
        if (pattern.length() != tmp.length) return false;
        for (int i=0; i<pattern.length(); i++) {
            char c = pattern.charAt(i);
            if (str2char.get(tmp[i]) == null && map[c-'a'] == null) { // 初次建立映射关系
                str2char.put(tmp[i], c);
                map[c - 'a'] = tmp[i];
            } else if (str2char.get(tmp[i]) != null && map[c-'a'] != null && str2char.get(tmp[i]) == c) continue;
            else return false;
        }
        return true;
    }

    public static void main(String[] args) {
        String pattern = "abba";
        String str = "dog cat cat fish";
        System.out.println(wordPattern(pattern, str));
    }
}
