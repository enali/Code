package example.java.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 题目:
 * 已知有n个课程, 标记从0到n-1, 课程之间是有依赖关系的, 例如希望完成A课程, 可能需要先完成B课程. 已知n个课程的依赖
 * 关系, 求是否可以将n个课程全部完成
 *
 * 思考:
 * 本质上, 在由课程构造的有向图中, 判断有没有依赖
 *
 * 1, 深度优先搜索: 如果正在搜索某一顶点(还未退出该顶点的递归深度搜索), 又回到了该顶点, 即可证明图有环
 * 2, 拓扑排序(宽度优先搜索): 将入度为0的点添加到队列中, 当完成一个顶点的搜索(从队列中取出), 它指向的所有顶点入席都减1, 若此时
 * 某顶点入度为0, 则添加到队列, 若完成宽度搜索后, 所有的点入度都为0, 则图无环, 否则有环. (入度: 指向节点的边数)
 */
public class LeetCode207 {
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>(numCourses);
        int[] degree = new int[numCourses];  // 保存入度的数组
        for (int i=0; i<numCourses; i++)
            graph.add(new LinkedList<>());
        for (int[] req : prerequisites) {
            graph.get(req[1]).add(req[0]);  // 注意: 是req[0] <- req[1]
            degree[req[0]]++;  // 增加入度
        }
        ArrayList<Integer> bfs = new ArrayList<>();  // 充当队列
        for (int i=0; i<numCourses; i++) if (degree[i]==0) bfs.add(i);  // 添加0入度的节点
        for (int i=0; i<bfs.size(); i++) {  // 取入度0的节点
            for (int v : graph.get(bfs.get(i)))  // 注意: 是get, 并不从队列删除
                if (--degree[v] == 0) bfs.add(v);
        }
        return bfs.size() == numCourses;  // 如果无环, 则全部节点都会加进来
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        List<List<Integer>> graph = new ArrayList<>(numCourses);
        for (int i=0; i<numCourses; i++)
            graph.add(new LinkedList<>());
        for (int[] req : prerequisites) {
            graph.get(req[0]).add(req[1]);
        }
        int[] visit = new int[numCourses];
        for (int i=0; i<numCourses; i++)
            if (visit[i] == 0 && dfs(graph, i, visit)) return false;
        return true;
    }

    // 返回是否有环
    private boolean dfs(List<List<Integer>> graph, int node, int[] visit) {
        visit[node] = 2;  // 已访问, 且在当前搜索路径中
        for (int v : graph.get(node))
            // 若未访问且对其的访问有环, 或者v在当前搜索路径中
            if (visit[v] == 0 && dfs(graph, v, visit) || visit[v] == 2) return true;
        visit[node] = 1;  // 回退, 置1, 表示已访问, 但并非当前搜索路径
        return false;
    }

    public static void main(String[] args) {
        LeetCode207 lc = new LeetCode207();

        int numCourses = 2;
        int[][] req = {{1,0}};
        System.out.println(lc.canFinish2(numCourses, req));

        int numCourses2 = 2;
        int[][] req2 = {{1,0}, {0, 1}};
        System.out.println(lc.canFinish2(numCourses2, req2));
    }
}
