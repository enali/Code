package example.java.offer;

/**
 * 题目:
 * 请设计一个函数，用来判断在一个矩阵中是否存在一条包含某字符串所有字符的路径。路径可以从矩阵中的任意一个格子开始，
 * 每一步可以在矩阵中向左，向右，向上，向下移动一个格子。如果一条路径经过了矩阵中的某一个格子，则之后不能再次进入这个格子。
 *               a b c e
 *               s f c s
 *               a d e e
 * 这样的3 X 4 矩阵中包含一条字符串"bcced"的路径，但是矩阵中不包含"abcb"路径，因为字符串的第一个字符b占据了矩阵中的第一行
 * 第二个格子之后，路径不能再次进入该格子。
 *
 * 思考:
 * 回溯
 *
 * 参考:
 * {@link Offer67}
 */
public class Offer66 {
    public static boolean hasPath(char[] matrix, int rows, int cols, char[] str) {
        if (matrix == null || matrix.length != rows * cols || str == null || str.length == 0) return false;

        char[][] tmp = new char[rows][cols];
        int idx = 0;
        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                tmp[i][j] = matrix[idx++];
            }
        }
        boolean[][] visit = new boolean[rows][cols];
        boolean[] finish = new boolean[1];
        int[][] step = {{-1, 0},{1, 0}, {0, -1}, {0, 1}};
        for (int i=0; i<rows; i++) {
            for (int j=0; j<cols; j++) {
                if (tmp[i][j] == str[0])
                    move(tmp, visit, finish, step, i, j, str, 0);
            }
        }
        return finish[0];
    }

    private static void move(
            char[][] matrix, boolean[][] visit,
            boolean[] finish, int[][] step,
            int x, int y,
            char[] str, int idx) {
        if (finish[0]) return;
        visit[x][y] = true;
        if (idx == str.length-1) {
            finish[0] = true;
            return;
        }
        for (int[] act : step) {
            x += act[0]; y += act[1];
            if (x >= 0 && x < matrix.length &&
                    y >= 0 && y < matrix[0].length &&
                    !visit[x][y] &&
                    matrix[x][y] == str[idx+1])
                move(matrix, visit, finish, step, x, y, str, idx+1);
            x -= act[0]; y -= act[1];
        }
        visit[x][y] = false;
    }

    public static void main(String[] args) {
        char[] matrix = {'a', 'b', 'c', 'e', 's', 'f', 'c', 's', 'a', 'd', 'e', 'e'};
        int rows = 3, cols = 4;
        char[] str = {'b', 'c', 'c', 'e', 'd'};
        System.out.println(hasPath(matrix, rows, cols, str));

        char[] str2 = {'a', 'b', 'c', 'b'};
        System.out.println(hasPath(matrix, rows, cols, str2));
    }
}
