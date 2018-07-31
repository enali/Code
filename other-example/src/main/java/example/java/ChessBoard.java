package example.java;

/**
 * 棋盘覆盖问题
 * 问题： 在与特殊块相邻的块的编号不一致
 */
public class ChessBoard {
    private int t;
    private int[][] board;
    private int dr;
    private int dc;

    public ChessBoard(int[][] board, int dr, int dc) {
        this.board = board;
        this.dr = dr;
        this.dc = dc;
        this.t = 0;
    }

    public void chessboard() {
        chessboard(0, 0, dr, dc, board.length);
    }

    public void chessboard(int tr, int tc, int dr, int dc, int size) {
        if (size == 1) return;
        t++;
        int s = size/2;
        if (dr<tr+s && dc<tc+s)
            chessboard(tr, tc, dr, dc, s);
        else {
            board[tr+s-1][tc+s-1] = t;
            chessboard(tr, tc, tr+s-1, tc+s-1, s);
        }
        if (dr<tr+s && dc>=tc+s)
            chessboard(tr, tc+s, dr, dc, s);
        else {
            board[tr+s-1][tc+s] = t;
            chessboard(tr, tc+s, tr+s-1, tc+s, s);
        }
        if (dr>=tr+s && dc<tc+s)
            chessboard(tr+s, tc, dr, dc, s);
        else {
            board[tr+s][tc+s-1] = t;
            chessboard(tr+s, tc, tr+s, tc+s-1, s);
        }
        if (dr>=tr+s && dc>=tc+s)
            chessboard(tr+s, tc+s, dr, dc, s);
        else {
            board[tr+s][tc+s] = t;
            chessboard(tr+s, tc+s, tr+s, tc+s, s);
        }
    }

    public static void main(String[] args) {
        int N = 3;
        int size = (int)Math.pow(2, N);
        int[][] b = new int[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                b[i][j] = 0;
            }
        }
        ChessBoard cb = new ChessBoard(b, 3, 3);
        cb.chessboard();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.printf("%d\t", cb.board[i][j]);
            }
            System.out.println();
        }
    }
}
