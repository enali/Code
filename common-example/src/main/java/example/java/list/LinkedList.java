package example.java.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

// 为单向链表实现Iterable接口
public class LinkedList<T> implements Iterable<T> {
    private Node<T> head;
    private Node<T> tail;

    public LinkedList() {
        head = null;
        tail = null;
    }

    public static <E> LinkedList<E> newEmptyList() {
        return new LinkedList<>();
    }

    public void add(T value) {
        Node<T> node = new Node<>(value);
        if (tail == null) {
            head = node;
        } else {
            tail.setNext(node);
        }
        tail = node;
    }

    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    // 实现Iterator接口
    private class ListIterator implements Iterator<T> {
        private Node<T> cur;  // 表示当前要输出的节点

        public ListIterator() {
            cur = head;
        }

        @Override
        public boolean hasNext() {
            return cur != null;
        }

        @Override
        public T next() {
            if (cur == null)  // 无元素时, 一定要抛出异常
                throw new NoSuchElementException("cur Node is null");
            T val = cur.getValue();
            cur = cur.getNext();
            return val;
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> l = LinkedList.newEmptyList();
        l.add(1);
        l.add(2);

        for (Integer i : l) {
            System.out.println(i);
        }

        LinkedList<String> l2 = LinkedList.newEmptyList();
        l2.add("a");
        l2.add("b");

        for (String i : l2) {
            System.out.println(i);
        }

        System.out.println(LinkedList.<String>newEmptyList());
    }
}
