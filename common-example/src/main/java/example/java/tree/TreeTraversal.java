package example.java.tree;

import java.util.Stack;

// 树的遍历
public class TreeTraversal {
    // 前序遍历
    public void preOrder(TreeNode root) {
        if (root == null) return;
        System.out.print(root.getValue());
        preOrder(root.getLeft());
        preOrder(root.getRight());
    }

    // 中序遍历
    public void inOrder(TreeNode root) {
        if (root == null) return;
        inOrder(root.getLeft());
        System.out.print(root.getValue());
        inOrder(root.getRight());
    }

    // 以迭代的方式进行二叉树的中序遍历.
    public void inOrderIter(TreeNode root) {
        TreeNode cur = root;
        Stack<TreeNode> s = new Stack<>();
        while (! s.isEmpty() || cur != null) {
            while (cur != null) {
                s.push(cur);
                cur = cur.getLeft();
            }
            cur = s.pop();
            System.out.print(cur.getValue());
            cur = cur.getRight();
        }
    }

    // 后序遍历
    public void postOrder(TreeNode root) {
        if (root == null) return;
        postOrder(root.getLeft());
        postOrder(root.getRight());
        System.out.print(root.getValue());
    }

    // 直接根据前序和中序遍历字串, 返回后序字串
    public String postOrder(String preOrder, String inOrder) {
        if (preOrder.isEmpty()) return "";
        char rootValue = preOrder.charAt(0);
        int rootIndex = inOrder.indexOf(rootValue);

        return postOrder(
                preOrder.substring(1, 1 + rootIndex), inOrder.substring(0, rootIndex))
                + postOrder(
                preOrder.substring(1 + rootIndex), inOrder.substring(1 + rootIndex))
                + rootValue;
    }

    public static void main(String[] args) {
        TreeCreator creator = new TreeCreator();
        TreeTraversal traversal = new TreeTraversal();

        System.out.println("Sample tree traversal");
        TreeNode sampleTree = creator.createSampleTree();
        traversal.preOrder(sampleTree);
        System.out.println();

        traversal.inOrder(sampleTree);
        System.out.println();
        traversal.inOrderIter(sampleTree);
        System.out.println();

        traversal.postOrder(sampleTree);
        System.out.println();

        System.out.println("=====");

        TreeNode tree = creator.createTree("ABDEGCF", "DBGEACF");
        traversal.postOrder(tree);
        System.out.println();
        traversal.postOrder(creator.createTree("", ""));  // 测试空串
        System.out.println();
        traversal.postOrder(creator.createTree("A", "A"));  // 测试单字符串
        System.out.println();
        traversal.postOrder(creator.createTree("AB", "BA"));  // 测试只有两个字符的字串
        System.out.println();

        System.out.println("Generating postOrder directly");
        System.out.println("====");

        System.out.println(traversal.postOrder("ABDEGCF", "DBGEACF"));
        System.out.println(traversal.postOrder("", ""));
        System.out.println(traversal.postOrder("A", "A"));
        System.out.println(traversal.postOrder("AB", "BA"));
    }
}
