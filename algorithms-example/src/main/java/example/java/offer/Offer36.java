package example.java.offer;

import java.util.Arrays;

/**
 * 题目:
 * 在数组中的两个数字如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。输入一个数组，求出这个数组中的逆序对的总数。
 *
 * 思路:
 * 归并排序的merge
 */
public class Offer36 {
    public static int inversePairs(int[] ary) {
        int count = 0;
        int len = ary.length;
        int[] aux = new int[len];
        // 由底向上的归并
        for (int sz=1; sz<len; sz += sz)
            for (int i=0; i+sz<len; i+=sz+sz)
                count += merge(aux, ary, i, i+sz-1, Math.min(len-1, i+sz+sz-1));
        return count;
    }

    // 返回逆序对的个数
    private static int merge(int[] aux, int[] ary, int lo, int mid, int hi) {
        for (int i=lo; i<=hi; i++) aux[i] = ary[i];
        int i=lo, j=mid+1, count=0;
        for (int k=lo; k<=hi; k++)
            if (i > mid) ary[k] = aux[j++];
            else if (j > hi) ary[k] = aux[i++];
            else if (aux[i] <= aux[j]) ary[k] = aux[i++];
            else {
                count += mid - i + 1;  // IMP: 如果aux[i]>aux[j], 则aux[j]跟aux[i]...aux[mid]全部构成逆序对
                ary[k] = aux[j++];
            }
        return count;
    }

    public static void main(String[] args) {
        int[] ary = {1,2,3,4,5,6,7,0};
        System.out.println(inversePairs(ary));
        System.out.println(Arrays.toString(ary));
    }
}
