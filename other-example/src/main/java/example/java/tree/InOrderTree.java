package example.java.tree;

import java.util.Iterator;
import java.util.NoSuchElementException;

// 中序遍历树， 以迭代器的形式
public class InOrderTree implements Iterable<TreeNode> {
    private TreeNode root;

    public InOrderTree(TreeNode root) {
        this.root = root;
    }

    @Override
    public Iterator<TreeNode> iterator() {
        return new TreeIterator();
    }

    private TreeNode first(TreeNode node) {
        if (node == null) return null;
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    // 比如, TreeNode root;
    // getNext(root)不会改变外在的root对其对象的引用关系, 但是却能改变此对象的内部状态(若对象是可变的)
    private TreeNode getNext(TreeNode node) {
        if (node == null) return null;
        if (node.getRight() != null) {
            return first(node.getRight());
        } else {
            while (node.getParent() != null && node == node.getParent().getRight()) {
                node = node.getParent();
            }
            return node.getParent();
        }
    }

    class TreeIterator implements Iterator<TreeNode> {
        TreeNode cur;  // 以cur表示next要展示的值

        public TreeIterator() {
            cur = first(root);
        }

        @Override
        public boolean hasNext() {  // 重点是不能修改cur指针
            return cur != null;
        }

        @Override
        public TreeNode next() {
            if (cur == null)
                throw new NoSuchElementException();
            TreeNode rst = cur;
            cur = getNext(cur);
            return rst;
        }
    }

    public static void main(String[] args) {
    }
}
