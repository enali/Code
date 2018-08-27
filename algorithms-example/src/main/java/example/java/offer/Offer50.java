package example.java.offer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * 题目:
 * 求树中两个结点的最低公共祖先, 此树不是二叉树, 并且没有指向父结点的指针
 *
 * 思考:
 * 1, 求出从根结点到需要的结点的路径, 然后比较两个路径, 其路径上最近的那个公共节点即最低公共祖先
 *
 * 可参考: {@link example.java.leetcode.LeetCode236}, 不过这个是二叉树
 */
public class Offer50 {
    // 多叉树
    private static class TreeNode<T> {
        public T val;
        public List<TreeNode<T>> children;
        public TreeNode(T val) {
            this.val = val;
            this.children = new LinkedList<>();
        }
        @Override
        public String toString() {
            return val + "";
        }
    }

    public static <T> TreeNode<T> getLastCommonNode(TreeNode<T> root, TreeNode<T> p, TreeNode<T> q) {
        if (root == null || root == p || root == q) return root;
        if (p == null || q == null) return null;

        List<TreeNode<T>> pathToP = new ArrayList<>();
        List<TreeNode<T>> pathToQ = new ArrayList<>();
        Stack<TreeNode<T>> stack = new Stack<>();
        boolean[] finish = new boolean[1];
        getPath(root, p, stack, pathToP, finish);
        stack.clear(); finish[0] = false;
        getPath(root, q, stack, pathToQ, finish);
        int idx = 0;
        while (idx < pathToP.size() && idx < pathToQ.size() && pathToP.get(idx) == pathToQ.get(idx)) idx++;
        return pathToP.get(idx-1);
    }

    /**
     * 从根结点出发, 找到到某个结点的路径
     *
     * @param root 树的根结点
     * @param tofind 要寻找的结点
     * @param stack 用于结点遍历的栈
     * @param path 保存根到结点的路径
     * @param finish 是否找到结点的标志
     * @param <T> 结点的值的类型
     */
    private static <T> void getPath(
            TreeNode<T> root, TreeNode<T> tofind,
            Stack<TreeNode<T>> stack, List<TreeNode<T>> path,
            boolean[] finish) {
        if (root == null) return;
        if (finish[0]) return;
        stack.push(root);
        if (root == tofind) {
            finish[0] = true;
            path.addAll(stack);  // stack是用于遍历, path才是用来保存结果的
            return;
        }
        for (TreeNode<T> child : root.children) {  // 对于叶了结点, 其没有子结点, 此处不遍历, 即不存在null
            getPath(child, tofind, stack, path, finish);
        }
        stack.pop();
    }

    public static void main(String[] args) {
        TreeNode<Character> a = new TreeNode<>('A');
        TreeNode<Character> b = new TreeNode<>('B');
        TreeNode<Character> c = new TreeNode<>('C');
        TreeNode<Character> d = new TreeNode<>('D');
        TreeNode<Character> e = new TreeNode<>('E');
        TreeNode<Character> f = new TreeNode<>('F');
        TreeNode<Character> g = new TreeNode<>('G');
        TreeNode<Character> h = new TreeNode<>('H');
        TreeNode<Character> i = new TreeNode<>('I');
        TreeNode<Character> j = new TreeNode<>('J');
        a.children.add(b); a.children.add(c);
        b.children.add(d); b.children.add(e);
        d.children.add(f); d.children.add(g);
        e.children.add(h); e.children.add(i); e.children.add(j);

        System.out.println(getLastCommonNode(a, f, h));  // b
        System.out.println(getLastCommonNode(a, h, i));  // e
    }
}
