package example.java.list;

// 链表的节点
public class Node<T> {
    private final T value;  // 不可变
    private Node<T> next;

    public Node(T value) {
        this.value = value;
        this.next = null;
    }

    public T getValue() {
        return value;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    // 静态函数, 使用方法泛型
    public static <E> void printLinkedList(Node<E> head) {
        while (head != null) {
            System.out.print(head.getValue());
            System.out.print(" ");
            head = head.getNext();
        }
        System.out.println();
    }
}
