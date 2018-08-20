package example.java.offer;

import cn.enali.utils.TreeNode;
import cn.enali.utils.TreeUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目:
 * 从上到下按层打印二叉树，同一层的结点按从左到右的顺序打印，每一层打印一行。 (强调一层一行)
 *
 * 思路:
 * {@link Offer23} {@link Offer61}
 */
public class Offer60 {
    public static <T> ArrayList<ArrayList<T>> print(TreeNode<T> root) {
        ArrayList<ArrayList<T>> rst = new ArrayList<>();
        if (root == null) return rst;

        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            int size = queue.size();  // 核心, 遍历这一层的所有节点
            ArrayList<T> row = new ArrayList<>();
            for (int i=0; i<size; i++) {
                TreeNode<T> tmp = queue.remove();
//                if (tmp == null) continue;
                row.add(tmp.val);
                if (tmp.left != null) queue.add(tmp.left);
                if (tmp.right != null) queue.add(tmp.right);
            }
            rst.add(row);
        }
        return rst;
    }

    public static void main(String[] args) {
        TreeNode<Integer> root = TreeUtils.createCharTree(Arrays.asList(0, 1, 2, 3, 4, 5, 6));
        ArrayList<ArrayList<Integer>> rst = print(root);
        for (ArrayList<Integer> row : rst)
            System.out.println(row);
    }
}
