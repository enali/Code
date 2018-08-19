package example.java.offer;

import cn.enali.utils.TreeNode;
import cn.enali.utils.TreeUtils;

/**
 * 题目:
 * 输入一棵二叉树的根结点，求该树的深度。从根结点到叶子点依次经过的结点（含根、叶结点）形成树的一条路径，最长路径的长度为树的深度。
 */
public class Offer39 {
    public static <T> int treeDepth(TreeNode<T> root) {
        if (root == null) return 0;
        int left = treeDepth(root.left);
        int right = treeDepth(root.right);
        return left > right ? (left + 1) : (right + 1);
    }

    public static void main(String[] args) {
        TreeNode<String> root = TreeUtils.createCharTree(
                new String[] {"1", "2", "3", "4", "5", "6", "7", "8", "9"}
        );
        System.out.println(treeDepth(root));
    }
}
