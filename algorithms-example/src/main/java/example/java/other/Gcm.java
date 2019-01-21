package example.java.other;

/**
 * 求两个数的最小公倍数
 */
public class Gcm {
    public static int lcm(int a, int b) {
        int m = a, n = b;
        int t = m % n;
        while (t != 0) {
            m = n;
            n = t;
            t = m % n;
        }
        return a * b / n;
    }
}
