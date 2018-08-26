package example.java.leetcode;

import cn.enali.utils.TreeNode;
import scala.Int;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 题目:
 * 给定一个二叉树, 假设从该二叉树的右侧观察它, 将观察到的节点按照从上到下的顺序输出
 *
 * 思考:
 * 利用队列进行层次遍历, 总是先加入右节点, 因此当前队列的0节点即为最右节点, 即右视图
 */
public class LeetCode199 {
    public static <T> List<T> rightSideView(TreeNode<T> root) {
        List<T> rst = new ArrayList<>();
        if (root == null) return rst;
        Queue<TreeNode<T>> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            // 遍历一层
            int size = q.size();
            for (int i = 0; i < size; i++) {
                TreeNode<T> tmp = q.remove();
                if (i == 0) rst.add(tmp.val);  // 首节点为所需节点
                if (tmp.right != null) q.add(tmp.right);  // 总是先加入右节点
                if (tmp.left != null) q.add(tmp.left);
            }
        }
        return rst;
    }

    public static void main(String[] args) {
        TreeNode<Integer> p1 = new TreeNode<>(1);
        TreeNode<Integer> p2 = new TreeNode<>(2);
        TreeNode<Integer> p3 = new TreeNode<>(3);
        TreeNode<Integer> p4 = new TreeNode<>(4);
        TreeNode<Integer> p5 = new TreeNode<>(5);
        p1.left = p2; p1.right = p3;
        p2.right = p5; p3.right = p4;

        System.out.println(rightSideView(p1));
    }
}
