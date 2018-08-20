package example.java.leetcode;

public class LeetCode120 {
    public static int minimumTotal(int[][] ary) {
        int row = ary.length;
        int col = ary[row-1].length;

        int[][] dp = new int[row][col];
        int sum = 0;
        for (int i=0; i<row; i++) {
            sum += ary[i][0];
            dp[i][0] = sum;
        }

        for (int i=1; i<row; i++) {
            for (int j=1; j<=i; j++) {
                int min = dp[i-1][j-1];
                if (i != j && min > dp[i-1][j]) min = dp[i-1][j];
                dp[i][j] = min + ary[i][j];
            }
        }
        int min = Integer.MAX_VALUE;
        for (int j=0; j<col; j++)
            if (min > dp[row-1][j])
                min = dp[row-1][j];
        return min;
    }

    public static void main(String[] args) {
        int[][] ary = {
                {2},
                {3, 4},
                {6, 5, 7},
                {4, 1, 8, 3}
        };
        System.out.println(minimumTotal(ary));
    }
}
