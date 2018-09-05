package example.jvm;

import java.util.Arrays;

/**
 * 测试接口的一些性质
 */
interface InterfaceTest {
    // interface中的变量全部默认为`public static final`
    String a = "hello, world";

    // 定义静态方法, 通过InterfaceTest.world()进行调用
    static void world() {
        System.out.println("world");
    }

    // 定义默认方法, 若类继承接口, 会成为实例方法
    default void hello() {
        System.out.println("hello");
    }
}

public class ClassTest implements InterfaceTest {
    static {
        i = 2;  // 能赋值, 但其实没意思, 因为, 后面还会把i=1赋值上去
//        System.out.println(i);  // 不能访问
    }
    static int i = 1;

    // 覆盖接口中定义的默认方法
    public void hello() {
        InterfaceTest.super.hello();  // 调用接口中的默认方法
        InterfaceTest.world();  // 调用接口中的静态方法
        System.out.println(", ClassTest");
    }

    public static void main(String[] args) {
        System.out.println(ClassTest.a);  // "hello, world"

        ClassTest im = new ClassTest();
        im.hello();

        InterfaceTest.world();  // world

        System.out.println(i);  // i = 1
    }
}
