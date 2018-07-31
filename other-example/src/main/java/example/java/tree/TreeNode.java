package example.java.tree;

// 带指向父节点的二叉树
// 父节点主要用于寻找中序遍历下， 任一节点的下一个节点
public class TreeNode {
    private final char value;
    private TreeNode left;
    private TreeNode right;
    private TreeNode parent;

    public TreeNode(char value) {
        this.value = value;
        this.left = null;
        this.right = null;
        this.parent = null;
    }

    public TreeNode getParent() {
        return parent;
    }

    private void setParent(TreeNode parent) {
        this.parent = parent;
    }

    public char getValue() {
        return value;
    }

    public TreeNode getLeft() {
        return left;
    }

    // 设置左节点时， 自动设置父节点
    public void setLeft(TreeNode left) {
        this.left = left;
        if (left != null) {
            this.left.setParent(this);
        }
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
        if (right != null) {
            this.right.setParent(this);
        }
    }
}
