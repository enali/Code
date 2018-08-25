package example.java.leetcode;

import cn.enali.utils.TreeNode;

import java.util.Stack;

/**
 * 题目:
 * 给定一个二叉树, 将该二叉树就地(in-place)转换为单链表, 单链表中的节点顺序为二叉树的前序遍历顺序
 */
public class LeetCode114 {
    // 利用栈进行前序遍历, 则栈顶总是下一个节点
    // 借助了栈, 非就地
    public static <T> void flatten(TreeNode<T> root) {
        Stack<TreeNode<T>> s = new Stack<>();
        s.push(root);
        while (!s.isEmpty()) {
            TreeNode<T> tmp = s.pop();
            if (tmp.right != null) s.push(tmp.right);
            if (tmp.left != null) s.push(tmp.left);
            tmp.left = null;  // 置left为null
            if (s.isEmpty()) tmp.right = null;
            else tmp.right = s.peek();  // 栈顶置于right
        }
    }

    public static <T> void flatten2(TreeNode<T> root) {
        preOrder(root);
    }

    // 返回单链表的尾结点
    private static <T> TreeNode<T> preOrder(TreeNode<T> root) {
        if (root == null) return null;
        // 左支的头结点为root.left, 尾结点为leftTail
        TreeNode<T> leftTail = preOrder(root.left);
        TreeNode<T> rightTail = preOrder(root.right);
        if (leftTail == null && rightTail == null) return root;  // 左右空, 则root为尾
        else if (leftTail == null) return rightTail;  // 左空, 则右尾为尾
        else if (rightTail == null) {  // 右空, 移动左树到root的右支
            root.right = root.left;
            root.left = null;
            return leftTail;  // 返回左尾
        } else {  // 左右都不空, 这里有个trick, 即所有尾结点一定是叶节点, 因此, 其left/right为null
            leftTail.right = root.right;
            root.right = root.left;
            root.left = null;
        }
        return rightTail;
    }

    public static void main(String[] args) {
        TreeNode<Integer> p1 = new TreeNode<>(1);
        TreeNode<Integer> p2 = new TreeNode<>(2);
        TreeNode<Integer> p5 = new TreeNode<>(5);
        TreeNode<Integer> p3 = new TreeNode<>(3);
        TreeNode<Integer> p4 = new TreeNode<>(4);
        TreeNode<Integer> p6 = new TreeNode<>(6);
        TreeNode<Integer> p7 = new TreeNode<>(7);
        p1.left = p2; p1.right = p5;
        p2.left = p3; p2.right = p4;
        p4.left = p7;
        p5.right = p6;

//        TreeNode<Integer> tmp = flatten(p1);
//        while (tmp != null) {
//            System.out.println(tmp);
//            tmp = tmp.right;
//        }
        flatten2(p1);
        while (p1 != null) {
            System.out.println(p1);
            p1 = p1.right;
        }

    }
}
