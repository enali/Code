package example.java.other;

/**
 * 题目:
 * 输入一个数n, 求n!的结果的所有位数的和. 如5! = 120, 和为1 + 2 + 0 = 3
 */
public class SumOfFactorial {
    public static int cal(int n) {
        if (n < 0) return 1;
        int[] record = new int[100];
        record[0] = 1;
        // 从个位开始, 每一位直接乘以乘数再加上进位数(初始进位为0), 结果模10作为保留数, 结果除以10作为进位数
        for (int i=n; i>=1; i--) {  // 阶乘的每个数
            int e = 0;  // 进位
            for (int j=0; j<100; j++) {  // 从个位开始处理
                int tmp = record[j];
                record[j] = (e + i * tmp) % 10;
                e = (e + tmp * i) / 10;
            }
        }
        int total = 0;
        for (int i=0; i<100; i++) {
            total += record[i];
        }
        return total;
    }

    public static void main(String[] args) {
        System.out.println(cal(0));  // 1
        System.out.println(cal(1));  // 1
        System.out.println(cal(2));  // 2
        System.out.println(cal(3));  // 6
        System.out.println(cal(4));  // 6
        System.out.println(cal(14));  // 14!, 45
    }
}