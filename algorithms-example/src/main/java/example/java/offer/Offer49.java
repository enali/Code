package example.java.offer;

/**
 * 题目:
 * 实现一个函数stringToInt,实现把字符串转换成整数这个功能，不能使用atoi或者其他类似的库函数。
 */
public class Offer49 {
    public static int strToInt(String str) {
        if (str == null || str.isEmpty()) return 0;
        int len = str.length();
        int left = 0;

        while (left < len && str.charAt(left) == ' ') left++;  // 过滤前面的空字符
        if (left == len) return 0;
        char c = str.charAt(left);
        boolean positive = true;
        if (c == '+' || c == '-') {
            left++;
            if (c == '-') positive = false;
        }

        int right = len-1;
        while (right > left && str.charAt(right) == ' ') right--;  // 过滤后面的空字符

        for (int i=left; i<=right; i++) {
            c = str.charAt(i);
            if (c < '0' || c > '9') return 0;  // 非数字字符
        }

        while (left <= right && str.charAt(left) == '0') left++;
        if (left > right) return 0;  // 字符全0

        // 初步溢出决断, 依赖平台知识
        len = right - left + 1;
        if (len > 10) return 0;  // 依赖了32位int值的最大位数为10位
        else if (len == 10) {
            if (str.charAt(left) > 2) return 0;  // 最高位最大为2
            else if (str.charAt(left) == 2 && str.charAt(left + 1) > 1) return 0;  // 次高位最大为1
        }

        int sum = 0;
        if (positive) {
            for (int i=left; i<=right; i++) {
                sum *= 10;
                sum += str.charAt(i) - '0';
            }
        } else {
            for (int i=left; i<=right; i++) {
                sum *= 10;
                sum -= str.charAt(i) - '0';
            }
        }

        if (positive) {
            if (sum < 0) return 0;  // 溢出
            else return sum;
        } else {
            if (sum > 0) return 0;  // 溢出
            else return sum;
        }
    }

    public static void main(String[] args) {
        String s = "+2147483647";
        System.out.println(strToInt(s));
    }
}
