package example.java.other;

import java.util.Stack;

/**
 * 将中缀表达式转换为后缀表达式(逆波兰表达式)
 */
public class PostfixExpr {
    public static String postSuffix(String expr) {
        char s = '\0';
        StringBuilder sb = new StringBuilder();
        int idx = 0;
        while (idx < expr.length()) {
            char c = expr.charAt(idx++);
            if (c >= '0' && c <= '9') sb.append(c);
            // * / 优先级高, 则先连数字, 再连操作符
            else if (c == '*' || c == '/') {
                sb.append(expr.charAt(idx++));
                sb.append(c);
            }
            // + - 优先级低, 则先保留操作符
            else if (c == '+' || c == '-') {
                if (s == '\0') s = c;
                else {
                    sb.append(s);
                    s = c;
                }
            }
        }
        if (s != '\0') sb.append(s);
        return sb.toString();
    }

    // 利用一个符号栈计算后缀表达式
    public static int calc(String expr) {
        Stack<Integer> s = new Stack<>();
        for (int i=0; i<expr.length(); i++) {
            char c = expr.charAt(i);
            if (c >= '0' && c <= '9') s.push(c - '0');
            else if (c == '+' || c == '-' || c == '*' || c == '/') {
                int v2 = s.pop();
                int v1 = s.pop();
                if (c == '+') s.push(v1 + v2);
                else if (c == '-') s.push(v1 - v2);
                else if (c == '*') s.push(v1 * v2);
                else if (c == '/') s.push(v1 / v2);
            }
        }
        return s.pop();
    }

    public static void main(String[] args) {
        String expr1 = "1+3*4";
        String expr1S = postSuffix(expr1);
        System.out.println(calc(expr1S));

        String expr2 = "1*3+4";
        String expr2S = postSuffix(expr2);
        System.out.println(calc(expr2S));

        String expr3 = "1+3*4*5-7-8";
        String expr3S = postSuffix(expr3);
        System.out.println(calc(expr3S));
    }
}

