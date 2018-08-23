package example.java.leetcode;

/**
 * 题目：
 * 已知一个二维数组， 其中存储了非负整数， 找到从左上角到右下角的一条路径， 使得路径上的和最小 (移动过程只能向下或向右）
 *
 * 思考：
 * 以dp[i][j]表示， 进行到（i,j)时的路径和， 考虑到只能向下或向右， 则
 * dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
 *
 * 参考 {@link LeetCode120}
 */
public class LeetCode64 {
    public static int minPathSum(int[][] grid) {
        if (grid == null || grid.length == 0 || grid[0].length == 0) return -1;

        int row = grid.length;
        int col = grid[0].length;
        int[][] dp = new int[row][col];
        dp[0][0] = grid[0][0];

        for (int i=1; i<row; i++)
            dp[i][0] = dp[i-1][0] + grid[i][0];
        for (int j=1; j<col; j++)
            dp[0][j] = dp[0][j-1] + grid[0][j];

        for (int i=1; i<row; i++) {
            for (int j=1; j<col; j++) {
                dp[i][j] = Math.min(dp[i-1][j], dp[i][j-1]) + grid[i][j];
            }
        }
        return dp[row-1][col-1];
    }

    public static void main(String[] args) {
        int[][] grid = {
                {1,3,1},
                {1,5,1},
                {4,2,1}
        };
        System.out.println(minPathSum(grid));  // 7
    }
}
