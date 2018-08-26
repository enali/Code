package example.java.offer;

import java.util.Arrays;

/**
 * 题目:
 * 输入一个正整数数组，把数组里所有数字拼接起来排成一个数，打印能拼接出的所有数字中最小的一个。例如输入数组{3，32，321}，
 * 则打印出这三个数字能排成的最小数字为321323。
 *
 * 思考:
 * 本质在于实现一个比较器:
 * 1, 比较33, 34, 若有一个字符小, 则小, 若字符相同, 则比较下一个字符
 * 2, 比较3, 32, 若在索引1, 一方没有字符, 则索引跟前一个字符比, 如果小则小
 */
public class Offer33 {
    public static String printMinNumber(int[] numbers) {
        Integer[] newNums = new Integer[numbers.length];
        for (int i = 0; i < numbers.length; i++) newNums[i] = numbers[i];
        // 一个新的比较器, 将pq与qp比较, 若用数字去实现的话, a * 10 ^ (b.len) + b跟b * 10^(a.len) + a
        Arrays.sort(newNums, (p, q) -> {
            String tmp = p.toString() + q.toString();
            return tmp.compareTo(q.toString() + p.toString());
        });
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < newNums.length; i++) sb.append(newNums[i].toString());
        return sb.toString();
    }
}
