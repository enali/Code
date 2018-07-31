package example.java.offer;

import java.util.LinkedList;
import java.util.Queue;

public class TreeNode<T> {
    T val;
    TreeNode<T> left;
    TreeNode<T> right;

    public TreeNode(T val) {
        this.val = val;
        this.left = null;
        this.right = null;
    }

    public static <T> String printTreeNode(TreeNode<T> root) {
        Queue<TreeNode<T>> q = new LinkedList<>();
        q.add(root);
        StringBuilder sb = new StringBuilder();

        while (!q.isEmpty()) {
            TreeNode<T> tmp = q.remove();
            sb.append(tmp.val + ",");
            if (tmp.left != null) q.add(tmp.left);
            if (tmp.right != null) q.add(tmp.right);
        }
        return sb.toString().substring(0, sb.length()-1);
    }
}
