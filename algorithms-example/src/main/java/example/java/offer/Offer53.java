package example.java.offer;

/**
 * 题目:
 * 请实现一个函数用来匹配包括'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（包含0次）。
 * 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但是与"aa.a"和"ab*a"均不匹配
 */
public class Offer53 {
    // memo[i][j]表示text[i:]和pat[j:]是否匹配
    // null表示不知道, TRUE表示匹配, FALSE表示不匹配
    Result[][] memo;

    public boolean match(String text, String pattern) {
        memo = new Result[text.length() + 1][pattern.length() + 1];
        return dp(0, 0, text, pattern);
    }

    public boolean dp(int i, int j, String text, String pattern) {
        if (memo[i][j] != null) {  // 如果已经有匹配结果, 则直接返回
            return memo[i][j] == Result.TRUE;
        }
        boolean ans;
        if (j == pattern.length()) {  // 模式串已经结束
            ans = i == text.length();  // 若文本串也结束, 则匹配, 否则不匹配
        } else {
            // 简单匹配, 即字符相同, 或模式字符为'.'
            boolean first_match = i < text.length() &&
                    (pattern.charAt(j) == text.charAt(i) || pattern.charAt(j) == '.');

            if (j + 1 < pattern.length() && pattern.charAt(j + 1) == '*') { // 如果下一个模式字符是*号的话
                // '*'有两个特别的地方, 表示其前一个字符重复0或多次.
                //    重复0次, 相当于模式串跳到j+2        当前字符必须简单匹配,    重复多次, 则文本串跳到i+1
                ans = dp(i, j + 2, text, pattern) || first_match && dp(i + 1, j, text, pattern);
            } else { // 如果不是*, 只有字母和'.', 则跳到下一字符
                ans = first_match && dp(i + 1, j + 1, text, pattern);
            }
        }
        // 因为会多次递归调用dp方法, 因此可以记录[i:]和[j:]的匹配情况
        memo[i][j] = ans ? Result.TRUE : Result.FALSE;
        return ans;
    }

    public boolean match2(String input, String pattern) {
        if (input == null || pattern == null) return false;
        return matchCore(input, 0, pattern, 0);
    }

    private boolean matchCore(String input, int i, String pattern, int p) {
        // 匹配串和模式串都到达尾，说明成功匹配
        if (i >= input.length() && p >= pattern.length()) return true;
        // 只有模式串到达结尾，说明匹配失败
        if (i != input.length() && p >= pattern.length()) return false;
        // 模式串未结束，匹配串有可能结束有可能未结束

        // p位置的下一个字符中为*号
        if (p + 1 < pattern.length() && pattern.charAt(p + 1) == '*') {
            // 匹配串已经结束
            if (i >= input.length()) return matchCore(input, i, pattern, p + 2);
            // 匹配串还没有结束
            else {
                if (pattern.charAt(p) == input.charAt(i) || pattern.charAt(p) == '.') {
                    return // 匹配串向后移动一个位置，模式串向后移动两个位置
                            matchCore(input, i + 1, pattern, p + 2)
                                    // 匹配串向后移动一个位置，模式串不移动
                                    || matchCore(input, i + 1, pattern, p)
                                    // 匹配串不移动，模式串向后移动两个位置
                                    || matchCore(input, i, pattern, p + 2);
                } else {
                    return matchCore(input, i, pattern, p + 2);
                }
            }
        }

        // 匹配串已经结束
        if (i >= input.length()) return false;
        // 匹配串还没有结束
        else {
            if (input.charAt(i) == pattern.charAt(p) || pattern.charAt(p) == '.') {
                return matchCore(input, i + 1, pattern, p + 1);
            }
        }
        return false;
    }

    public static void main(String[] args) {
        Offer53 o53 = new Offer53();
        String s1 = "aa", p1 = "a";
        System.out.println("false: " + o53.match(s1, p1));
        String s2 = "aa", p2 = "a*";
        System.out.println("true: " + o53.match(s2, p2));
        String s3 = "ab", p3 = ".*";
        System.out.println("true: " + o53.match(s3, p3));
        String s4 = "aab", p4 = "c*a*b";
        System.out.println("true: " + o53.match(s4, p4));
        String s5 = "mississippi", p5 = "mis*is*p*.";
        System.out.println("false: " + o53.match(s5, p5));
    }
}

enum Result {
    TRUE, FALSE
}
