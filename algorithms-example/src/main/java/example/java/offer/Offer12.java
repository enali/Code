package example.java.offer;

/**
 * 题目:
 * 输入数字n, 按顺序打印出从1最大的n位十进制数, 比如输入3, 则打印出1,2,3...999
 *
 * 思考:
 * 1, 采取递归, 在每个数位上遍历0-9
 * 2, 采取模拟进位
 */
public class Offer12 {
    public static void printOneToNthDigits2(int n) {
        byte[] num = new byte[n+1];

        // 模拟进位
        while (num[n] == 0) {
            printArray(num);
            num[0]++;
            int idx = 0;
            while (num[idx] == 10) {
                num[idx++] = 0;
                num[idx] += 1;
            }
        }
    }

    public static void printOneToNthDigits(int n) {
        byte[] num = new byte[n];  // 存储每个位的数字
        printOneToNthDigits(n, num);
    }

    private static void printOneToNthDigits(int n, byte[] num) {
        if (n == 0) {
            printArray(num);
            return;
        }
        for (int i=0; i<10; i++) {  // 变换每个位的数字
            num[n-1] = (byte)i;
            printOneToNthDigits(n-1, num);
        }
    }

    // 将数组作为整数打印, 左侧为低位, 最右侧为高位
    private static void printArray(byte[] num) {
        int idx = num.length-1;
        while (idx >= 0 && num[idx] == 0) idx--;
        if (idx < 0) return;
        while (idx >= 0) System.out.print(num[idx--]);
        System.out.println();
    }

    public static void main(String[] args) {
        int n = 3;
        printOneToNthDigits2(n);
    }
}
