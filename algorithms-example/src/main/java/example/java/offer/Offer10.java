package example.java.offer;

/**
 * 题目：
 * 输入一个整数，输出该数二进制表示中1的个数。其中负数用补码表示。
 *
 * 思路：
 * 非常奇妙, 把任意二进制数看成是100...的组合, 如9, 对应1001, 对应1000和1
 * 将一个组件减1, 再与组件与, 如1000 - 1 = 0111, 再与1000与, 得0
 * 所以, while其实只循环了1的个数次.
 */
public class Offer10 {
    // 能处理负数
    public static int numberOf1(int n) {
        if (n == 0) return 0;
        int count = 1;
        while ((n & n-1) != 0) {
            count++;
            n &= n-1;
        }
        return count;
    }

    public static void main(String[] args) {
        System.out.println(numberOf1(-1));  // 32
        System.out.println(numberOf1(0));  // 0
        System.out.println(numberOf1(1));  // 1
        System.out.println(numberOf1(2));  // 1
        System.out.println(numberOf1(3));  // 2
        System.out.println(numberOf1(4));  // 1
        System.out.println(numberOf1(5));  // 2
    }
}
