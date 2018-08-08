package example.java.offer;

import cn.enali.utils.TreeNode;

/**
 * 题目:
 * 输入两棵二叉树A，B，判断B是不是A的子结构。（ps：我们约定空树不是任意一个树的子结构）
 *
 * 思路:
 * 该方法是在A树中找到一个与B树的根节点相等的元素的结点，
 * 从这个相等的结点开始判断树B是不是树A的子结构，如果找到其的一个就返回，
 * 否则直到所有的结点都找完为止。
 */
public class Offer18 {
    public static <T> boolean hasSubTree(TreeNode<T> root1, TreeNode<T> root2) {
        if (root1 == null || root2 == null) return false;
        if (root1 == root2) return true;  // 同引用

        if (root1.val.equals(root2.val) && isSameTree(root1, root2)) return true;

        // 递归左右子树
        return hasSubTree(root1.left, root2) || hasSubTree(root1.right, root2);
    }

    private static <T> boolean isSameTree(TreeNode<T> tree1, TreeNode<T> tree2) {
        if (tree1 == tree2) return true;  // 同引用
        if (tree2 == null) return true;  // 右树为空, 而左树不空, 则右树是左树的子结构
        if (tree1 == null) return false;  // 反过来, 表示右树不是子结构
        if (!tree1.val.equals(tree2.val)) return false;  // 值不同, 自然不是子结构
        return isSameTree(tree1.left, tree2.left) && isSameTree(tree1.right, tree2.right);
    }
}
