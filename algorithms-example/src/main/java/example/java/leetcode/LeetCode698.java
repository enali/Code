package example.java.leetcode;

import java.util.Arrays;

/**
 * 题目:
 * 能否将一个数组分成k份非空的子数组, 使各个子数组的和相等
 *
 * 思路:
 * sum % k == 0成立, 每个子数组的和为sum / k, 数组中不存在比sum/k大的元素
 */
enum Result { TRUE, FALSE}

public class LeetCode698 {
    /**
     *
     * @param used 每个比特位表示对应索引元素是否已经使用
     * @param todo 表示当前未使用元素的和
     * @param memo
     * @param nums 原数组
     * @param target 每个子数组的和
     * @return
     */
    boolean search(int used, int todo, Result[] memo, int[] nums, int target) {
        if (memo[used] == null) {  // 当使用所有元素时, memo[used]为TRUE, 跳过条件
            memo[used] = Result.FALSE;
            // 比如target为5, todo为17, 则还差2, 此处的targ表示要找个2
            int targ = (todo - 1) % target + 1;
            for (int i = 0; i < nums.length; i++) {
                // 1th条件表示, 第i个元素未使用, 2th条件表示, 此未使用的元素值要<=需要的值
                if ((((used >> i) & 1) == 0) && nums[i] <= targ) {
                    // used | (1<<i)将此元素标为已使用
                    if (search(used | (1<<i), todo - nums[i], memo, nums, target)) {
                        memo[used] = Result.TRUE;
                        break;
                    }
                }
            }
        }
        return memo[used] == Result.TRUE;
    }

    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = Arrays.stream(nums).sum();
        if (sum % k > 0) return false;  // 若不能平分, 则false

        Result[] memo = new Result[1 << nums.length];  // 记录, 每个特定的used情况是否可用
        memo[(1 << nums.length) - 1] = Result.TRUE;  // 如果使用到了所有元素, 则表示成功切分, 自然为TRUE
        return search(0, sum, memo, nums, sum / k);
    }

    public boolean canPartitionKSubsets2(int[] nums, int k) {
        int N = nums.length;
        Arrays.sort(nums);
        int sum = Arrays.stream(nums).sum();
        int target = sum / k;
        // 如果不能整除, 不能存在大于target的元素
        if (sum % k > 0 || nums[N - 1] > target) return false;

        boolean[] dp = new boolean[1 << N];
        dp[0] = true;
        int[] total = new int[1 << N];  // 对应每种使用情况的, 已使用元素的和

        // 若以每个比特位表示相应元素是否使用, 则此处遍历所有可能的使用情况
        for (int state = 0; state < (1 << N); state++) {
            if (!dp[state]) continue;
            for (int i = 0; i < N; i++) {
                int future = state | (1 << i);  // 使用ith元素
                // state != future表示此元素未被使用过
                // !dp[future]表示加上ith元素的使用情况, 对应的结果还未成功
                if (state != future && !dp[future]) {
                    // 如果当前元素 <= 剩余需要的补足target的值
                    if (nums[i] <= target - (total[state] % target)) {
                        dp[future] = true;
                        total[future] = total[state] + nums[i];  // 加上ith元素的总和
                    } else {
                        break;
                    }
                }
            }
        }
        return dp[(1 << N) - 1];  // 使用所有元素的情况
    }

    public static void main(String[] args) {
        LeetCode698 lc = new LeetCode698();
        int[] nums = {4,3,2,3,5,2,1};
        int k = 4;
        System.out.println(lc.canPartitionKSubsets(nums, k));
    }
}
