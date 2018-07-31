package example.java;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 演算计算表达式， 支持 加减乘除， 和括号
 */
public class ExprEvaluation {
    /**
     * Support normal(infix) expression, such as:
     * 1 + 2, (1 + 2) * 3, 2 * 3 + 7 - 6
     *
     * 思路：用两个栈：数字栈和操作符栈
     * 遇到数字就压栈， 遇到操作符，先比较跟当前栈顶的操作符，如果大则压栈，如果小则先计算再压栈
     *
     * @param expr the expr needed to calculate, can't be empty, or include unsupported char
     * @return the calculate result
     */
    public static long eval(String expr) {
        expr = expr.trim();
        if (expr.isEmpty()) throw new IllegalArgumentException("the expr is empty!");

        // operator stack, includes: +-*/()
        Stack<Character> op = new Stack<>();
        // number stack
        Stack<Long> od = new Stack<>();

        int idx = 0;
        int len = expr.length();
        while (idx < len) {
            char c = expr.charAt(idx);
            if (c == '(') {
                op.push(c);
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                // the current operator's level
                int curOpLevel = opLevel.get(c);
                while (true) {
                    int stackOpLevel = 0;
                    // the top operator level
                    if (!op.isEmpty()) stackOpLevel = opLevel.get(op.peek());
                    if (curOpLevel > stackOpLevel) {
                        op.push(c);
                        break;
                    }
                    // if current level less than the top, then calculate
                    else {
                        char optmp = op.pop();
                        long num2 = od.pop();
                        long num1 = od.pop();
                        od.push(calcResult(optmp, num1, num2));
                    }
                }
            } else if (c == ')') {
                // calculate until pop the '(' operator
                while (true) {
                    char optmp = op.pop();
                    if (optmp == '(') {
                        break;
                    } else {
                        long num2 = od.pop();
                        long num1 = od.pop();
                        od.push(calcResult(optmp, num1, num2));
                    }
                }
            }
            // push number
            else if (c >= '0' && c <= '9') {
                int endIdx = idx + 1;
                while (endIdx < len && expr.charAt(endIdx) >= '0' && expr.charAt(endIdx) <= '9') {
                    endIdx++;
                }
                long num = Long.parseLong(expr.substring(idx, endIdx));
                od.push(num);
                idx = endIdx - 1;
            }
            // just allow: +-*/0123456789(), include ' '(space)
            else if (c != ' ') {
                throw new IllegalArgumentException("unsupported char: " + c);
            }
            idx++;
        }
        while (!op.isEmpty()) {
            char optmp = op.pop();
            long num2 = od.pop();
            long num1 = od.pop();
            od.push(calcResult(optmp, num1, num2));
        }
        return od.pop();
    }

    private static long calcResult(char op, long num1, long num2) {
        switch (op) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                return num1 / num2;
            default:
                return 0;
        }
    }

    private static Map<Character, Integer> opLevel;
    static {
        opLevel = new HashMap<>();
        opLevel.put('(', 0);
        opLevel.put('+', 1); opLevel.put('-', 1);
        opLevel.put('*', 2); opLevel.put('/', 2);
    }
}
