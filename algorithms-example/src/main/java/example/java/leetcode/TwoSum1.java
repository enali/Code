package example.java.leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 题目:
 * 找到数组中, 和为target的两个元素的索引
 */
public class TwoSum1 {
    public static int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> m = new HashMap<>();
        int[] rst = new int[2];
        for (int i=0; i<nums.length; i++) {
            int tmp = target - nums[i];
            if (m.containsKey(tmp)) {
                rst[0] = m.get(tmp);
                rst[1] = i;
                break;
            } else {
                m.put(nums[i], i);
            }
        }
        return rst;
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        System.out.println(Arrays.toString(twoSum(nums, target)));
    }
}
