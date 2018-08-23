import java.util.Scanner;

public class Test6 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
        String ans = find(input);
        if (ans != )

    }

    private static String find(String input) {
        int n = input.length();
        int[][] dp = new int[n+1][n+1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= n; j++) {
                if (input.charAt(i-1) == input.charAt(j-1) && i != j) {
                    dp[i][j] = 1 + dp[i-1][j-1];
                } else {
                    dp[i][j] = Math.max(dp[i][j-1], dp[i-1][j]);
                }
            }
        }
        String res = "";
        int i = n, j = n;
        while (i > 0 && j > 0) {
            if (dp[i][j] == dp[i-1][j-1] + 1) {
                res = res + input.charAt(i-1);
                i--;j--;
            } else if (dp[i][j] == dp[i-1][j]){
                i--;
            } else {
                j--;
            }
        }
        String ans = new StringBuilder(res).reverse().toString();
        return ans;
    }
}