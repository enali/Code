package example.java.offer;

import cn.enali.utils.TreeNode;
import cn.enali.utils.TreeUtils;

import java.util.Arrays;
import java.util.Stack;

/**
 * 题目:
 * 给定一棵二叉搜索树，请找出其中的第k小的结点。例如， （5，3，7，2，4，6，8）    中，按结点数值大小顺序第三小结点的值为4。
 *
 * 思考:
 * 中序遍历二叉搜索树, 结果是有序的序列, 因此, 也就是说, 中序遍历的第k个节点
 */
public class Offer63 {
    public static <T> TreeNode<T> kthNode(TreeNode<T> root, int k) {
        if (root == null) return null;
        Stack<TreeNode<T>> s = new Stack<>();
        TreeNode<T> tmp = root;
        int cnt = 1;

        while (tmp != null || !s.isEmpty()) {
            while (tmp != null) {
                s.push(tmp);
                tmp = tmp.left;
            }
            tmp = s.pop();
            if (cnt++ == k) return tmp;
            tmp = tmp.right;
        }
        return null;
    }

    public static void main(String[] args) {
        TreeNode<Integer> root = TreeUtils.createCharTree(
                Arrays.asList(5, 3, 7, 2, 4, 6, 8)
        );
        System.out.println(kthNode(root, 1));
        System.out.println(kthNode(root, 10));
    }
}
