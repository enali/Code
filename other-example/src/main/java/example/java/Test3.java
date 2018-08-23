package example.java;

import java.util.Scanner;

public class Test3 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        double price_temp = scan.nextDouble();
        int price = (int) price_temp * 100;

        int sum1 = factory1(n, price);
        int sum2 = factory2(n, price);
//        double eps = 1e-16;
        int factory = sum1 > sum2 ? 1 : 2;
        if (sum1 == sum2) {
            factory = 0;
        }
        System.out.println(factory);
    }

    private static int factory1(int n, int price) {
        int sum = n * price;
        if (n >= 3) sum *= 0.7;
        if (sum < 50) sum += 10;  // 不到50需要加10元运费
        return sum;
    }

    private static int factory2(int n, int price) {
        int sum = n * price;
        sum -= (long) sum / 10 * 2;
        if (sum < 99) sum += 6;  // 不到99需要加6元运费
        return sum;
    }
}
