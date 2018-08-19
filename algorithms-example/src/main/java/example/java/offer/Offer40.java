package example.java.offer;

import java.util.Arrays;

/**
 * 题目:
 * 一个整型数组里除了两个数字之外，其他的数字都出现了偶数次。请写程序找出这两个只出现一次的数字。
 * 例如, {2, 4, 3, 6, 3, 2, 5, 5}, 除了4/6, 其它数字都出现了2次
 *
 * 思路:
 * 利用异或, 相同数字异或为0, 则数组所有数字异或的结果不为0.
 * 如果把数组分成两部分, 每部分都包含一个只出现1次的数字, 则将每部分分别异或, 即可得到那个数字.
 */
public class Offer40 {
    public static int[] findNumbersAppearOnce(int[] ary) {
        int[] rst = new int[2];

        // 求数组的异或结果
        int len = ary.length;
        int xor = 0;
        for (int e: ary) xor ^= e;

        // 找异或结果的为1的某位, 作为mark将数组分成两部分, 易知, 相同的2个数字其mark位一定相同,
        // 不同的2个只出现1次的数字, 其mark位一定不同(若相同, 则异或结果的mark位一定为0)
        int mark = 1;
        while ((xor & mark) == 0) mark <<= 1;

        // 将每部分分别异或
        for (int i=0; i<len; i++) {
            if ((ary[i] & mark) == 0) rst[0] ^= ary[i];
            else rst[1] ^= ary[i];
        }
        return rst;
    }

    public static void main(String[] args) {
        int[] ary1 = {2, 4, 3, 6, 3, 2, 5, 5};
        int[] rst1 = findNumbersAppearOnce(ary1);
        System.out.println(Arrays.toString(rst1));

        int[] ary2 = {4, 6};
        int[] rst2 = findNumbersAppearOnce(ary2);
        System.out.println(Arrays.toString(rst2));

        int[] ary3 = {4, 6, 1, 1, 1, 1};
        int[] rst3 = findNumbersAppearOnce(ary3);
        System.out.println(Arrays.toString(rst3));
    }
}
