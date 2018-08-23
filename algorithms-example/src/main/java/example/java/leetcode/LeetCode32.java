package example.java.leetcode;

import java.util.Stack;

/**
 * 题目:
 * 找最长的有效括号序列
 */
public class LeetCode32 {
    public static int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();  // 存放'('的索引
        int max = 0;  // 最长长度
        stack.push(-1);  // stack中至少存储1个数字, 表示有效序列'('前的索引, 初始自然为-1
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') stack.push(i);  // 存储索引
            else if (c == ')') {
                stack.pop();  // 总是弹出对应的(的索引
                if (stack.isEmpty()) stack.push(i);  // stack空, 表示有多个), 则此时应存储)的索引作为(前的索引
                else max = Math.max(max, i - stack.peek());  // stack不空, 则至少存储1个索引, 表示(前的索引
            } else {  // 遇到非括号的字符
                stack.clear();  // 清空
                stack.push(i);  // 存储下索引
            }
        }
        return max;
    }

    public static void main(String[] args) {
        String s1 = "(()";
        System.out.println(longestValidParentheses(s1));  // 2
        String s2 = ")()())";
        System.out.println(longestValidParentheses(s2));  // 4
    }
}
