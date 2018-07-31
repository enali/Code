package example.java.leetcode;

public class Test31 {
    public String ReverseSentence(String str) {
        if (str == null) return null;
        int len = str.length();
        if (len <= 1) return str;

        char[] cs = str.toCharArray();
        int left = 0, right = cs.length - 1;
        while (left < right) exch(cs, left++, right--);
        left = 0;
        while(right < len-1) {
            while (left < len && cs[left] == ' ') left++;
            right = left;
            while (right < len && cs[right] != ' ') right++;
            int tmp = right;
            right--;
            while (left < right) exch(cs, left++, right--);
            left = tmp;
        }
        return String.valueOf(cs);
    }

    private void exch(char[] ary, int p, int q) {
        if (p == q) return;
        char tmp = ary[p];
        ary[p] = ary[q];
        ary[q] = tmp;
    }

    public static void main(String[] args) {
        String str = "I am a student.";
        Test31 t32 = new Test31();
        System.out.println(t32.ReverseSentence(str));
    }
}