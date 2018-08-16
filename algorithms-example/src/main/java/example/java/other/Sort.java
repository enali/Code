package example.java.other;

import java.util.Arrays;

public class Sort {
    public static void mergeSort(int[] ary) {
        if (ary == null || ary.length <= 1) return;
        int[] aux = Arrays.copyOf(ary, ary.length);
        mergeSort(aux, ary, 0, ary.length-1);
    }

    private static void mergeSort(int[] src, int[] dst, int lo, int hi) {
        if (lo >= hi) return;
        int mid = lo + (hi - lo) / 2;
        mergeSort(dst, src, lo, mid);
        mergeSort(dst, src, mid+1, hi);
        merge(src, dst, lo, mid, hi);
    }

    private static void merge(int[] src, int[] dst, int lo, int mid, int hi) {
        int i=lo, j = mid+1;
        for (int k = lo; k<=hi; k++) {
            if (i > mid) dst[k] = src[j++];
            else if (j > hi) dst[k] = src[i++];
            else if (src[i] < src[j]) dst[k] = src[i++];
            else dst[k] = src[j++];
        }
    }

    private static void swap(int[] ary, int i, int j) {
        if (i != j) {
            int tmp = ary[i];
            ary[i] = ary[j];
            ary[j] = tmp;
        }
    }

    public static void main(String[] args) {
        int[] ary = {9, 7, 8, 3, 1, 5};
        mergeSort(ary);
        System.out.println(Arrays.toString(ary));
    }
}
