package example.java.leetcode;

import java.util.Arrays;

public class Test30 {
    public static int InversePairs(int [] array) {
        int count = 0;
        int len = array.length;
        int[] aux = new int[len];
        for (int sz=1; sz<len; sz += sz)
            for (int i=0; i+sz<len; i+=sz+sz)
                count += merge(aux, array, i, i+sz-1, Math.min(len-1, i+sz+sz-1));
        return count;
    }

    private static int merge(int[] aux, int[] ary, int lo, int mid, int hi) {
        for (int i=lo; i<=hi; i++) aux[i] = ary[i];
        int i=lo, j=mid+1, count=0;
        for (int k=lo; k<=hi; k++)
            if (i > mid) ary[k] = aux[j++];
            else if (j > hi) ary[k] = aux[i++];
            else if (aux[i] <= aux[j]) ary[k] = aux[i++];
            else {
                count += mid - i + 1;
                ary[k] = aux[j++];
            }
        return count;
    }

    public static void main(String[] args) {
        int[] ary = {1,2,3,4,5,6,7,0};
        System.out.println(InversePairs(ary));
        System.out.println(Arrays.toString(ary));
    }
}
