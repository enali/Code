package example.java.offer;


import cn.enali.utils.TreeNode;
import cn.enali.utils.TreeUtils;

/**
 * 题目：输入某二叉树的前序遍历和中序遍历的结果，请重建出该二叉树。假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 * 例如：前序遍历序列｛ 1, 2, 4, 7, 3, 5, 6, 8｝和中序遍历序列｛4, 7, 2, 1, 5, 3, 8，6}，重建出下图所示的二叉树并输出它的头结点。
 */
public class Offer06 {
    public static <T> TreeNode<T> reConstructBinaryTree(T[] pre, T[] in) {
        if (pre == null || in == null || pre.length != in.length || pre.length == 0)
            return null;
        return construct(pre, 0, pre.length-1, in, 0, in.length-1);
    }

    // 包含 pe/ie
    private static <T> TreeNode<T> construct(T[] pre, int ps, int pe, T[] in, int is, int ie) {
        if (ps > pe) return null;

        T rootVal = pre[ps];
        int rootIdx = is;
        while (rootIdx <= ie && in[rootIdx] != rootVal) rootIdx++;

        if (rootIdx > ie) throw new RuntimeException("invalid input");

        TreeNode<T> root = new TreeNode<>(rootVal);
        root.left = construct(pre, ps+1, ps+rootIdx-is, in, is, rootIdx-1);
        root.right = construct(pre, ps+rootIdx-is+1, pe, in, rootIdx+1, ie);
        return root;
    }

    public static void main(String[] args) {
        Integer[] pre = {1, 2, 3, 4, 5, 6, 7};
        Integer[] in = {3, 2, 4, 1, 6, 5, 7};
        TreeNode<Integer> root = reConstructBinaryTree(pre, in);
        System.out.println(TreeUtils.levelOrder(root));
    }
}
