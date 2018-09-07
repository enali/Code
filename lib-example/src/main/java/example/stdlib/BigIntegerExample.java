package example.stdlib;

import java.math.BigInteger;

public class BigIntegerExample {
    public static void main(String[] args) {
        BigInteger b1 = new BigInteger("123456789");
        BigInteger b2 = new BigInteger("987654321");

        System.out.println(b1.add(b2));
        System.out.println(b1.multiply(b2));
        System.out.println(b1.subtract(b2));
        System.out.println(b1.divide(b2));
    }
}
