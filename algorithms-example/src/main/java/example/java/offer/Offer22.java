package example.java.offer;

import java.util.Stack;

/**
 * 题目：
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。
 * 可假设数字都不相同
 *
 * 思路:
 * 用真实栈模拟
 */
public class Offer22 {
    public static boolean isPopOrder(int[] push, int[] pop) {
        if (push == null || pop == null) return false;
        // 如果都为空数组, 则为true
        if (push.length <= 0 && push.length == pop.length) return true;
        if (push.length != pop.length) return false;
        Stack<Integer> s = new Stack<>();
        int popIdx = 0;
        // 不断压入数据
        for (int i=0; i<push.length; i++) {
            s.push(push[i]);
            // 如果可以弹出(即在弹栈序列中), 则一直弹出
            while (popIdx < pop.length && s.peek() == pop[popIdx]) {
                s.pop();
                popIdx++;
            }
        }
        return s.isEmpty();
    }

    public static void main(String[] args) {
        int[] push = {3, 4, 2, 7};
        int[] pop = {2, 4, 7, 3};
        System.out.println(isPopOrder(push, pop));
    }

}
