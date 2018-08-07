package example.java.offer;

public class ListNode<T> {
    T val;
    ListNode<T> next;

    public ListNode(T val) {
        this.val = val;
        this.next = null;
    }

    @Override
    public String toString() {
        return "ListNode[ " + val + " ]";
    }

    public static <T> String mkString(ListNode<T> head, String delimeter) {
        StringBuilder sb = new StringBuilder();
        while (head != null) {
            sb.append(head.val + delimeter);
            head = head.next;
        }
        return sb.toString().substring(0, sb.length() - delimeter.length());
    }
}
