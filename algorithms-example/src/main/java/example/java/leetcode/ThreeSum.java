package example.java.leetcode;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

class ThreeSum {
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new LinkedList<>();
        for (int i=0; i<nums.length-2; i++) {
            if (i==0 || nums[i] != nums[i-1]) {
                int lo=i+1, hi=nums.length-1, sum=0-nums[i];
                while (lo < hi) {
                    boolean add_lo = false, sub_hi = false;
                    if (nums[lo] + nums[hi] == sum) {
                        res.add(Arrays.asList(nums[i], nums[lo], nums[hi]));
                        add_lo = true; sub_hi = true;
                    } else if (nums[lo] + nums[hi] < sum) {
                        add_lo = true;
                    } else {
                        sub_hi = true;
                    }
                    if (add_lo) {
                        while (lo < hi && nums[lo] == nums[lo+1]) lo++;
                        lo++;
                    }
                    if (sub_hi) {
                        while (lo < hi && nums[hi] == nums[hi-1]) hi--;
                        hi--;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        ThreeSum s = new ThreeSum();
        int[] nums = {-1, 0, 1, 2, -1, -4};
        List<List<Integer>> res = s.threeSum(nums);
        for (List<Integer> l : res) {
            for (int a : l) {
                System.out.print(a + " ");
            }
            System.out.println();
        }
    }
}
