package example.java.offer;


import cn.enali.utils.ListNode;

import java.util.*;

/**
 * 题目:
 * 输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
 * 逆向输出链表
 */
public class Offer05 {
    /**
     * 使用栈来逆向输出单向链表.
     *
     * @param listNode 表头
     * @return 表的逆向遍历值
     */
    public static <T> ArrayList<T> printListFromTailToHead(ListNode<T> listNode) {
        ArrayList<T> rst = new ArrayList<>();
        if (listNode == null) return rst;

        Stack<ListNode<T>> s = new Stack<>();
        while (listNode != null) {
            s.push(listNode);
            listNode = listNode.next;
        }

        while (!s.isEmpty()) {
            rst.add(s.pop().val);
        }
        return rst;
    }

    // 使用递归的方式
    public static <T> ArrayList<T> printListFromTailToHead2(ListNode<T> head) {
        ArrayList<T> rst = new ArrayList<>();
        if (head != null) {
            // 先添加后面的值, 再添加自己的值
            rst.addAll(printListFromTailToHead2(head.next));
            rst.add(head.val);
        }
        return rst;
    }

    public static void main(String[] args) {
        ListNode<Integer> node1 = new ListNode<>(1);
        ListNode<Integer> node2 = new ListNode<>(2);
        ListNode<Integer> node3 = new ListNode<>(3);
        ListNode<Integer> node4 = new ListNode<>(4);
        ListNode<Integer> node5 = new ListNode<>(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;

        List<Integer> rst1 = printListFromTailToHead(node1);
        System.out.println(rst1);
        List<Integer> rst2 = printListFromTailToHead2(node1);
        System.out.println(rst2);
    }
}
