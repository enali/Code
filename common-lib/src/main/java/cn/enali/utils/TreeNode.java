package cn.enali.utils;

public class TreeNode<T> {
    public final T val;
    public TreeNode<T> left;
    public TreeNode<T> right;

    public TreeNode(T val) {
        this.val = val;
        left = null;
        right = null;
    }
}
