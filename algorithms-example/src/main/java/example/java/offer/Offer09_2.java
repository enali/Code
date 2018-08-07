package example.java.offer;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）
 *
 * 思路：
 * 最后一跳有两个可能的值, 跳一个台阶, 跳两个台阶
 * 以d[n]表示n级台阶的可能跳法, 则d[n] = d[n-1] + d[n-2];
 * 正好是Fibonacci数列的递推式.
 * 如果马上看不出来, 推荐从n=1,2,3,4...手工计算一下就OK
 */
public class Offer09_2 {
    // 其实就是求fibonacci数列
    public static int jumpFloor(int n) {
        if (n < 0) throw new IllegalArgumentException("n >= 0");

        int a = 0;
        int b = 1;
        for (int i = 0; i < n; i++) {
            int tmp = a;
            a = b;
            b = a + tmp;
        }
        return a;
    }
}
