package cn.enali.utils;

import java.util.List;

public class ListUtils {
    public static <T> String mkString(ListNode<T> head, String delimeter) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val + delimeter);
            head = head.next;
        }
        return sb.toString().substring(0, sb.length() - delimeter.length());
    }

    // 以递归的方式从数组中创建单向链表
    public static <T> ListNode<T> createLinkedList(List<T> data) {
        if (data.isEmpty()) {
            return null;
        }
        ListNode<T> firstNode = new ListNode<>(data.get(0));
        firstNode.next = createLinkedList(data.subList(1, data.size()));
        return firstNode;
    }

    // 以递归的方式进行单向链表的逆转
    public static <T> ListNode<T> reverseLinkedListRec(ListNode<T> head) {
        // size == 0 or size == 1
        if (head == null || head.next == null) return head;
        ListNode<T> newHead = reverseLinkedListRec(head.next);
        head.next.next = head;
        head.next = null;
        return newHead;
    }

    // 以循环的方式进行链表的逆转
    public static <T> ListNode<T> reverseLinkedListLoop(ListNode<T> head) {
        ListNode<T> newHead = null;
        // newHead永远指向已逆转链表的表头
        // head则永远指向尚未逆转链表的表头
        while (head != null) {
            // loop invariant holds
            ListNode<T> p = head.next;
            head.next = newHead;
            newHead = head;
            head = p;
            // loop invariant holds
        }
        return newHead;
    }

    // 以循环的方式, 从链表中删除特定值的节点
    // 头结点没有prev, 1, 特殊处理; 2, 增加虚拟头结点
    public static <T> ListNode<T> deleteIfEquals(ListNode<T> head, T val) {
        // handle the head if head's val equals val
        while (head != null && head.val.equals(val)) {
            head = head.next;
        }
        ListNode<T> prev = head;
        while (prev != null && prev.next != null) {
            if (prev.next.val.equals(val)) {
                prev.next = prev.next.next;
            } else {
                prev = prev.next;
            }
        }
        return prev;
    }
}
