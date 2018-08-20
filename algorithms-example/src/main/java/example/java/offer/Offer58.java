package example.java.offer;

/**
 * 题目:
 * 给定一个二叉树和其中的一个结点，请找出中序遍历顺序的下一个结点并且返回。注意，树中的结点不仅包含左右子结点，同时包含指向父结点的指针。
 */
public class Offer58 {
    private static class TreeNode<T> {
        public T val;
        public TreeNode<T> left;
        public TreeNode<T> right;
        public TreeNode<T> parent;

        public TreeNode(T val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return "TreeNode[ " + val + " ]";
        }
    }

    public static <T> TreeNode<T> getNext(TreeNode<T> node) {
        if (node == null) return null;
        if (node.right != null) { // 如果有右节点, 则找右节点的最左节点
            TreeNode<T> right = node.right;
            while (right.left != null) right = right.left;
            return right;
        } else {  // 没有, 则找某个是父结点的左节点的节点
            while (node.parent != null && node == node.parent.right)
                node = node.parent;
            return node.parent;
        }
    }

    public static void main(String[] args) {
        TreeNode<Integer> n1 = new TreeNode<>(1);
        TreeNode<Integer> n2 = new TreeNode<>(2);
        TreeNode<Integer> n3 = new TreeNode<>(3);
        TreeNode<Integer> n4 = new TreeNode<>(4);
        TreeNode<Integer> n5 = new TreeNode<>(5);
        TreeNode<Integer> n6 = new TreeNode<>(6);
        TreeNode<Integer> n7 = new TreeNode<>(7);
        n1.left = n2; n1.right = n3;
        n2.left = n4; n2.right = n5; n2.parent = n1;
        n3.left = n6; n3.right = n7; n3.parent = n1;
        n4.parent = n2; n5.parent = n2; n6.parent = n3; n7.parent = n3;

        System.out.println(getNext(n4));  // 2
        System.out.println(getNext(n2));  // 5
        System.out.println(getNext(n5));  // 1
        System.out.println(getNext(n7));  // null
    }
}
