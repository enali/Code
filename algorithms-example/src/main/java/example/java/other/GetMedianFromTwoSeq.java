package example.java.other;

public class GetMedianFromTwoSeq {
    public static int getMedian(int[] ary1, int[] ary2) {
        int lo1 = 0, hi1 = ary1.length-1;
        int lo2 = 0, hi2 = ary2.length-1;

        do {
            int len1 = (hi1 - lo1) + 1;
            int len2 = (hi2 - lo2) + 1;
            int mid1 = getMedian(ary1, lo1, hi1);
            int mid2 = getMedian(ary2, lo2, hi2);
            if (mid1 == mid2) return mid1;
            else if (mid1 < mid2) {
                int tmp = Math.min(len1, len2) / 2;
                lo1 += tmp;
                hi2 -= tmp;
            } else {
                int tmp = Math.min(len1, len2) / 2;
                lo2 += tmp;
                hi1 -= tmp;
            }
        } while (lo1 <= hi1 && lo2 <= hi2);
        return -1;
    }

    private static int getMedian(int[] ary, int lo, int hi) {
        int len = (hi - lo) + 1;
        if (len % 2 == 0) return (ary[len/2-1] + ary[len/2]) / 2;
        else return ary[len/2];
    }

    public static void main(String[] args) {
        int[] ary1 = {1,3,5,7};
        int[] ary2 = {2,4,6,8};
        System.out.println(getMedian(ary1, ary2));
    }
}
