package example.java.offer;

import cn.enali.utils.ListNode;
import cn.enali.utils.ListUtils;

import java.util.Arrays;
import java.util.Collections;

/**
 * 题目:
 * 输入一个链表，反转链表后，输出新链表的表头。
 */
public class Offer16 {
    /**
     * 总是以newHead指向已经反转好的链表的表头, 以head指向尚未反转好的链表的表头, 每次从head上取一个节点
     */
    public static <T> ListNode<T> reverseList(ListNode<T> head) {
        ListNode<T> newHead = null;
        while (head != null) {
            ListNode<T> tmp = head.next;
            head.next = newHead;
            newHead = head;
            head = tmp;
        }
        return newHead;
    }

    /**
     * 尾插法, 以left指向0节点, right指向0的下一个节点, 总是将right节点插入head前
     */
    public static <T> ListNode<T> reverseList2(ListNode<T> head) {
        if (head == null || head.next == null) return head;
        ListNode<T> left = head, right = head.next;
        while (right != null) {
            left.next = right.next;
            right.next = head;
            head = right;
            right = left.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode<Integer> head = ListUtils.createLinkedList(Arrays.asList(1,2,3));
        ListNode<Integer> newHead = reverseList(head);
        System.out.println(ListUtils.mkString(newHead, ","));

        ListNode<Integer> headnull = ListUtils.createLinkedList(Collections.emptyList());

        ListNode<Integer> newHeadNull = reverseList(headnull);
        System.out.println(ListUtils.mkString(newHeadNull, ","));
    }
}
