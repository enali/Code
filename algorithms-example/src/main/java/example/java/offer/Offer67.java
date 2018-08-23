package example.java.offer;

public class Offer67 {
    private static int cnt = 0;
    private static int[][] step = {
            {-1, 0},{1, 0}, {0, -1}, {0, 1}
    };

    public static int movingCount(int threshold, int rows, int cols) {
        boolean[][] visited = new boolean[rows][cols];
        move(visited, threshold, rows, cols, 0, 0);
        return cnt;
    }

    private static void move(boolean[][] visited, int threshold, int rows, int cols, int x, int y) {
        visited[x][y] = true;
        for (int i=0; i<step.length; i++) {
            x += step[i][0];
            y += step[i][1];
            if (x >= 0 && x <= rows && y >= 0 && y <= cols && canMove(x, y, threshold) && !visited[x][y]) {
                cnt++;
                move(visited, threshold, rows, cols, x, y);
            }
        }


    }

    private static boolean canMove(int x, int y, int threshold) {
        int sum = 0;
        while (x != 0) {
            sum += x % 10;
            x /= 10;
        }
        while (y != 0) {
            sum += y % 10;
            y /= 10;
        }
        return sum <= threshold;
    }
}
