package cn.enali.utils;

// 链表的节点
public class ListNode<T> {
    public final T val;  // 不可变
    public ListNode<T> next;

    public ListNode(T value) {
        this.val = value;
        this.next = null;
    }
}
