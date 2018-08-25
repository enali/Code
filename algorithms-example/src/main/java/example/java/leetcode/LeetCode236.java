package example.java.leetcode;

import cn.enali.utils.TreeNode;

import java.util.Stack;

/**
 * 题目:
 * 已知二叉树, 求二叉树中给定的两个节点的最近公共祖先(两节点的最近公共祖先u, 满足在树上最低(离根最远), 且v,w两个节点都是u的子孙)
 */
public class LeetCode236 {
    // 基于这样一个事实:
    // 如果在左侧树找到2个节点, 则公共节点在左侧, 如果在右侧树找到2个节点, 则公共节点在右侧, 如果分别在两侧找到2个节点, 则公共节点为当前节点
    public static TreeNode<Integer> lowestCommonAncestor(TreeNode<Integer> root, TreeNode<Integer> p, TreeNode<Integer> q) {
        if (root == null || root == p || root == q) return root;
        TreeNode<Integer> left = lowestCommonAncestor(root.left, p, q);
        TreeNode<Integer> right = lowestCommonAncestor(root.right, p, q);
        // 如果左为null, 则在右, 如果左不为null, 右为null, 则在左, 如果左右都不为null, 则为root
        return left == null ? right : right == null ? left : root;
    }

    public static void main(String[] args) {
        TreeNode<Integer> p3 = new TreeNode<>(3);
        TreeNode<Integer> p5 = new TreeNode<>(5);
        TreeNode<Integer> p1 = new TreeNode<>(1);
        TreeNode<Integer> p6 = new TreeNode<>(6);
        TreeNode<Integer> p2 = new TreeNode<>(2);
        TreeNode<Integer> p0 = new TreeNode<>(0);
        TreeNode<Integer> p8 = new TreeNode<>(8);
        TreeNode<Integer> p7 = new TreeNode<>(7);
        TreeNode<Integer> p4 = new TreeNode<>(4);
        p3.left = p5; p3.right = p1;
        p5.left = p6; p5.right = p2;
        p1.left = p0; p1.right = p8;
        p2.left = p7; p2.right = p4;

        System.out.println(lowestCommonAncestor(p3, p6, p4));  // 5
    }
}
