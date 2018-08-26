package example.java.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * 题目:
 * 已知一组数(其中无重复元素), 求这组数可以组成的所有子集. 结果中不可有无重复的子集
 *
 * 思考:
 * 以[1,2,3]为例, 每个元素都存在选或不选的问题, 不能用for循环, 是因为元素的个数不确定
 */
public class LeetCode78 {
    public List<List<Integer>> subsets2(int[] nums) {
        List<List<Integer>> rst = new ArrayList<>();
        // 以位运算, n个元素一共2^n可能性
        long allSet = 1 << nums.length;
        for (int i=0; i<allSet; i++) {  // 每个i表示一种可能性, 其每个比特位表示是否选择第j个元素
            List<Integer> path = new ArrayList<>();
            for (int j=0; j<nums.length; j++) {
                if ((i & (1 << j)) == 1) path.add(nums[j]);
            }
            rst.add(path);
        }
        return rst;
    }

    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> rst = new ArrayList<>();
        select(nums, 0, new ArrayList<>(), rst);
        return rst;
    }

    private void select(int[] nums, int idx, List<Integer> path, List<List<Integer>> rst) {
        if (idx == nums.length) {  // 最后一个元素也做出选择了, 可以结束了
            rst.add(new ArrayList<>(path));
            return;
        }
        path.add(nums[idx]);  // 选
        select(nums, idx+1, path, rst);
        path.remove(path.size()-1);  // 不选, 最重要的是此步, 需要回退
        select(nums, idx+1, path, rst);
    }

    public static void main(String[] args) {
        LeetCode78 lc = new LeetCode78();
        List<List<Integer>> rst = lc.subsets(new int[]{1,2,3});
        for (List<Integer> path : rst)
            System.out.println(path);
    }
}
