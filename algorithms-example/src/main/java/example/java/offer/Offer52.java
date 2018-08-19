package example.java.offer;

/**
 * 题目:
 * 给定一个数组A[0,1,...,n-1],请构建一个数组B[0,1,...,n-1],其中B中的元素B[i]=A[0]*A[1]*...*A[i-1]*A[i+1]*...*A[n-1]。
 * 不能使用除法。
 *
 * 思路:
 * 最简单的思考是, 用一个数组存储{1, a[0], a[0]*a[1], a[0]*a[1]*a[2], ..., a[0]*a[1]*a[2]*..*a[n-2]}
 * 一个数组存储{a[1]*a[2]*...*a[n-1], a[2]*a[3]*...*a[n-1], ..., 1}
 */
public class Offer52 {
    public static int[] multiply(int[] a) {
        if (a == null) return null;

        int len = a.length;
        if (len == 0) return new int[0];

        int[] b = new int[len];
        b[0] = 1;
        if (len == 1) return b;

        // 正向遍历
        for (int i=1; i<len; i++)
            b[i] = b[i-1] * a[i-1];

        // 反向遍历
        int tmp = 1;
        for (int i=len-1; i>=0; i--) {
            b[i] *= tmp;
            tmp *= a[i];
        }

        return b;
    }

    public static void main(String[] args) {

    }
}
