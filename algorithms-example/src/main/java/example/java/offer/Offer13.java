package example.java.offer;


import cn.enali.utils.ListNode;

/**
 * 题目:
 * 给定单向链表的头指针和一个结点指针, 定义一个函数在O(1)时间删除该结点.
 *
 * 思考:
 * 1, 如果要删除的结点是头结点, 直接返回头结点的next
 * 2, 如果要删除的结点是尾结点, 找到倒数第2个结点
 * 3, 如果要删除的结点是中间结点, 将下一个结点的值赋予给要删除的节点, 然后删除下一个节点
 */
public class Offer13 {
    public static <T> ListNode<T> deleteNode(ListNode<T> head, ListNode<T> node) {
        if (head == null || node == null) return head;

        if (head == node) return head.next;

        if (node.next == null) {  // 尾结点
            ListNode<T> tmp = head;
            while (tmp.next != node) tmp = tmp.next;
            tmp.next = null;
        } else {  // 这里特别取巧
            node.val = node.next.val;
            node.next = node.next.next;
        }
        return head;
    }
}
