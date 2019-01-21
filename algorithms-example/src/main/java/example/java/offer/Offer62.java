package example.java.offer;

import cn.enali.utils.TreeNode;
import cn.enali.utils.TreeUtils;

import java.util.Stack;

public class Offer62 {
    private static int idx = 0;

    // 前序遍历, 将null用字符'$'替换
    public static <T> String serialize(TreeNode<T> root) {
        if (root == null) return "";

        StringBuilder sb = new StringBuilder();
        Stack<TreeNode<T>> s = new Stack<>();
        s.push(root);

        while (!s.isEmpty()) {
            TreeNode<T> tmp = s.pop();
            if (tmp == null) {
                sb.append('$').append(",");
                continue;
            }
            sb.append(tmp.val).append(",");
            s.push(tmp.right);
            s.push(tmp.left);
        }
        return sb.toString().substring(0, sb.length()-1);
    }

    public static TreeNode<String> deserialize(String str) {
        if (str == null || str.isEmpty()) return null;
        String[] items = str.split(",");
        idx = -1;
        return deserialize(items);
    }

    private static TreeNode<String> deserialize(String[] items) {
        idx++;  // 任何情况下, 保证idx是递增的
        if (items[idx].equals("$")) return null;
        TreeNode<String> root = new TreeNode<>(items[idx]);
        root.left = deserialize(items);
        root.right = deserialize(items);
        return root;
    }

    public static void main(String[] args) {
        TreeNode<Integer> n0 = new TreeNode<>(0);
        TreeNode<Integer> n1 = new TreeNode<>(1);
        TreeNode<Integer> n2 = new TreeNode<>(2);
        TreeNode<Integer> n3 = new TreeNode<>(3);
        TreeNode<Integer> n4 = new TreeNode<>(4);
        TreeNode<Integer> n5 = new TreeNode<>(5);
        TreeNode<Integer> n6 = new TreeNode<>(6);
        TreeNode<Integer> n7 = new TreeNode<>(7);
        TreeNode<Integer> n8 = new TreeNode<>(8);

        n0.left = n1;
        n0.right = n2;
        n1.right = n3;
        n2.left = n4;
        n3.left = n5;
        n3.right = n6;
        n4.left = n7;
        n4.right = n8;

        String ser = serialize(n0);
        System.out.println(ser);

        TreeNode<String> root = deserialize(ser);
        TreeUtils.preOrder(root);
    }
}
