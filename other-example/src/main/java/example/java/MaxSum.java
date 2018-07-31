package example.java;

/**
 * 求整数序列的最大子段和
 */
public class MaxSum {
    public Rst maxsum(int[] r) {
        return maxsum(r, 0, r.length-1);
    }

    private Rst maxsum(int[] r, int s, int e) {
        Rst ms = new Rst(0,0,0);
        if (s==e) {
            if (r[s]>0)
                ms.sum = r[s];
            return ms;
        } else {
            int m = (s+e)/2;
            Rst ls = maxsum(r, s, m);
            Rst rs = maxsum(r, m+1, e);
            int s1=0, left=0;
            for (int i=m; i>=s; i--) {
                left += r[i];
                if (left>s1) {
                    s1 = left;
                    ms.lidx = i;
                }
            }
            int s2=0, right=0;
            for (int i=m+1; i<=e; i++) {
                right += r[i];
                if (right>s2) {
                    s2 = right;
                    ms.ridx = i;
                }
            }
            ms.sum = s1 + s2;
            if (ms.sum < ls.sum) ms = ls;
            if (ms.sum < rs.sum) ms = rs;
        }
        return ms;
    }

    public static void main(String[] args) {
        int[] r = {-20, 11, -4, 13, -5, -2};
        MaxSum ms = new MaxSum();
        Rst rst = ms.maxsum(r);
        System.out.printf("%d: %d->%d\n", rst.sum, rst.lidx, rst.ridx);
    }
}

class Rst {
    int sum = 0;
    int ridx = 0;
    int lidx = 0;

    public Rst(int s, int l, int r) {
        sum = s;
        ridx = r;
        lidx = l;
    }
}
