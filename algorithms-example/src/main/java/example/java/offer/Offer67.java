package example.java.offer;

/**
 * 题目:
 * 地上有mxn的方格, 一个机器人从坐标(0, 0)的格子开始移动, 它每一次可以向左, 右, 上, 下移动一格, 但不能进入行坐标和列
 * 坐标的数位之和大于k的格子. 例如, 当k为18时, 机器人可以进入方格(35, 37), 因为3+5+3+7=18. 但是, 它不能进入方格(35, 38),
 * 因为3+5+3+8=19. 请问机器人能够达到多少个格子.
 *
 *
 */
public class Offer67 {
    public static int movingCount(int threshold, int rows, int cols) {
        boolean[][] visited = new boolean[rows][cols];
        int[][] step = {{-1, 0},{1, 0}, {0, -1}, {0, 1}};
        int[] cnt = new int[1];
        move(visited, threshold, rows, cols, 0, 0, step, cnt);
        return cnt[0];
    }

    /**
     * 移动
     *
     * @param visited 标记当前已访问的位置
     * @param threshold 格子位数和的阈值
     * @param rows 格子的总行数
     * @param cols 格子的总列数
     * @param x 当前的x坐标
     * @param y 当前的y坐标
     * @param step 分别表示上下左右的坐标增量
     * @param cnt 计数可走的格子数
     */
    private static void move(
            boolean[][] visited, int threshold,
            int rows, int cols,
            int x, int y,
            int[][] step, int[] cnt) {
        visited[x][y] = true;  // 标记已访问
        cnt[0]++;
        for (int i=0; i<step.length; i++) {  // 遍历上下左右
            x += step[i][0];  // 新的坐标
            y += step[i][1];
            // 如果新的坐标合理, 且未访问, 且阈值未超, 则移动下一格
            if (x >= 0 && x < rows && y >= 0 && y < cols && !visited[x][y] && getSum(x, y) <= threshold) {
                move(visited, threshold, rows, cols, x, y, step, cnt);
            }
            x -= step[i][0];  // 回溯
            y -= step[i][1];
        }
    }

    // 获得坐标点(x, y)的数位和
    private static int getSum(int x, int y) {
        int sum = 0;
        while (x != 0) {
            sum += x % 10;
            x /= 10;
        }
        while (y != 0) {
            sum += y % 10;
            y /= 10;
        }
        return sum;
    }

    public static void main(String[] args) {
        int rows = 3;
        int cols = 4;
        System.out.println(movingCount(2, rows, cols));  // 6
        System.out.println(movingCount(3, rows, cols));  // 9
    }
}
