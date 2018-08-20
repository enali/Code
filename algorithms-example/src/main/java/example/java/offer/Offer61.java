package example.java.offer;

import cn.enali.utils.TreeNode;
import cn.enali.utils.TreeUtils;

import java.util.*;

/**
 * 题目:
 * 请实现一个函数按照之字形顺序打印二叉树，即第一行按照从左到右的顺序打印，第二层按照从右到左的顺序打印，
 * 即第一行按照从左到右的顺序打印，第二层按照从右到左顺序打印，第三行再按照从左到右的顺序打印，其他以此类推。
 *
 * 思路:
 * 需要一个变量来控制方向, 参考 {@link Offer60} {@link Offer23}
 */
public class Offer61 {
    public static <T> ArrayList<ArrayList<T>> print(TreeNode<T> root) {
        ArrayList<ArrayList<T>> rst = new ArrayList<>();
        if (root == null) return rst;

        Queue<TreeNode<T>> queue = new LinkedList<>();
        queue.add(root);
        boolean reverse = false;  // 控制方向
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
            if (reverse) Collections.reverse(row);
            rst.add(row);
            reverse = !reverse;
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
