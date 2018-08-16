package example.stdlib;

import java.util.Arrays;
import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Supplier;

public class FunctionExample {
    // 无参无返回值, 定义run方法
    public static void repeat(int n, Runnable r) {
        for (int i=0; i<n; i++)
            r.run();
    }

    // 无参, 返回T, 定义get方法
    public static int[] fill(int n, Supplier<Integer> s) {
        int[] rst = new int[n];
        for (int i=0; i<n; i++) rst[i] = s.get();
        return rst;
    }

    // 一个参, 无返回, 定义accept方法
    public static void print(int[] ary, Consumer<Integer> cons) {
        for (int i=0; i<ary.length; i++)
            cons.accept(ary[i]);
    }

    public static void main(String[] args) {
        System.out.println("==== repeat Runnable ====");
        repeat(10, () -> System.out.println("hello, world"));

        System.out.println("==== fill Supplier ====");
        Random r = new Random();
        System.out.println(Arrays.toString(fill(10, r::nextInt)));  // 应用实例的方法引用

        System.out.println("==== print Consumer ====");
        int[] ary = {1, 2, 3, 4, 5};
        print(ary, System.out::println);
    }
}
