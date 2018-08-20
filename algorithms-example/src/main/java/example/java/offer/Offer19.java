package example.java.offer;

import cn.enali.utils.TreeNode;
import cn.enali.utils.TreeUtils;

import java.util.Arrays;

/**
 * 题目:
 * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 *
 * 思路:
 * 参考 {@link Offer59}
 */
public class Offer19 {
    public static <T> void mirror(TreeNode<T> root) {
        if (root == null) return;

        TreeNode<T> tmp = root.left;
        root.left = root.right;
        root.right = tmp;

        mirror(root.left);
        mirror(root.right);
    }

    public static void main(String[] args) {
//        TreeNode<Character> tree = TreeUtils.createCharTree("ABCDEFG");
        TreeNode<String> tree = TreeUtils.createCharTree(Arrays.asList("A", "B", "C", "D", "E", "F", "G"));
        mirror(tree);
        System.out.println(TreeUtils.levelOrder(tree));
    }
}
