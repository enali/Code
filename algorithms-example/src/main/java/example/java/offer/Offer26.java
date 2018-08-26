package example.java.offer;

/**
 * 题目:
 * 输入一个复杂链表（每个节点中有节点值，以及两个指针，一个指向下一个节点，另一个特殊指针指向任意一个节点），
 * 返回结果为复制后复杂链表的head。（注意，输出结果中请不要返回参数中的节点引用，否则判题程序会直接返回空
 */
public class Offer26 {
    private static class RandomListNode {
        int label;
        RandomListNode next = null;
        RandomListNode random = null;

        RandomListNode(int label) {
            this.label = label;
        }
    }

    public RandomListNode clone(RandomListNode pHead)
    {
        if (pHead == null) return null;

        copy(pHead);  // 复制
        connect(pHead);  // 连接
        return split(pHead);  // 分开
    }

    /**
     * 根据原始链表的每个结点N 创建对应的N’。把N’链接在N的后面
     */
    private void copy(RandomListNode head) {
        while (head != null) {
            RandomListNode n = new RandomListNode(head.label);
            n.next = head.next;
            head.next = n;
            head = n.next;
        }
    }

    /**
     * 设置复制出来的结点的sibling。假设原始链表上的N的sibling指向结点S，那么其对应复制出来的N’
     * 是N的pext指向的结点，同样S’也是S的next指向的结点。
     */
    private void connect(RandomListNode head) {
        while (head != null) {
            if (head.random != null)
                head.next.random = head.random.next;
            head = head.next.next;
        }
    }

    /**
     * 把这个长链表拆分成两个链表。把奇数位置的结点用next .
     * 链接起来就是原始链表，把偶数位置的结点用next 链接起来就是复制
     * 出来的链表。
     */
    private RandomListNode split(RandomListNode head) {
        if (head == null) return null;
        RandomListNode newHead = head.next;
        RandomListNode tmp = head.next;
        while (tmp.next != null) {
            head.next = tmp.next;
            head = head.next;
            tmp.next = head.next;
            tmp = tmp.next;
        }
        head.next = null;
        return newHead;
    }

}
