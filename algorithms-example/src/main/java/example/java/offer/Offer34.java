package example.java.offer;

/**
 * 题目:
 * 把只包含质因子2、3和5的数称作丑数（Ugly Number）。例如6、8都是丑数，但14不是，因为它包含质因子7。
 * 习惯上我们把1当做是第一个丑数。求按从小到大的顺序的第N个丑数。
 *
 * 思路:
 * 如果一个数的因子只有2/3/5, 那么它一定是多个2相乘, 多个3相乘, 多个5相乘的结果. 换句话说, 它一定是前面某个丑数乘以2/3/5的结果.
 * 那么从1开始, 以乘2/3/5的结果为结果, 一个个找到第n位.
 */
public class Offer34 {
    public static int uglyNum(int n) {
        if (n < 0) return -1;
        int[] tmp = new int[n];
        tmp[0] = 1;
        int i2 = 0, i3 = 0, i5 = 0;

        for (int i=1; i<n; i++) {
            int ugly = Math.min(tmp[i2] * 2,
                    Math.min(tmp[i3] * 3, tmp[i5] * 5));
            if (tmp[i2] * 2 == ugly) i2++;
            if (tmp[i3] * 3 == ugly) i3++;
            if (tmp[i5] * 5 == ugly) i5++;
            tmp[i] = ugly;
        }
        return tmp[n-1];
    }

    public static void main(String[] args) {
        System.out.println(uglyNum(1));
        System.out.println(uglyNum(2));
        System.out.println(uglyNum(3));
        System.out.println(uglyNum(4));
        System.out.println(uglyNum(5));
        System.out.println(uglyNum(6));
        System.out.println(uglyNum(7));
        System.out.println(uglyNum(8));
        System.out.println(uglyNum(9));
    }
}
