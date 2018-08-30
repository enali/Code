package example.java.sort;

import java.util.Arrays;

public class QuickSort {
    public static void sort(int[] ary) {
        if (ary == null || ary.length <= 1) return;
        sort(ary, 0, ary.length-1);
    }

    private static void sort(int[] ary, int lo, int hi) {
        if (lo >= hi) return;
        int j = partition(ary, lo, hi);
        sort(ary, lo, j-1);
        sort(ary, j+1, hi);
    }

    private static int partition(int[] ary, int lo, int hi) {
        int i = lo, j = hi+1;
        int k = ary[lo];
        while (true) {
            while (ary[++i] < k) if (i == hi) break;  // i停止的值, 一定>=k
            while (ary[--j] > k) if (j == lo) break;  // j停止的值, 一定<=k
            if (i >= j) break;  // 若i==j, 则某个值==k
            exch(ary, i, j);
        }
        exch(ary, lo, j);
        return j;
    }

    private static void exch(int[] ary, int i, int j) {
        if (i == j) return;
        int tmp = ary[i];
        ary[i] = ary[j];
        ary[j] = tmp;
    }

    public static void main(String[] args) {
        int[] ary = {3, 1, 9, 0, 6};
        sort(ary);
        System.out.println(Arrays.toString(ary));

        int[] ary2 = {1, 0};
        sort(ary2);
        System.out.println(Arrays.toString(ary2));

        int[] ary3 = {3, 1, 9, 0, 1, 6};
        sort(ary3);
        System.out.println(Arrays.toString(ary3));

        int[] ary4 = {3, 0, 3, 6};
        sort(ary4);
        System.out.println(Arrays.toString(ary4));
    }
}
