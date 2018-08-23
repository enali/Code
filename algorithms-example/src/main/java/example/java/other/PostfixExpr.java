package example.java.other;

import java.util.Stack;

/**
 * 将中缀表达式转换为后缀表达式(逆波兰表达式)
 *
 * 思路:
 * 1, 比如, 3 * 4 * 5 * 6, 连续3个高优先级的*, 要转化为34*5*6*, 第1个*要入栈,
 * 在第2个*前, 把第1个*加入表达式, 把第2个压栈, 在第3个*前, 把栈中的第2个*加入表达式, 把第3个压栈, 在结尾处理时, 把栈中运算符加入表达式
 *
 * 2, 比如, 3 * 4 + 5 * 6, 第1个*入栈, 第2个+时, 把栈中的*加入表达式, 把第2个+入栈, 第3个*时, 栈中有+, 但优先级低, 把第3个*入栈
 * 结束时, 把栈中所有运算符加入表达式, 3 4 * 5 6 * +
 *
 * 3, 比如, 3 * (4 + 5 * 6) + 7, 遇到')'时, 要处理到'('前的所有符号
 */
public class PostfixExpr {
    public static String postfix(String expr) {
        Stack<Character> s = new Stack<>();  // 符号栈
        StringBuilder sb = new StringBuilder();  // 中缀表达式
        for (int i=0; i<expr.length(); i++) {
            char c = expr.charAt(i);
            if (c >= '0' && c <= '9') sb.append(c);  // 数字则直接加入中缀表达式
            else if (c == '(') s.push(c);  // '('直接入栈
            // 对于运算符, 总是把栈中'('前的所有大于等于其优先级的运行符加入表达式
            else if (c == '*' || c == '/') {  // 把之前的*/加入表达式
                while (!s.isEmpty()) {
                    char tmp = s.peek();
                    if (tmp == '+' || tmp == '-') break;
                    else if (tmp == '(') break;
                    else if (tmp == '*' || tmp == '/') sb.append(s.pop());
                }
                s.push(c);  // 确保当前栈中, '('后, 只有1个*/运算符
            } else if (c == '+' || c == '-') {  // 把之前的+-*/加入表达式
                while (!s.isEmpty()) {
                    char tmp = s.peek();
                    if (tmp == '+' || tmp == '-' || tmp == '*' || tmp == '/') sb.append(s.pop());
                    else if (tmp == '(') break;
                }
                s.push(c);  // 确保当前栈中, '('后, 只有1个+-运算符
            } else if (c == ')') {  // 弹出'('前的所有运算符
                char tmp;
                do {
                    tmp = s.peek();
                    if (tmp == '(') {
                        s.pop();
                        break;
                    }
                    sb.append(s.pop());
                } while (true);
            }
        }
        while (!s.isEmpty()) {
            char tmp = s.pop();
            sb.append(tmp);
        }
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
        String expr1S = postfix(expr1);
        System.out.println(expr1S);

        String expr2 = "1*3+4";
        String expr2S = postfix(expr2);
        System.out.println(expr2S);

        String expr3 = "1+3*4*5-7-8";
        String expr3S = postfix(expr3);
        System.out.println(expr3S);

        String expr4 = "1+(3*5-7)-8";
        String expr4S = postfix(expr4);
        System.out.println(expr4S);

        String expr5 = "1+(3*4*5-7-8)";
        String expr5S = postfix(expr5);
        System.out.println(expr5S);
    }
}

