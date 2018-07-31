package example.java.list;

public class LinkedListDeletor {
    // 以循环的方式, 从链表中删除特定值的节点
    // 头结点没有prev, 1, 特殊处理; 2, 增加虚拟头结点
    public static <T> Node<T> deleteIfEquals(Node<T> head, T value) {
        // handle the head if head's value equals value
        while (head != null && head.getValue().equals(value)) {
            head = head.getNext();
        }
        Node<T> prev = head;
        while (prev != null && prev.getNext() != null) {
            if (prev.getNext().getValue().equals(value)) {
                prev.setNext(prev.getNext().getNext());
            } else {
                prev.setNext(prev.getNext());
            }
        }
        return prev;
    }

    public static void main(String[] args) {
        Node<Integer> n1 = new Node<>(1);
        Node<Integer> n2 = new Node<>(2);
        Node<Integer> n3 = new Node<>(1);
        n1.setNext(n2);
        n2.setNext(n3);

        System.out.println(LinkedListDeletor.<Integer>deleteIfEquals(n1, 1).getValue());
    }
}
