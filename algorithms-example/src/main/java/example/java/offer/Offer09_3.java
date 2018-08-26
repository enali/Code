package example.java.offer;

/**
 * 题目：一只青蛙一次可以跳上1级台阶，也可以跳上2级, 也可以跳n级。求该青蛙跳上一个n级的台阶总共有多少种跳法（先后次序不同算不同的结果）
 *
 * 思路：
 * 这是个排列组和问题, 有n个台阶
 * 一种跳法对应台阶的一个切分: 不切刀, 则一下子跳n阶, 一种; 切一刀, 即两个跳完, 共C(n-1, 1); 切两人刀, 即三下跳完, 共C(n-1, 2)
 * C(n-1, 0) + C(n-1, 1) + C(n-1, 2) + ... + C(n-1, n-1) = 2^(n-1)
 */
public class Offer09_3 {
    public static int jumpFloor(int n) {
        if (n <= 0) return 0;
        int rst = 1;
        for (int i = 1; i < n; i++) {
            rst *= 2;
        }
        return rst;
    }

    public static void main(String[] args) {
        System.out.println(jumpFloor(0));
        System.out.println(jumpFloor(1));
        System.out.println(jumpFloor(2));
        System.out.println(jumpFloor(3));
        System.out.println(jumpFloor(4));
    }
}
