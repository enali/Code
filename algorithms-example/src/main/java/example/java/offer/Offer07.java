package example.java.offer;

import java.util.Stack;

/**
 * 题目：用两个栈实现一个队列。队列的声明如下，请实现它的两个函数appendTail 和deleteHead，分别完成在队列尾部插入结点和在队列头部删除结点的功能。
 */
public class Offer07 {
    Stack<Integer> s1 = new Stack<>();
    Stack<Integer> s2 = new Stack<>();

    public void push(int node) {
        s1.push(node);
    }

    /**
     * 如果 s2空,则把 s1的所有元素倒腾到 s2, 再弹出
     * @return
     */
    public int pop() {
        if (s2.isEmpty()) {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }

        if (s2.isEmpty())
            throw new RuntimeException("No more element");

        return s2.pop();
    }
}
