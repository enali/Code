package example.java.offer;

/**
 * 题目:
 * 写一个函数，求两个整数之和，要求在函数体内不得使用+、-、*、/四则运算符号。
 *
 * 思路:
 * 考虑二进制的加法:
 * 1, 先不考虑进位, 则0/0,1/1都为0, 0/1, 1/0则为1, 与异或同
 * 2, 考虑进位, 只有1/1才进位, 进位相当于左移1
 * 3, 将两都的结果相加
 * 4, 重复
 */
public class Offer47 {
    public static int add(int num1, int num2) {
        int sum;
        int carry;
        do {
            sum = num1 ^ num2;
            carry = (num1 & num2) << 1;  // 进位相当于左移1
            num1 = sum;
            num2 = carry;
        } while (carry != 0);  // 进位后相加, 仍然可能进位
        return sum;
    }

    public static void main(String[] args) {
        System.out.println(add(27, 13));
    }
}
