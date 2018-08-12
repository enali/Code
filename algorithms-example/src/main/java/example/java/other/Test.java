package example.java.other;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        int[][] a = {
                {9, 0},
                {3, 1},
                {5, 2},
                {1, 3}
        };
        Arrays.sort(a, (x1, x2) -> x1[0] - x2[0]);
        for (int[] tmp : a) {
            System.out.println(Arrays.toString(tmp));
        }
    }
}
