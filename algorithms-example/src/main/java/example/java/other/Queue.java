package example.java.other;

import java.util.Arrays;

public class Queue {
    public static void queue(int n) {
        // 表示每行的列位置
        int[] q = new int[n];
        place(q, 0);
    }

    // 放置第idx个皇后
    private static void place(int[] q, int idx) {
        if (idx == q.length) {  // 已放置最后一个皇后, 打印
            System.out.println(Arrays.toString(q));
            return;
        }
        // 能放的位置, 只有0到q.length
        for (int i=0; i<q.length; i++) {
            q[idx] = i;
            if (check(q, idx)) place(q, idx+1);  // 当前可旋转, 则递归.
        }
    }

    // 检查第idx个皇后的位置是不可行
    private static boolean check(int[] q, int idx) {
        for (int i = 0; i < idx; i++)
            // 不在同列, 不在斜线上
            if (q[i] == q[idx] || Math.abs(q[i] - q[idx]) == idx - i) return false;
        return true;
    }

    public static void main(String[] args) {
        queue(8);
    }
}
