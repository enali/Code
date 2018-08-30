package example.java.other;

import cn.enali.utils.GraphNode;

import java.util.Queue;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Arrays;


public class Graph {
    public static void main(String[] args) {
        int nodeNum = 5;
        List<GraphNode<Integer>> graph = new ArrayList<>(nodeNum);
        for (int i=0; i<nodeNum; i++) {
            graph.add(new GraphNode<>(i));
        }
        graph.get(0).neighbors.addAll(Arrays.asList(graph.get(4), graph.get(2)));
        graph.get(1).neighbors.addAll(Arrays.asList(graph.get(0), graph.get(2)));
        graph.get(2).neighbors.addAll(Arrays.asList(graph.get(3)));
        graph.get(3).neighbors.addAll(Arrays.asList(graph.get(4)));
        graph.get(4).neighbors.addAll(Arrays.asList(graph.get(3)));

        int[] visit = new int[nodeNum];
        for (GraphNode<Integer> v : graph) {
            if (visit[v.label] == 0) {
                System.out.print("From label<" + v.label + ">: ");
                dfs(v, visit);
                System.out.println();
            }
        }

        Arrays.fill(visit, 0);
        for (GraphNode<Integer> v : graph) {
            if (visit[v.label] == 0) {
                System.out.print("From label<" + v.label + ">: ");
                bfs(v, visit);
                System.out.println();
            }
        }
    }

    // 深度优先搜索, visit控制是否访问
    public static void dfs(GraphNode<Integer> node, int[] visit) {
        System.out.print(node.label + " ");
        visit[node.label] = 1;  // 标记已访问
        for (GraphNode<Integer> v : node.neighbors) {
            if (visit[v.label] == 1) continue;  // 跳过已经遍历的节点
            dfs(v, visit);
        }
    }

    public static void bfs(GraphNode<Integer> node, int[] visit) {
        Queue<GraphNode<Integer>> q = new LinkedList<>();
        q.add(node);
        visit[node.label] = 1;  // 加入队列即认为已访问
        while (!q.isEmpty()) {
            GraphNode<Integer> tmp = q.remove();
            System.out.print(tmp.label + " ");
            // 可防止, 在访问4/2时, 都有邻节点3, 因为3未被访问, 而被加入列队2次
            for (GraphNode<Integer> v : tmp.neighbors)
                if (visit[v.label] == 0) {
                    q.add(v);
                    visit[v.label] = 1;
                }
        }
    }
}
