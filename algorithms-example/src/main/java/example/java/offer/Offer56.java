package example.java.offer;

import cn.enali.utils.ListNode;
import cn.enali.utils.ListUtils;

import java.util.Arrays;

/**
 * 题目:
 * 一个链表中包含环，如何找出环的入口结点？
 */
public class Offer56 {
    public static <T> ListNode<T> entryNodeOfLoop(ListNode<T> head) {
        if (head == null || head.next == null) return null;

        // 通过快慢指针检测环, 并定位到环内节点
        ListNode<T> fast = head, slow = head;
        do {
            fast = fast.next.next;
            slow = slow.next;
        } while (fast != null && fast != slow);  // fast快, 若无环, 则先Null
        if (fast == null) return null;  // 无环

        // 计算环的长度
        int cnt = 0;
        do {
            fast = fast.next;
            cnt++;
        } while (fast != slow);

        // 从头出发, 让fast指向离起始长度为环长的位置
        fast = head;
        slow = head;
        for (int i=0; i<cnt; i++) fast = fast.next;

        // 定位入口节点
        while (fast != slow) {
            fast = fast.next;
            slow = slow.next;
        }
        return fast;
    }

    public static void main(String[] args) {
        ListNode<Integer> head = ListUtils.createLoopList(
                Arrays.asList(1,2,3,4,5,6,7,8),
                4
        );
        System.out.println(entryNodeOfLoop(head));
    }
}
