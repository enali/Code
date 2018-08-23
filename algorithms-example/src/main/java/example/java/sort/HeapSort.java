package example.java.sort;

import java.util.Arrays;

public class HeapSort {
    public static <T extends Comparable<T>> void sort(T[] ary) {
        int end = ary.length - 1;
        for (int i=(end-1)/2; i>= 0; i--)
            sink(ary, i, end);
        for (int i=end; i>0; i--) {
            exch(ary, 0, i);
            sink(ary, 0, i-1);
        }
    }

    private static <T extends Comparable<T>> void sink(T[] ary, int k, int end) {
        while (k * 2 + 1 <= end) {
            int j = k * 2 + 1;  // 子
            if (j < end && ary[j+1].compareTo(ary[j]) > 0) j += 1;  // 双子中的最大值
            if (ary[k].compareTo(ary[j]) < 0) exch(ary, k, j);  // 父 < 子
            k = j;
        }
    }

    private static <T extends Comparable<T>> void swim(T[] ary, int k) {
        while (k != 0) {
            if (ary[k].compareTo(ary[(k-1)/2]) > 0) exch(ary, k, (k-1) / 2);  // 父比子大
            k = (k-1) / 2;
        }
    }

    private static <T extends Comparable<T>> void exch(T[] ary, int i, int j) {
        if (i == j) return;
        T tmp = ary[i];
        ary[i] = ary[j];
        ary[j] = tmp;
    }

    private static <T extends Comparable<T>> boolean greater(T[] ary, int i, int j) {
        if (i == j) return false;
        return ary[i].compareTo(ary[j]) > 0;
    }

    public static void main(String[] args) {
        Integer[] ary = {8, 4, 0, 1, 5, 23};
        sort(ary);
        System.out.println(Arrays.toString(ary));
    }
}
