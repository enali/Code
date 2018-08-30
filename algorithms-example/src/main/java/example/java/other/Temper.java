package example.java.other;

import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;

/**
 * 题目:
 * 小易有一些立方体，每个立方体的边长为1，他用这些立方体搭了一些塔。
 * 现在小易定义：这些塔的不稳定值为它们之中最高的塔与最低的塔的高度差。
 * 小易想让这些塔尽量稳定，所以他进行了如下操作：每次从某座塔上取下一块立方体，并把它放到另一座塔上。
 * 注意，小易不会把立方体放到它原本的那座塔上，因为他认为这样毫无意义。
 * 现在小易想要知道，他进行了不超过k次操作之后，不稳定值最小是多少。
 */
public class Temper {
    // n, 塔数, k, 操作次数, heights, 塔高
    public static void move(int n, int k, int[] heights) {
        if (heights.length != n) return;
        if (n <= 1 || k <= 0) return;

        LinkedList<Integer> path = new LinkedList<>();
        // 对塔高排序, 并记录原顺序
        int[][] tmp = new int[heights.length][2];
        for (int i=0; i<heights.length; i++) {
            tmp[i][0] = heights[i];
            tmp[i][1] = i;
        }
        Arrays.sort(tmp, Comparator.comparingInt(x -> x[0]));

        int lo=0, hi = n-1;
        // 操作次数不能超
        while (lo < hi && k > 0) {
            // 寻找最低点
            while (lo < hi && tmp[lo][0] >= tmp[lo+1][0]) lo++;
            // 寻找最高点
            while (lo < hi && tmp[hi][0] <= tmp[hi-1][0]) hi--;
            if (lo >= hi) break;  // 重合或没有
            if (tmp[hi][0] - tmp[lo][0] <= 1) break;  // 高度差 <= 1, 不需要移动
            tmp[hi][0]--; tmp[lo][0]++;
            k--;
            path.add(tmp[hi][1] + 1); path.add(tmp[lo][1] + 1);
            lo=0; hi = n-1;
        }
        // 输出路径
        while (!path.isEmpty()) {
            System.out.print(path.removeFirst() + " -> " + path.removeFirst());
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int n = 2, k = 2;
        int[] heights = {0, 2};
        move(n, k, heights);
    }
}
