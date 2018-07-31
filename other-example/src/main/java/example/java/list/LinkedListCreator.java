package example.java.list;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LinkedListCreator {
    // 从数组中创建单向链表
    public Node<Integer> createLinkedList(List<Integer> data) {
        if (data.isEmpty()) {
            return null;
        }
        Node<Integer> firstNode = new Node<>(data.get(0));
        firstNode.setNext(createLinkedList(data.subList(1, data.size())));
        return firstNode;
    }

    public static void main(String[] args) {
        LinkedListCreator lc = new LinkedListCreator();

        Node.printLinkedList(lc.createLinkedList(new ArrayList<>()));
        Node.printLinkedList(lc.createLinkedList(Arrays.asList(1)));
        Node.printLinkedList(lc.createLinkedList(Arrays.asList(1, 2, 3)));
    }
}
