package example.java.tree;

public class InOrder {
    // 返回中序遍历下, 二叉树的某个节点的下一个节点
    public TreeNode next(TreeNode node) {
        if (node == null) return null;
        // 右子树不空, 则返回右子树的最左节点
        if (node.getRight() != null) {
            return first(node.getRight());
        } else {  // 右子树空, 则往上找第一个父节点, 满足: 此节点是父节点的左节点
            while (node.getParent() != null
                    && node == node.getParent().getRight()) {
                node = node.getParent();
            }
            return node.getParent();
        }
    }

    // 返回中序遍历下, 一棵二叉树的首个节点
    public TreeNode first(TreeNode node) {
        if (node == null) return null;
        TreeNode curNode = node;
        while (curNode.getLeft() != null) {
            curNode = curNode.getLeft();
        }
        return curNode;
    }

    // 中序遍历一棵二叉树
    public void traverse(TreeNode root) {
        for (TreeNode node = first(root);
             node != null;
             node = next(node)) {
            System.out.print(node.getValue());
        }
        System.out.println();
    }

    public static void main(String[] args) {
    }
}
