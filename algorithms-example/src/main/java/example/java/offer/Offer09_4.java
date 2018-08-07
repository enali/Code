package example.java.offer;

/**
 * 题目：
 * 我们可以用2*1的小矩形横着或者竖着去覆盖更大的矩形。请问用n个2*1的小矩形无重叠地覆盖一个2*n的大矩形，总共有多少种方法？
 *
 * 思路：
 * 2*n的矩形的高固定, 只能为2, 则要么竖着放一个2*1矩形, 要么横着放2个2*1矩形.
 * 即, 大矩形的宽边, 由1或2构成, 可联想那个青蛙跳的问题, 即一个数, 要么由1构成, 要么由2构成, 问有多少种构成法
 * 又或者, 最后一个小矩形, 要么竖着, 要么横着. d[n] = d[n-1] + d[n-2]
 * 又或者, 从target为1, 2, 3 ,4, 手工推导也能看出来
 *
 * 注意：
 * 思路并不是最后空一格还是空两格的问题，而是最后的矩形是横放还是坚放的问题, 因为探求的是可旋转的方式
 */
public class Offer09_4 {
    public static int rectCover(int n) {
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
