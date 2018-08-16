package example.java.offer;

/**
 * 题目:
 * 输入一个英文句子，翻转句子中单词的顺序，但单词内字啊的顺序不变。
 *
 * 思路:
 * 第一步翻转句子中所有的字符。比如翻转“I am a student. ”中所有的字符得到”.tneduts a m a I”，
 * 此时不但翻转了句子中单词的顺序，连单词内的字符顺序也被翻转了。第二步再翻转每个单词中字符的顺序，就得到了”student. a am I”。
 */
public class Offer42 {
    public static String reverseSentence(String str) {
        if (str == null) return null;
        int len = str.length();
        if (len <= 1) return str;

        char[] cs = str.toCharArray();
        int left = 0, right = cs.length - 1;
        while (left < right) exch(cs, left++, right--);
        left = 0;
        while (right < len-1) {
            while (left < len && cs[left] == ' ') left++;  // 定位word的左界
            right = left;
            while (right < len && cs[right] != ' ') right++;  // 定位word的右界
            int tmp = right;  // 开始下一个word的搜索
            right--;
            while (left < right) exch(cs, left++, right--);  // 反转word
            left = tmp;
        }
        return String.valueOf(cs);  // 将字符数组转为字符串
    }

    private static void exch(char[] ary, int p, int q) {
        if (p == q) return;
        char tmp = ary[p];
        ary[p] = ary[q];
        ary[q] = tmp;
    }

    public static void main(String[] args) {
        String str = "I am a student.";
        System.out.println(reverseSentence(str));
    }
}