package example.java.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class LinkedListReverser {
    // 以递归的方式进行单向链表的逆转
    public <T> Node<T> reverseLinkedList(Node<T> head) {
        // size == 0 or size == 1
        if (head == null || head.getNext() == null) return head;
        Node<T> newHead = reverseLinkedList(head.getNext());
        head.getNext().setNext(head);
        head.setNext(null);
        return newHead;
    }

    // 以循环的方式进行链表的逆转
    public <T> Node<T> reverseLinkedList2(Node<T> head) {
        Node<T> newHead = null;
        // newHead永远指向已逆转链表的表头
        // head则永远指向尚未逆转链表的表头
        while (head != null) {
            // loop invariant holds
            Node<T> p = head.getNext();
            head.setNext(newHead);
            newHead = head;
            head = p;
            // loop invariant holds
        }
        return newHead;
    }

    public static void main(String[] args) {
        LinkedListCreator lc = new LinkedListCreator();
        LinkedListReverser lr = new LinkedListReverser();

        Node<Integer> n1 = lc.createLinkedList(new ArrayList<>());
        // 单元素链表的创建， 推荐singletonList
        Node<Integer> n2 = lc.createLinkedList(Collections.singletonList(1));
        Node<Integer> n3 = lc.createLinkedList(Arrays.asList(1, 2, 3));

        Node<Integer> nn1 = lr.reverseLinkedList(n1);
        Node<Integer> nn2 = lr.reverseLinkedList(n2);
        Node<Integer> nn3 = lr.reverseLinkedList(n3);

        Node.printLinkedList(nn1);
        Node.printLinkedList(nn2);
        Node.printLinkedList(nn3);

    }
}
