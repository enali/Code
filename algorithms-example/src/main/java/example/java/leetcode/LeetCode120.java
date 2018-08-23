package example.java.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 题目:
 * 给定一个二维数组, 其保存了一个数字三角形, 求从数字三角形顶端到底端各数字和最小的路径之和, 每次可以向下走相邻的两个位置.
 * [2]
 * [3,4]
 * [6,5,7]
 * [4,1,8,3]
 *
 * 思考:
 * 因为只能从上往下, 走相邻的点, 也就是向下或向斜下, 以dp[i][j]表示走到(i,j)的最小路径和, 则其只能从(i-1, j)或(i-1, j-1)
 * 则, dp[i][j] = min(dp[i-1][j], dp[i-1][j-1]) + dp[i][j], 要注意右侧边界只有斜上点
 *
 * 类似的, 可以参考 {@link LeetCode64}
 */
public class LeetCode120 {
    public static int minimumTotal(List<List<Integer>> triangle) {
        int row = triangle.size();
        int col = triangle.get(row-1).size();

        int[][] dp = new int[row][col];
        int sum = 0;
        for (int i=0; i<row; i++) {
            sum += triangle.get(i).get(0);
            dp[i][0] = sum;
        }

        for (int i=1; i<row; i++) {
            for (int j=1; j<=i; j++) {
                int min = dp[i-1][j-1];
                if (i != j && min > dp[i-1][j])
                    min = dp[i-1][j];
                dp[i][j] = min + triangle.get(i).get(j);
            }
        }
        int min = Integer.MAX_VALUE;
        for (int j=0; j<col; j++)
            if (min > dp[row-1][j])
                min = dp[row-1][j];
        return min;
    }

    public static void main(String[] args) {
        List<List<Integer>> tri = new ArrayList<>();
        tri.add(Arrays.asList(2));
        tri.add(Arrays.asList(3, 4));
        tri.add(Arrays.asList(6, 5, 7));
        tri.add(Arrays.asList(4, 1, 8, 3));
        System.out.println(minimumTotal(tri));
    }
}
