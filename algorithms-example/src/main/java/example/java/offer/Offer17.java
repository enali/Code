package example.java.offer;

import cn.enali.utils.ListNode;
import cn.enali.utils.ListUtils;

import java.util.Arrays;

/**
 * 题目:
 * 输入两个单调递增的链表，输出两个链表合成后的链表，当然我们需要合成后的链表满足单调不减规则。
 *
 * 思路:
 * 有个虚拟节点的方式, 但是如果声明方法支持泛型, 则需要实例化一个`ListNode<T>`, 因此无法使用.
 *
 * 总是假设list1的首节点值小, list1和p1分别指向尚未合并的链表中, 可合并的头/尾结点, list2和p2同.
 * 先以list2的首节点值为比较值, 移动p1, 合并. 再以list1的首节点值为比较值, 移动p2, 合并.
 */
public class Offer17 {
    // 只要T是可比较就OK
    public static <T extends Comparable<T>> ListNode<T> merge(ListNode<T> list1, ListNode<T> list2) {
        if (list1 == null) return list2;
        if (list2 == null) return list1;

        ListNode<T> curHead = null, curLast = null;
        // 总是默认list1的首节点val小
        if (list2.val.compareTo(list1.val) < 0) {
            curHead = list2; list2 = list1; list1 = curHead;  // 交换
        }
        curHead = list1;  // list1一定是新链表的头

        ListNode<T> p1 = list1, p2 = list2;  // p1/p2分别表示当前链表可合并部分的尾指针
        // list1总是指向尚未合并的链表list1的表头, list2则总是指向尚未合并的链表list2的表头
        while (list2 != null) {  // list1不可能为null
            // p1当前值总是小, 移动p1到idx, idx的下一个节点值要大
            while (p1.next != null && p1.next.val.compareTo(list2.val) <= 0) p1 = p1.next;
            if (curLast != null) curLast.next = list1;
            curLast = p1;
            list1 = p1.next;
            p1 = list1;

            // list1为空则结束
            if (list1 == null) break;

            // 经过上面, p2当前值总是小, 移动p2到idx, idx的下一个节点值要大
            while (p2.next != null && p2.next.val.compareTo(list1.val) <= 0) p2 = p2.next;
            curLast.next = list2;  // 经过上面, curLast不可能为null
            curLast = p2;
            list2 = p2.next;
            p2 = list2;
        }

        if (list2 == null) curLast.next = list1;
        else curLast.next = list2;

        return curHead;
    }

    public static void main(String[] args) {
        ListNode<Integer> list1 = ListUtils.createLinkedList(Arrays.asList(1,3,5,7,9));
        ListNode<Integer> list2 = ListUtils.createLinkedList(Arrays.asList(2,4,6,8,10));
        ListNode<Integer> curList = merge(list1, list2);
        System.out.println(ListUtils.mkString(curList));
    }
}
