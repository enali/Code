package example.java.algs4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

// 图的邻接表示
public class Graph {
    private static final String NEWLINE = System.getProperty("line.separator");

    private final int V;
    private int E;
    private List<List<Integer>> adj;  // 邻接链表, 外层为ArrayList, 便于索引访问, 内层LinkedList, 便于迭代

    public Graph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.V = V;
        this.E = 0;
        adj = new ArrayList<>(V);  // 指定初始容量, 但是, get(0)还是错误
        for (int i=0; i<V; i++)
            adj.add(new LinkedList<>());
    }

    // 返回当前节点数
    public int V() { return V; }
    // 返回当前边数
    public int E() { return E; }

    // 添加一条边, v->w
    public void addEdge(int v, int w) {
        validateVertex(v);
        validateVertex(w);
        E++;
        adj.get(v).add(w);
        adj.get(w).add(v);
    }

    // 返回点v的邻接节点
    public Iterable<Integer> adj(int v) {
        validateVertex(v);
        return adj.get(v);
    }

    public int degree(int v) {
        validateVertex(v);
        return adj.get(v).size();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(V + " vertics, " + E + " edges " + NEWLINE);
        for (int v=0; v<V; v++) {
            sb.append(v + ": ");
            for (int w: adj.get(v)) {
                sb.append(w + " ");
            }
            sb.append(NEWLINE);
        }
        return sb.toString();
    }

    // 验证点v是否有效
    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
}
