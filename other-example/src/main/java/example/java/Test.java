package example.java;

import java.math.BigDecimal;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
//        Scanner scan = new Scanner(System.in);
//        int n = scan.nextInt();
//        double prince = scan.nextDouble();
//
//        double sum1 = factory1(n, prince);
//        double sum2 = factory2(n, prince);
//        int factory = sum1 > sum2 ? 1 : 2;
//        System.out.println(factory);
    }

    private static double factory1(int n, double prince) {
        double sum = n * prince;
        if (n >= 3) sum *= 0.7;
        if (sum >= 50) sum -= 10;
        return sum;
    }

    private static double factory2(int n, double prince) {
        double sum = n * prince;
        sum -= (int) sum / 10 * 2;
        if (sum >= 99) sum -= 6;
        return sum;
    }
}



