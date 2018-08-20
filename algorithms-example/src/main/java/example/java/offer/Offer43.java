package example.java.offer;

/**
 * 题目:
 * 把 n 个骰子扔在地上，所有骰子朝上一面的点数之和为 s。输入 n，打印出 s 的所有可能的值出现的概率。
 *
 * 思考:
 * 以dp[i][j]表示i个骰子点数为j的概率
 * 则, 考虑第i个骰子的点数只可能为1/2/3/4/5/6, 则
 * dp[i][j] = dp[i-1][j-1] + dp[i-1][j-2] + dp[i-1][j-3] + ....
 */
public class Offer43 {
    public static String[] printProbility(int n) {
        if (n <= 0) return null;
        int total = (int) Math.pow(6, n);
        String[] result = new String[6 * n - n + 1];

        int[][] dp = new int[n + 1][6 * n + 1];
        for (int i = 1; i <= 6; i++) dp[1][i] = 1;
        for (int i = 2; i <= n; i++)
            for (int j = i; j <= 6 * n; j++) {
                int sum = 0;
                for (int m = 1; m <= j && m <= 6; m++)
                    sum += dp[i - 1][j - m];
                dp[i][j] = sum;
            }
        for (int k = n; k <= 6 * n; k++) {
            result[k - n] = dp[n][k] + "/" + total;
        }
        return result;
    }

    public static void main(String[] args) {
        String[] result = printProbility(3);
        for (String s : result)
            System.out.print(s + ",");
    }
}
