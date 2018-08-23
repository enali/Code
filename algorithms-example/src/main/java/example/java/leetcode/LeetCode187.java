package example.java.leetcode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 题目:
 * 将DNA序列看作是只包含['A', 'C', 'G', 'T']4个字符的字符串, 给一个DNA字符串, 找到所有长度为10且出现超过1次的子串
 *
 * 思考:
 * 1, 简单: 枚举出所有的子串, 统计所有子串出现的次数, 取出其中>1的子串
 *
 * 2, 复杂: 因为只有ACGT 4个字符, 因此, 将其编码为00, 01, 10, 11
 */
public class LeetCode187 {
    public static List<String> findRepeatedDnaSequences(String s) {
        List<String> rst = new ArrayList<>();
        if (s == null || s.length() <= 10) return rst;
        Map<String, Integer> m = new HashMap<>();
        // 10是固定的, 因为, 确定的字符串, 可枚举出所有的子串
        for (int i=0; i<=s.length()-10; i++) {
            String tmp = s.substring(i, i+10);
            // 若子串不存在于映射中, 则置1, 否则加1
            m.put(tmp, m.getOrDefault(tmp, 0) + 1);
        }
        m.forEach((k, v) -> {
            if (v > 1) rst.add(k);
        });
        return rst;
    }

    public static List<String> findRepeatedDnaSequences2(String s) {
        List<String> rst = new ArrayList<>();
        if (s == null || s.length() <= 10) return rst;
        int[] m = new int[1<<20];  // 存储10个字符的所有可能取值, 每个字符2bit, 因此, 可能性为1 << 20
        int base = encode(s.substring(0, 10));
        m[base] += 1;
        for (int i=10; i<s.length(); i++) {  // 从第10个字符起, 增量计算编码
            char c = s.charAt(i);
            base = (base >> 2) | (encode(c) << 18);  // 右移2, 移除最开始的1个字符, 将encode左移18位作为最高位
            m[base] += 1;
            if (m[base] == 2) rst.add(s.substring(i-9, i+1));  // 如果刚好是2, 表示字串重复了, 更多的重复不计算
        }
        return rst;
    }

    // 将1个字符转为数字
    private static int encode(char c) {
        switch (c) {
            case 'A':
                return 0;
            case 'C':
                return 1;
            case 'G':
                return 2;
            case 'T':
                return 3;
            default:
                throw new IllegalArgumentException("the str must have ACGT");
        }
    }

    // 将ACGT字串编码为整数, 因为只有4个字符, 因此编码为00, 01, 10, 11, 右侧字符为高位
    private static int encode(String s) {
        int rst = 0;
        for (int i=s.length()-1; i>=0; i--) {
            rst <<= 2;
            char c = s.charAt(i);
            rst |= encode(c);
        }
        return rst;
    }

    public static void main(String[] args) {
        String s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT";
        System.out.println(findRepeatedDnaSequences(s));
        System.out.println(findRepeatedDnaSequences2(s));
    }
}
