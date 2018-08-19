package example.java.other;

/**
 * 01背包问题
 */
public class Package01 {
    private static class Item {
        public int weight;
        public int value;
        public Item(int weight, int value) {
            this.weight = weight;
            this.value = value;
        }
    }

    public static int getAnswer(Item[] items, int capacity) {
        int len = items.length;
        // dp[i][j]表示前i个物品, 容量j的背包能放下的最大价值
        int[][] dp = new int[len+1][capacity+1];
        for (int i=0; i<=len; i++) dp[i][0] = 0;
        for (int j=0; j<=capacity; j++) dp[0][j] = 0;

        for (int i=1; i<=len; i++) {
            for (int j=1; j<=capacity; j++) {
                // dp[i][j] = max(dp[i-1][j-wi] + pi, dp[i-1][j])
                int tmp = j - items[i-1].weight;  // 第i件物品, 对应items的i-1索引
                dp[i][j] = Math.max(
                        tmp >= 0 ? dp[i-1][tmp] + items[i-1].value : 0,
                        dp[i-1][j]
                );
            }
        }
        return dp[len][capacity];
    }

    public static void main(String[] args) {
        Item[] items = {
                new Item(2, 6),
                new Item(2, 3),
                new Item(6, 5),
                new Item(5, 4),
                new Item(4, 6)
        };
        int capacity = 10;
        System.out.println(getAnswer(items, capacity));
    }
}
