package example.java.offer;

import java.util.Arrays;

/**
 * 题目:
 * 从扑克牌中随机抽5张牌，判断是不是一个顺子， 即这5张牌是不是连续的。
 * 2～10为数字本身， A为1。 J为11、Q为12、 为13。小王可以看成任意数字。
 * 大小王可以当做任意数字
 *
 * 思考:
 * 首先把数组排序，再统计数组中0 的个数，最后统计排序之后的数组中相邻数字之间的空缺总数。
 * 如果空缺的总数小于或者等于0 的个数，那么这个数组就是连续的：反之则不连续。
 */
public class Offer44 {
    public static boolean isContinuous(int[] numbers) {
        if (numbers == null) return false;
        if (numbers.length == 0) return false;
        if (numbers.length == 1) return true;

        Arrays.sort(numbers);
        int gapCnt = 0;
        int idx = 0;
        while (idx < numbers.length && numbers[idx] == 0) idx++;
        if (idx >= numbers.length-1) return true;  // 全是0, 或只有1个非0, 自然为顺子
        int zeroCnt = idx;
        idx++;
        while (idx < numbers.length) {
            int tmp = numbers[idx] - numbers[idx-1];
            if (tmp == 0) return false;  // 出现相同牌, 则肯定不是顺子
            gapCnt += tmp - 1;  // 不同牌, 如3/4, 则间隔0
            idx++;
        }
        return gapCnt <= zeroCnt;  // 如果间隔多, 则不是顺子
    }
}
