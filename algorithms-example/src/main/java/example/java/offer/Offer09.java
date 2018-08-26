package example.java.offer;

/**
 * 斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项（从0开始，第0项为0）。
 * n<=39
 */
public class Offer09 {
    private static int[] fib = new int[40];  // 因为n是有范围的，则可以直接缓存范围内的所有值

    static {
        fib[1] = 1;
        for (int i=2; i < fib.length; i++)
            fib[i] = fib[i-1] + fib[i-2];
    }

    public static int fibonacci(int n) {
        int a = 0;
        int b = 1;
        for (int i = 0; i < n; i++) {
            int tmp = a;
            a = b;
            b = a + tmp;
        }
        // 关键是输出a还是b
        return a;
    }

    public static int fibonacci2(int n) {
        return fib[n];
    }

    public static void main(String[] args) {
        System.out.println(fibonacci(0));
        System.out.println(fibonacci(1));
        System.out.println(fibonacci(2));
        System.out.println(fibonacci(3));

        System.out.println("==========");

        System.out.println(fibonacci2(0));
        System.out.println(fibonacci2(1));
        System.out.println(fibonacci2(2));
        System.out.println(fibonacci2(3));
    }
}
