package example.java.offer;

import cn.enali.utils.ListNode;

/**
 * 题目:
 * 输入两个链表，找出它们的第一个公共结点。
 *
 * 思路:
 * 有公共结点的两个链表, 组成Y字型. 先遍历每条链表求出长度, 根据长度差, 将两个链表的头对齐, 再同步前进
 */
public class Offer37 {
    public static <T> ListNode<T> findFirstCommonNode(ListNode<T> head1, ListNode<T> head2) {
        if (head1 == null || head2 == null) return null;
        ListNode<T> tmp = null;

        int len1 = 0;
        tmp = head1;
        while (tmp != null) {
            len1++;
            tmp = tmp.next;
        }
        int len2 = 0;
        tmp = head2;
        while (tmp != null) {
            len2++;
            tmp = tmp.next;
        }
        // 保证head1是最长
        if (len1 < len2) {
            tmp = head1;
            head1 = head2;
            head2 = tmp;
        }
        int cha = Math.abs(len1 - len2);
        for (int i=0; i<cha; i++) head1 = head1.next;
        while (head1 != head2) {
            head1 = head1.next;
            head2 = head2.next;
        }
        return head1;
    }

    public static void main(String[] args) {
        ListNode<Integer> p1 = new ListNode<>(1);
        ListNode<Integer> p2 = new ListNode<>(2);
        ListNode<Integer> p3 = new ListNode<>(3);
        ListNode<Integer> p4 = new ListNode<>(4);
        ListNode<Integer> p5 = new ListNode<>(5);
        ListNode<Integer> p6 = new ListNode<>(6);
        ListNode<Integer> p7 = new ListNode<>(7);
        p1.next = p2;
        p2.next = p3;
        p3.next = p6;
        p6.next = p7;
        p4.next = p5;
        p5.next = p6;
        System.out.println(findFirstCommonNode(p1, p4));
    }
}
