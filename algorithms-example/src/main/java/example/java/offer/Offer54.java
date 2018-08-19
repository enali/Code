package example.java.offer;

public class Offer54 {
    public static boolean isNumeric(char[] str) {
        if (str == null || str.length == 0) return false;
        int i = 0;
        if (str[i] == '+' || str[i] == '-') i++;  // 过滤看最开始的'+'/'-'
        if (i == str.length) return false;  // 即只有一个'+'或'-'
        while (i < str.length && str[i] >= '0' && str[i] <= '9') i++;
        if (i == str.length) return true;  // 即纯整数
        if (str[i] == '.') {  // 可能是小数
            i++;
            while (i < str.length && str[i] >= '0' && str[i] <= '9') i++;
            if (i == str.length) return true;
        }
        if (str[i] == 'e' || str[i] == 'E') {  // 可能是科学计数法
            i++;
            if (i == str.length) return false;  // 如12e, 以e结尾
            if (str[i] == '+' || str[i] == '-') i++;
            int tmp = i;
            while (i < str.length && str[i] >= '0' && str[i] <= '9') i++;
            if (tmp == i) return false;  // e后面无数字, 但有别的符号
            return i == str.length;  // 数字后, 不应该出现任何符号
        }
        return false;  // 不是字符e, 却没结束
    }

    public static void main(String[] args) {
        String[] symNum = {
                "+100", "5e2", "-123", "3.1416", "-1E-16"
        };
        String[] symNotNum = {
                "12e", "1a3.14", "1.2.3", "+-5", "12e+4.3"
        };
        System.out.println("==== true ====");
        for (String sym : symNum) System.out.println(isNumeric(sym.toCharArray()));
        System.out.println("==== false ====");
        for (String sym : symNotNum) System.out.println(isNumeric(sym.toCharArray()));
    }
}
