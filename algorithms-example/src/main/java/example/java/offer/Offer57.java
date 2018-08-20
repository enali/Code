package example.java.offer;


import cn.enali.utils.ListNode;
import cn.enali.utils.ListUtils;

import java.util.Arrays;

/**
 * 题目:
 * 在一个排序的链表中，存在重复的结点，请删除该链表中重复的结点，重复的结点不保留，返回链表头指针。
 * 例如，链表1->2->3->3->4->4->5 处理后为 1->2->5
 */
public class Offer57 {
    public static <T> ListNode<T> deleteDuplication(ListNode<T> head) {
        while (head != null && head.next != null && head.val.equals(head.next.val)) {
            while (head.next != null && head.val.equals(head.next.val))
                head = head.next;
            head = head.next;
        }
        if (head == null && head.next == null) return head;

        ListNode<T> cur = head;
        ListNode<T> tmp = null;
        while (cur.next != null) {
            tmp = cur.next;
            while (tmp.next != null && tmp.val.equals(tmp.next.val)) tmp = tmp.next;
            if (tmp == cur.next) cur = tmp;
            else cur.next = tmp.next;
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode<Integer> head = ListUtils.createLinkedList(Arrays.asList(1,2,3,3,4,4,5));
        ListNode<Integer> newHead = deleteDuplication(head);
        System.out.println(ListUtils.mkString(newHead));
    }
}
