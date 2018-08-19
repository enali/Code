package example.java.offer;

/**
 * 题目:
 * 统计一个数字：在排序数组中出现的次数。
 *
 * 思路:
 * 利用二分查找.
 * 1, 先找到跟key相等的元素索引
 * 2, 在左半段找到, 前元素不是key, 当前元素是key的左界 (同样是二分查找)
 * 3, 在右半段找到, 当前元素是key, 后元素不是key的右界
 */
public class Offer38 {
    public static <T extends Comparable<T>> int getNumberOfK(T[] ary, T key) {
        int len = ary.length;
        int lo = 0, hi = len-1;
        int part = -1;
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            int tmp = key.compareTo(ary[mid]);
            if (tmp == 0) {
                part = mid;
                break;
            } else if (tmp > 0) lo = mid+1;
            else hi = mid - 1;
        }
        if (part == -1) return 0;  // 未找到

        int leftIdx = findLeftIdx(ary, lo, part, key);
        int rightIdx = findRightIdx(ary, part, hi, key);
        return rightIdx - leftIdx + 1;
    }

    private static <T extends Comparable<T>> int findLeftIdx(T[] ary, int lo, int hi, T key) {
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;
            int tmp = key.compareTo(ary[mid]);
            if (tmp < 0) hi = mid - 1;
            else if (tmp > 0) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    private static <T extends Comparable<T>> int findRightIdx(T[] ary, int lo, int hi, T key) {
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2 + 1;
            int tmp = key.compareTo(ary[mid]);
            if (tmp < 0) hi = mid - 1;
            else if (tmp > 0) lo = mid + 1;
            else lo = mid;
        }
        return lo;
    }

    public static void main(String[] args) {
        Integer[] ary = {1, 2, 3, 3, 3, 3, 4, 5};
        System.out.println(getNumberOfK(ary, 3));  // 4


        Integer[] ary2 = {3, 3, 3, 3, 4, 5};
        System.out.println(getNumberOfK(ary2, 3));  // 4
        Integer[] ary3 = {1, 2, 3, 3, 3, 3};
        System.out.println(getNumberOfK(ary3, 3));  // 4
        Integer[] ary4 = {3, 3, 3, 3};
        System.out.println(getNumberOfK(ary4, 3));  // 4
        Integer[] ary5 = {3};
        System.out.println(getNumberOfK(ary5, 3));  // 1
    }
}
