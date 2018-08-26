package example.java.leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 题目:
 * 已知一个数组(其中有重复元素), 求这组数可以组成的所有子集. 结果中无重复的子集.
 */
public class LeetCode90 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> each = new ArrayList<>();
        helper(res, each, 0, nums);
        return res;
    }

    public void helper(List<List<Integer>> res, List<Integer> each, int pos, int[] n) {
        if (pos <= n.length) {
            res.add(each);
        }
        int i = pos;
        while (i < n.length) {
            each.add(n[i]);
            helper(res, new ArrayList<>(each), i + 1, n);
            each.remove(each.size() - 1);
            i++;
            while (i < n.length && n[i] == n[i - 1]) {i++;}
        }
        return;
    }

    public static void main(String[] args) {
        LeetCode90 lc = new LeetCode90();
        int[] nums = {1, 2, 2, 2};
        List<List<Integer>> rst = lc.subsetsWithDup(nums);
        for (List<Integer> a : rst)
            System.out.println(a);
    }
}
