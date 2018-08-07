package example.java.offer;

import cn.enali.utils.ListNode;
import cn.enali.utils.ListUtils;

import java.util.Arrays;

/**
 * 题目:
 * 输入一个链表，输出该链表中倒数第k个结点。
 *
 * 思路:
 * 用两个相隔k-1的指针left/right, 当right处于结束时, 则left即所求
 */
public class Offer15 {
    public static <T> ListNode<T> findKthToTail(ListNode<T> head, int k) {
        if (head == null || k <= 0) return null;

        ListNode<T> left = head;
        ListNode<T> right = head;

        for (int i=1; i<k; i++) {
            // 初始right不为null, 且每次赋值right.next也不为null, 所以right肯定不为null
            if (right.next == null) return null;
            right = right.next;
        }

        // 同时移动
        while (right.next != null) {
            right = right.next;
            left = left.next;
        }
        return left;
    }

    public static void main(String[] args) {
        ListNode<Integer> list = ListUtils.createLinkedList(Arrays.asList(1,2,3,4));

        ListNode<Integer> node = findKthToTail(list, 1);
        System.out.println(node.val);  // 4
        ListNode<Integer> node4 = findKthToTail(list, 4);
        System.out.println(node4.val);  // 1
        ListNode<Integer> node6 = findKthToTail(list, 6);
        System.out.println(node6);  // null
    }
}
