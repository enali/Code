package example.java.offer;

import cn.enali.utils.TreeNode;
import cn.enali.utils.TreeUtils;

import java.util.Arrays;
import java.util.Stack;

/**
 * 题目:
 * 输入一棵二叉搜索树，将该二叉搜索树转换成一个排序的双向链表。要求不能创建任何新的结点，只能调整树中结点指针的指向。
 *
 * 思考:
 * 以中序遍历二叉树, 当前节点cur跟下一个节点next的转化关系为: cur.right = next; next.right = cur.
 */
public class Offer27 {
    public static <T> TreeNode<T> convert(TreeNode<T> root) {
        if (root == null) return null;
        Stack<TreeNode<T>> s = new Stack<>();
        TreeNode<T> tmp = root;
        TreeNode<T> pre = null;
        while (!s.isEmpty() || tmp != null) {
            while (tmp != null) {
                s.push(tmp);
                tmp = tmp.left;
            }
            tmp = s.pop();
            if (pre != null) {
                pre.right = tmp;
                tmp.left = pre;
            }
            pre = tmp;
            tmp = tmp.right;
        }
        while (pre.left != null) pre = pre.left;
        return pre;
    }

    public static void main(String[] args) {
        TreeNode<Integer> root = TreeUtils.createCharTree(Arrays.asList(10, 6, 14, 4, 8, 12, 16));
        TreeNode<Integer> newRoot = convert(root);
        while (newRoot != null) {
            System.out.print(newRoot.val + ",");
            newRoot = newRoot.right;
        }
    }
}
