package example.java.offer;

import com.sun.istack.internal.NotNull;

/**
 * 题目:
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历的结果。如果是则输出Yes,否则输出No。假设输入的数组的任意两个数字都互不相同。
 *
 * 思路:
 * 二叉搜索树的元素值以左根右为顺序, 因此, 对于后序遍历来说, 其最显著的特征是: 最后一个元素为根, 因此, 前面所有比根小的为左树, 所有
 * 比根大的元素为右树.
 */
public class Offer24 {
    public static boolean verifySequenceOfBst(@NotNull int[] sequence) {
        int len = sequence.length;
        if (len <= 1) return true;
        return verifySequenceOfBst(sequence, 0, len-1);
    }

    private static boolean verifySequenceOfBst(int[] sequence, int lo, int hi) {
        if (lo >= hi) return true;
        int root = sequence[hi];  // 取根
        int leftIdx = lo;
        // 找到所有左树
        while (leftIdx < hi && sequence[leftIdx] < root)
            leftIdx++;
        int tmp = leftIdx;
        while (tmp < hi) {
            // 要求右树全部大于根元素
            if (sequence[tmp] < root) return false;
            tmp++;
        }
        // 要考虑到元素可能全左或全右, 此时, lo可能大于hi
        return verifySequenceOfBst(sequence, lo, leftIdx-1)
                && verifySequenceOfBst(sequence, leftIdx, hi-1);
    }

    public static void main(String[] args) {
        int[] data = {4, 8, 6, 12, 16, 14, 10};
        System.out.println("true: " + verifySequenceOfBst(data));

        int[] data2 = {4, 6, 7, 5};
        System.out.println("true: " + verifySequenceOfBst(data2));

        int[] data3 = {1, 2, 3, 4, 5};
        System.out.println("true: " + verifySequenceOfBst(data3));

        int[] data4 = {5, 4, 3, 2, 1};
        System.out.println("true: " + verifySequenceOfBst(data4));


        int[] data5 = {5};
        System.out.println("true: " + verifySequenceOfBst(data5));

        int[] data6 = {7, 4, 6, 5};
        System.out.println("false: " + verifySequenceOfBst(data6));

        int[] data7 = {4, 6, 12, 8, 16, 14, 10};
        System.out.println("false: " + verifySequenceOfBst(data7));
    }
}
