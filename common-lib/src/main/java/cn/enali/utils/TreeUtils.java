package cn.enali.utils;

import java.util.*;
import java.util.LinkedList;

public class TreeUtils {
    private static String DEFAULT_DELIMITER = ",";

    // 根据前序/中序遍历, 创建一个树
    public static TreeNode<Character> createCharTree(String preOrder, String inOrder) {
        if (preOrder == null || inOrder == null) return null;
        if (preOrder.length() != inOrder.length()) return null;
        if (preOrder.isEmpty()) return null;

        char rootValue = preOrder.charAt(0);
        int rootIndex = inOrder.indexOf(rootValue);
        TreeNode<Character> root = new TreeNode<>(rootValue);
        root.left = createCharTree(preOrder.substring(1, 1 + rootIndex), inOrder.substring(0, rootIndex));
        root.right = createCharTree(preOrder.substring(1 + rootIndex), inOrder.substring(1 + rootIndex));
        return root;
    }

    // TODO: 根据层序遍历的字串进行恢复, 无节点则以'#'代替
    public static TreeNode<String> createCharTree(String[] levelOrder) {
        int len = levelOrder.length;
        List<TreeNode<String>> nodes = new ArrayList<>(len);
        for (int i=0; i<len; i++) {
            nodes.add(new TreeNode<>(levelOrder[i]));
        }
        for (int i=1; i<len; i++) {
            TreeNode<String> parent = nodes.get((i-1) / 2);  // 父节点
            if ((i-1) % 2 == 0) parent.left = nodes.get(i);  // 左链
            else parent.right = nodes.get(i);  // 右链
        }
        return nodes.get(0);
    }

    // 根据层次遍历的元素列表创建二叉树, 无节点则以null代替
    public static <T> TreeNode<T> createCharTree(List<T> levelOrder) {
        List<TreeNode<T>> nodes = new ArrayList<>();
        for (T t : levelOrder) {  // 将元素值转为节点的list, 若元素为null, 则节点为空
            if (t != null) nodes.add(new TreeNode<>(t));
            else nodes.add(null);
        }
        int len = nodes.size();
        for (int i=1; i<len; i++) {
            TreeNode<T> parent = nodes.get((i-1) / 2);  // 父节点
            if (parent == null) {
                if (nodes.get(i) != null)
                    throw new IllegalArgumentException("child must be null when parent is null");
                continue;
            }
            if ((i-1) % 2 == 0) parent.left = nodes.get(i);  // 左链
            else parent.right = nodes.get(i);  // 右链
        }
        return nodes.get(0);
    }

    // 前序遍历
    public static <T> void preOrder(TreeNode<T> root) {
        if (root == null) return;
        System.out.print(root.val);
        preOrder(root.left);
        preOrder(root.right);
    }

    // 中序遍历
    public static <T> void inOrder(TreeNode<T> root) {
        if (root == null) return;
        inOrder(root.left);
        System.out.print(root.val);
        inOrder(root.right);
    }

    // 以迭代的方式进行二叉树的中序遍历.
    public static <T> String inOrderIter(TreeNode<T> root, String delimiter) {
        if (root == null) return "";

        TreeNode<T> cur = root;
        StringBuilder sb = new StringBuilder();

        Stack<TreeNode<T>> s = new Stack<>();
        while (!s.isEmpty() || cur != null) {
            while (cur != null) {
                s.push(cur);
                cur = cur.left;
            }
            cur = s.pop();
            sb.append(cur.val).append(delimiter);
            cur = cur.right;
        }
        return sb.toString().substring(0, sb.length()-delimiter.length());
    }

    public static <T> String inOrderIter(TreeNode<T> root) {
        return inOrderIter(root, DEFAULT_DELIMITER);
    }

    // 后序遍历
    public static <T> void postOrder(TreeNode<T> root) {
        if (root == null) return;
        postOrder(root.left);
        postOrder(root.right);
        System.out.print(root.val);
    }

    // 直接根据前序和中序遍历字串, 返回后序字串
    public static String postOrder(String preOrder, String inOrder) {
        if (preOrder.isEmpty()) return "";
        char rootValue = preOrder.charAt(0);
        int rootIndex = inOrder.indexOf(rootValue);

        return postOrder(preOrder.substring(1, 1 + rootIndex), inOrder.substring(0, rootIndex))
                + postOrder(preOrder.substring(1 + rootIndex), inOrder.substring(1 + rootIndex))
                + rootValue;
    }

    // 层序遍历字串
    // TODO: 添加完全层次遍历的flag, 即无元素时替换为票房字符
    public static <T> String levelOrder(TreeNode<T> root, String delimiter) {
        if (root == null) return "";

        Queue<TreeNode<T>> q = new LinkedList<>();
        q.add(root);
        StringBuilder sb = new StringBuilder();

        while (!q.isEmpty()) {
            TreeNode<T> tmp = q.remove();
            sb.append(tmp.val).append(delimiter);
            if (tmp.left != null) q.add(tmp.left);
            if (tmp.right != null) q.add(tmp.right);
        }
        return sb.toString().substring(0, sb.length()-delimiter.length());
    }

    public static <T> String levelOrder(TreeNode<T> root) {
        return levelOrder(root, DEFAULT_DELIMITER);
    }
}
