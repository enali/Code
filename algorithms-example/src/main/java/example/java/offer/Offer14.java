package example.java.offer;

import java.util.Arrays;

/**
 * 题目:
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有的奇数位于数组的前半部分，
 * 所有的偶数位于数组的后半部分，并保证奇数和奇数，偶数和偶数之间的相对位置不变
 * <p>
 * 思路:
 * 宏观上看, 这是个插入排序的问题, 区别是, 插入排序总是将最小值交换到最前面
 * 这里是将奇数值交换到最前面, 只在j为奇数, j-1为偶数时才交换
 */
public class Offer14 {
    public static void reorderArray(int[] ary) {
        if (ary == null || ary.length <= 1) return;

        for (int i = 1; i < ary.length; i++) {
            for (int j = i; j > 0 && ary[j] % 2 != 0 && ary[j - 1] % 2 == 0; j--) {
                int tmp = ary[j];
                ary[j] = ary[j - 1];
                ary[j - 1] = tmp;
            }
        }
    }

    public static void main(String[] args) {
        int[] ary = {1, 2, 3, 4, 5};
        reorderArray(ary);
        System.out.println(Arrays.toString(ary));
    }
}
