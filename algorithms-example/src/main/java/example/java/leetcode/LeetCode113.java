package example.java.leetcode;

import cn.enali.utils.TreeNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * 题目:
 * 给定一个二叉树与整数, 找出所有从根节点到叶节点的路径, 这些路径上的节点值累加和为sum
 *
 * 思考:
 * 参考 {@link example.java.offer.Offer25}
 */
public class LeetCode113 {
    public List<List<Integer>> pathSum(TreeNode<Integer> root, int sum) {
        List<List<Integer>> rst = new ArrayList<>();
        pathSum(root, sum, new Stack<>(), rst);
        return rst;
    }

    // 整体上是个前序遍历, 根左右
    private void pathSum(TreeNode<Integer> root, int remain, Stack<Integer> path, List<List<Integer>> rst) {
        if (root == null) return;
        path.push(root.val);
        if (root.left == null && root.right == null && root.val == remain) {
            // 所有的路径, 都使用path来存储(只有一个path), 因此, 找到一条路径, 就新建一个List
            rst.add(new ArrayList<>(path));  // 虽然到叶子结点了, 但是仍然不能返回
        }
        pathSum(root.left, remain-root.val, path, rst);
        pathSum(root.right, remain-root.val, path, rst);
        path.pop(); // 移除当前节点 (只有一个path)
    }
}
