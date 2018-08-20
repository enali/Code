package example.java.leetcode;

/**
 * 题目:
 * 求两个有序数组的中位数
 *
 * 思路:
 * L表示对数组切分后的左侧索引, R为右侧索引
 * 1, 对于单数组而言, 无论N为偶数还是奇数, L=(N-1)/2, R=(N-1)/2
 * 2, 对于单数组而言, 数字+数字的栅栏 = 2N+1, 其中位数的切分cut刚好在N处
 * {1,2,3}, 加上栅栏后{#, 1, #, 2, #, 3, #}, 一共7个, 中位数在索引3处
 * 切分刚好为N, 则L=(cut-1)/2, R=(cut-1)/2
 * 3, 两个有序数组的切分位置C1 + C2 = N1 + N2;
 */
public class LeetCode4 {
    public static double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length;
        int len2 = nums2.length;
        if (len1 < len2) findMedianSortedArrays(nums2, nums1);  // make sure nums2 is the shorter

        // 指向C2的起始和结束
        int lo = 0, hi = len2 * 2;  // 考虑栅栏, 最后的栅栏位置为2*N2
        while (lo <= hi) {
            int mid2 = lo + (hi - lo) / 2;
            int mid1 = (len1 + len2) - mid2;

            // 栅栏为0, 则l1表示左侧的最大值, 自然为整数的最小值, 否则, 为(cut-1)/2
            double l1 = (mid1 == 0) ? Integer.MIN_VALUE : nums1[(mid1-1)/2];
            double l2 = (mid2 == 0) ? Integer.MIN_VALUE : nums2[(mid2-1)/2];
            double r1 = (mid1 == len1 * 2) ? Integer.MAX_VALUE : nums1[mid1/2];
            double r2 = (mid2 == len2 * 2) ? Integer.MAX_VALUE : nums2[mid2/2];

            if (l1 > r2) lo = mid2 + 1;  // 表示nums1的左部分有太多的大值, 则将C1左移, 即C2右移
            else if (l2 > r1) hi = mid2 - 1;  // 表示num2的左部分有太多的大值, 则将C2左移
            else return (Math.max(l1, l2) + Math.min(r1, r2)) / 2;  // 找到
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums1 = {1,2,3,4,5};
        int[] nums2 = {1,1,1};
        System.out.println(findMedianSortedArrays(nums1, nums2));
    }
}
