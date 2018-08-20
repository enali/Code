package example.java.offer;

import cn.enali.utils.TreeNode;

/**
 * 题目:
 * 请实现一个函数，用来判断一颗二叉树是不是对称的。注意，如果一个二叉树同此二叉树的镜像是同样的，定义其为对称的。
 *
 * 思路:
 * 请参考 {@link Offer19}
 */
public class Offer59 {
    public static <T> boolean isSymmetrical(TreeNode<T> root) {
        if (root == null) return true;
        return isSymmetrical(root.left, root.right);
    }

    private static <T> boolean isSymmetrical(TreeNode<T> root1, TreeNode<T> root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null || !root1.val.equals(root2.val)) return false;
        return isSymmetrical(root1.left, root2.right) &&
                isSymmetrical(root1.right, root2.left);
    }

    public static void main(String[] args) {

    }
}
