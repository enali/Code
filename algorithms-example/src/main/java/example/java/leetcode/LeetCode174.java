package example.java.leetcode;

/**
 * 题目：
 * 已知一个二维数组， 左上角代表骑士的位置， 右下角代表公主的位置， 二维数组中存储整数， 正数可以给骑士增加生命值， 负数会减少骑士
 * 的生命值， 问骑士初始时至少是多少生命值， 才可保证骑士在行走的过程中至少保持生命值为1. （骑士只能向下或向右行走）
 *
 * [-2, -3, 3]
 * [-5, -10, 1]
 * [10, 30, -5]
 *
 * 思考：
 * 从左上向右下递推， 没办法将"每个格子最多能获得多少血量"，转换成“初始时至少是多少血量"
 *
 * 从右下向左上递推， dp[i][j]代表若要达到右下角， 至少有多少血量， 能在行走的过程中至少保持生命值为1
 *
 * 以3X3为例， 到达(2,2)， 要保持至少生命为1， 则Math.max(1, 1-(-5)) = 6, 此处主要考虑如果为正数时， 只要为1就OK
 * 到达(1,2)， 要保持至少生命为1， 考虑到达(2,2)至少6， 则Math.max(1, 6-1)
 */
public class LeetCode174 {
    public static int calculateMinimumHP(int[][] dungeon) {
        if (dungeon == null || dungeon.length == 0 || dungeon[0].length == 0)
            return 0;
        int row = dungeon.length;
        int col = dungeon[0].length;
        int[][] dp = new int[row][col];
        dp[row-1][col-1] = Math.max(1, 1-dungeon[row-1][col-1]);
        for (int i=row-2; i>=0; i--)
            dp[i][col-1] = Math.max(1, dp[i+1][col-1] - dungeon[i][col-1]);
        for (int j=col-2; j>=0; j--)
            dp[row-1][j] = Math.max(1, dp[row-1][j+1] - dungeon[row-1][j]);

        for (int i=row-2; i>=0; i--) {
            for (int j=col-2; j>=0; j--) {
                dp[i][j] = Math.max(1,
                        Math.min(dp[i+1][j] - dungeon[i][j], dp[i][j+1] - dungeon[i][j])
                        );
            }
        }
        return dp[0][0];
    }

    public static void main(String[] args) {
        int[][] dungeon = {
                {-2, -3, 3},
                {-5, -10, 1},
                {10, 30, -5}
        };
        System.out.println(calculateMinimumHP(dungeon));
    }
}
