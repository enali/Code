package cn.enali.utils;

// 单向链表的节点
public class ListNode<T> {
    public T val;  // 不可变
    public ListNode<T> next;

    public ListNode(T value) {
        this.val = value;
        this.next = null;
    }

    @Override
    public String toString() {
        return "ListNode[ " + val + " ]";
    }
}
