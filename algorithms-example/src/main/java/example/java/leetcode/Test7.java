package example.java.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Test7 {
    public class TreeNode {
        int val = 0;
        TreeNode left = null;
        TreeNode right = null;

        public TreeNode(int val) {
            this.val = val;
        }
    }
    public ArrayList<Integer> PrintFromTopToBottom(TreeNode root) {
        if (root == null) return null;

        ArrayList<Integer> al = new ArrayList<>();
        if (root.left == null && root.right == null) {
            al.add(root.val);
            return al;
        }
        Queue<TreeNode> q = new LinkedList<>();

        q.add(root);
        while (!q.isEmpty()) {
            TreeNode h = q.remove();
            al.add(h.val);
            if (h.left != null) q.add(h.left);
            if (h.right != null) q.add(h.right);
        }

        return al;
    }
}
