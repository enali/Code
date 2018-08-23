package example.java.leetcode;

import java.util.*;

/**
 * 题目:
 * 已知一组字符串, 将所有anagram(字母相同,顺序不同)放到一起输出
 * 例如: ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 返回: [["ate", "eat", "tea"], ["nat", "tan"], ["bat"]]
 *
 * 思考:
 * 如何建立哈希表, 怎样设计哈希表的key和value, 就可以将各个字符数相同的字符串映射到一起
 *
 * key为str的排序后字串, 则同字符数的str排序后相同
 */
public class LeetCode49 {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> m = new HashMap<>();
        for (int i=0; i<strs.length; i++) {
            char[] tmp = strs[i].toCharArray();
            Arrays.sort(tmp);
            String s = String.valueOf(tmp);
            if (!m.containsKey(s)) {
                m.put(s, new ArrayList<>());
            }
            m.get(s).add(strs[i]);
        }
        List<List<String>> rst = new ArrayList<>(m.values());
        return rst;
    }
}
