package example.java.offer;

import cn.enali.utils.TreeNode;
import cn.enali.utils.TreeUtils;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 题目：
 * 输入一棵二叉树和一个整数， 打印出二叉树中结点值的和为输入整数的所有路径。从树的根结点开始往下一直到叶结点所经过的结点形成一条路径。
 *
 * 思路:
 * 深度优先搜索, 参考 {@link example.java.leetcode.LeetCode113}
 */
public class Offer25 {
    public static ArrayList<ArrayList<Integer>> findPath(TreeNode<Integer> root, int target) {
        ArrayList<ArrayList<Integer>> rst = new ArrayList<>();
        if (root == null) return rst;
        findPath(root, target, new ArrayList<Integer>(), rst);
        return rst;
    }

    private static void findPath(
            TreeNode<Integer> root, int remain, ArrayList<Integer> path, ArrayList<ArrayList<Integer>> rst) {
        if (root == null) return;
        path.add(root.val);
        // 如果遇到叶子节点, 且刚好满足值的需求, 则添加并返回
        if (root.left == null && root.right == null && remain == root.val) {
            rst.add(new ArrayList<>(path));
        }
        remain -= root.val;
        findPath(root.left, remain, path, rst);  // 遍历左树
        findPath(root.right, remain, path, rst);  // 遍历右树
        path.remove(path.size() - 1);  // 回溯
    }

    public static void main(String[] args) {
        TreeNode<Integer> root = TreeUtils.createCharTree(Arrays.asList(1,2,3,4,5, 3));
        ArrayList<ArrayList<Integer>> rst = findPath(root, 7);
        for (ArrayList<Integer> path: rst) {
            System.out.println(path);
        }
    }
}
