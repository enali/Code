package example.java.offer;

/**
 * 题目:
 * 0, 1, … , n-1 这n个数字排成一个圈圈，从数字0开始每次从圆圏里删除第m个数字。求出这个圈圈里剩下的最后一个数字。
 *
 * 思路:
 * 1, 建立环进行模拟
 * 2, 递归公式
 * * 记f(n,m)表示有n个数字的圈, 删除第m个数字, 最终的数字
 * * 记f'(n-1,m)表示, 删除第1个数字后k=(m-1)%n后, 在剩余数字中继续删除第m个数字, 最终的数字, 晚知f(n, m) == f'(n-1,m)
 * * 将删除k后, k+1, k+2, ..., n-1, 0, 1, ..., k-1映射到0, 1, 2, ...., n-2中, 映射关系P(x) = (x-k-1)%n, 记映射后的序列的f(n-1, m)
 * * 如果映射后, 求出f(n-1, m), 则逆映射, 晚易知f'(n-1, m) = P'(f(n-1, m)) = (f(n-1, m) + k + 1) % n, 其中k=(m-1)%n
 * * 得出递推式f(n, m) = (f(n-1, m) + m) % n
 */
public class Offer45 {
    public static int lastRemaining(int n, int m) {
        if (n <= 0 || m <= 0) return -1;
        if (n == 1) return 0;
        if (m == 1) return n-1;

        int rst = 0, i = 1;
        while (i < n) {
            i++;
            rst = (rst + m) % i;
        }
        return rst;
    }

    public static void main(String[] args) {

    }
}
