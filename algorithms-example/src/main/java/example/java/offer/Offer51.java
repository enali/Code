package example.java.offer;

/**
 * 题目:
 * 在一个长度为n的数组里的所有数字都在0到n-1的范围内。 数组中某些数字是重复的，但不知道有几个数字是重复的。
 * 也不知道每个数字重复几次。请找出数组中任意一个重复的数字。 例如，如果输入长度为7的数组{2,3,1,0,2,5,3}，
 * 那么对应的输出是第一个重复的数字2。
 *
 * 思路:
 * 考虑到数组中的数字都在0到n-1范围内, 则若所有数字不同, 将其排好序后, idx对应的数字为idx;
 * 因此, 可以对数组的每个数字, 都将其交换到其应该属于的位置(即排序后的位置), 如果数字跟其应该交换的位置的
 * 数字相同, 则是重复数字.
 * {2,3,1,0,2,5,3}, 对于索引为0, 先跟索引2交换, 变为{1, 3, 2, 0, 2, 5, 3}, 再跟索引1交换, 变为{3, 1, 2, 0, 2, 5, 3}
 * 再跟索引3交换, {0, 1, 2, 3, 2, 5, 3}, 然后索引1/2/3都在其应在的位置, 到索引4, 其应交换到2, 但2上的数字就是2, 因此2重复
 */
public class Offer51 {
    public static int duplicate(int[] ary) {
        int len = ary.length;
        int idx = 0;
        while (idx < len) {
            if (ary[idx] != idx) {
                if (ary[idx] == ary[ary[idx]]) break;
                swap(ary, idx, ary[idx]);
            } else idx++;
        }
        return ary[idx];
    }

    private static void swap(int[] ary, int i, int j) {
        if (i == j) return;
        int tmp = ary[i];
        ary[i] = ary[j];
        ary[j] = tmp;
    }

    public static void main(String[] args) {
        int[] ary = {2, 3, 1, 0, 2, 5, 3};
        System.out.println(duplicate(ary));
    }
}
