package example.java.offer;


import java.util.ArrayList;
import java.util.Stack;

/**
 * 输入一个链表，按链表值从尾到头的顺序返回一个ArrayList。
 */
public class Offer05 {
    static class ListNode {
        int val;
        ListNode next;

        public ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    /**
     * 使用栈来逆向输出单向链表.
     *
     * @param listNode 表头
     * @return 表的逆向遍历值
     */
    public ArrayList<Integer> printListFromTailToHead(ListNode listNode) {
        ArrayList<Integer> rst = new ArrayList<>();
        if (listNode == null) return rst;

        Stack<ListNode> s = new Stack<>();
        while (listNode != null) {
            s.push(listNode);
            listNode = listNode.next;
        }

        while (!s.isEmpty()) {
            rst.add(s.pop().val);
        }
        return rst;
    }
}
