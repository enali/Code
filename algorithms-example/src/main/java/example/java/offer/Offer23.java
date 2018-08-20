package example.java.offer;

import cn.enali.utils.TreeNode;
import cn.enali.utils.TreeUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 题目:
 * 从上往下打印出二叉树的每个节点，同层节点从左至右打印。
 *
 * 思路:
 * 用队列, {@link Offer60} {@link Offer61}
 */
public class Offer23 {
    public static <T> ArrayList<T> printFromTopToBottom(TreeNode<T> root) {
        Queue<TreeNode<T>> queue = new LinkedList<>();
        ArrayList<T> rst = new ArrayList<>();
        if (root == null) return rst;
        int qsize = 1;  // 因为是一层层打印, qsize为当前打印层的大小
        queue.add(root);
        while (!queue.isEmpty()) {
            // 一次遍历一层
            for (int i=0; i<qsize; i++) {
                TreeNode<T> tmp = queue.remove();
                // 因为add时没判断null, 所以此时要加上null判断
                if (tmp == null) continue;
                rst.add(tmp.val);
                queue.add(tmp.left);
                queue.add(tmp.right);
            }
            qsize = queue.size();
        }
        return rst;
    }

    public static void main(String[] args) {
        TreeNode<Integer> root = TreeUtils.createCharTree(Arrays.asList(0, 1, 2, 3, 4, 5, 6));
        ArrayList<Integer> rst = printFromTopToBottom(root);
        System.out.println(rst);
    }
}
