package example.java.other;

/**
 * 题目:
 * 在旋转的有序数组中, 如[7, 8, 9, 1, 2, 3, 4], 找一个数k
 *
 * 思考:
 * 参考: {@link example.java.offer.Offer08}
 * 但略有不同, 大体思路上, 仍然是二分查找.
 */
public class FindInRotateOrderSeq {
    public static int find(int[] nums, int k) {
        int lo = 0, hi = nums.length-1;
        while (lo < hi) {
            // mid要么指向前一个升序序列, 要么指向后一个升序序列. 若k在升序序列中
            int mid = lo + (hi - lo) / 2;
            if (nums[mid] == k) return mid;
            if (nums[lo] < nums[mid]) {  // 前一个升序序列
                if (k < nums[mid]) return binarySearch(nums, lo, mid-1, k);  // k在升序序列中, 则二分查找
                else lo = mid + 1;  // 否则缩减范围
            } else if (nums[mid] < nums[hi]) {  // 后一个升序序列
                if (k > nums[mid]) return binarySearch(nums, mid+1, hi, k);
                else hi = mid - 1;
            }
        }
        return -1;
    }

    private static int binarySearch(int[] nums, int lo, int hi, int k) {
        while (lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (k == nums[mid]) return mid;
            else if (k > nums[mid]) lo = mid + 1;
            else hi = mid - 1;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {7, 8, 9, 1, 2, 3, 4};

        System.out.println("4: " + find(nums, 2));
        System.out.println("0: " + find(nums, 7));
        System.out.println("-1: " + find(nums, 5));
    }
}
