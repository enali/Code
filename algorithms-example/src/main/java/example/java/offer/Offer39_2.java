package example.java.offer;

import cn.enali.utils.TreeNode;

/**
 * 题目:
 * 输入一棵二叉树，判断该二叉树是否是平衡二叉树。
 */
public class Offer39_2 {
    public static <T> boolean isBalanced(TreeNode<T> root) {
        if (root == null) return true;
        int[] dep = new int[1];
        return isBalanced(root, dep);
    }

    // 通过1元素的数组, 把深度信息带出来
    private static <T> boolean isBalanced(TreeNode<T> root, int[] dep) {
        if (root == null) {
            dep[0] = 0;
            return true;
        }
        int[] left = new int[1];
        int[] right = new int[1];
        // 左子树平衡, 右子树平衡, 又有左右子树的深度信息, 决断当前节点的平衡
        if (isBalanced(root.left, left) && isBalanced(root.right, right)) {
            int diff = left[0] - right[0];
            if (Math.abs(diff) <= 1) {
                // 如果平衡, 则更新当前节点的深度信息
                dep[0] = 1 + (left[0] > right[0] ? left[0] : right[0]);
                return true;
            }
        }
        return false;
    }
}
