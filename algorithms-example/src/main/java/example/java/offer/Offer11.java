package example.java.offer;

/**
 * 题目:
 * 给定一个double类型的浮点数base和int类型的整数exponent。求base的exponent次方。
 *
 * 思路:
 * 迭代解法, 最主要是保存每次exp / 2的值到mid, 考虑到元素数跟整数的bit位数相同, 取32
 * 因为每次都只跟d[n/2]相关, 所以没必要用数组保存中间结果
 */
public class Offer11 {
    public static double power(double base, int exponent) {
        if (exponent == 0) return 1;
        if (exponent == 1) return base;

        int tmp = exponent;
        if (tmp < 0) tmp = -tmp; // 处理负数

        // 以13为例, 等于6+6+1, 6等于3+3, 3等于1+1+1, 数相乘等于指数相加
        int[] mid = new int[32];
        int idx = 0;
        while (tmp != 0) {
            mid[idx] = tmp;
            idx++;
            tmp /= 2;
        }

        double product = 1;
        for (int i=idx-1; i>=0; i--) {
            product = product * product * (mid[i] % 2 == 0 ? 1 : base);
        }
        return product;
    }

    public static void main(String[] args) {
        System.out.println(power(3.28, 1));
        System.out.println(power(3.28, 13));
    }
}
