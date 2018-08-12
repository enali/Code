package example.java.offer;

import java.util.Stack;

/**
 * 题目:
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小素的min 函数。在该栈中，调用min、push 及pop的时间复杂度都是0(1)
 *
 * 思路:
 * 定义一个栈mins用来存储当前栈中的最小元素, 在push下一个元素时, 先跟mins最顶上的元素比较, 如果小, 则压入元素, 否则, 压入
 * mins栈顶的元素
 */
public class Offer21 {
    Stack<Integer> data = new Stack<>();
    Stack<Integer> mins = new Stack<>();

    public int min() {
        return mins.peek();
    }

    public void push(int ele) {
        data.push(ele);
        if (mins.isEmpty()) mins.push(ele);
        else if (ele > mins.peek()) mins.push(mins.peek());
        else mins.push(ele);
    }

    public int pop() {
        mins.pop();
        return data.pop();
    }

    public static void main(String[] args) {
        Offer21 o21 = new Offer21();

        o21.push(9);
        o21.push(3);
        o21.push(6);
        o21.push(0);

        System.out.println(o21.min());  // 0
        System.out.println(o21.pop());  // 0
        System.out.println(o21.min());  // 3
        System.out.println(o21.pop());  // 6
        System.out.println(o21.min());  // 3
        System.out.println(o21.pop());  // 3
        System.out.println(o21.min());  // 9
        System.out.println(o21.pop());  // 9
    }
}
