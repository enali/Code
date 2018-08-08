package example.java.offer;

import cn.enali.utils.TreeNode;
import cn.enali.utils.TreeUtils;

import java.util.Arrays;

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
