package example.jvm;

import java.io.Serializable;

public class Overload {
    // 重载的优先级: char -> int -> long -> float -> double
    // -> Character 装箱
    // -> Serializable, Comparable 装箱后, 向接口类型转型
    // -> Object 装箱后, 向父类转型
    // -> char...或Character... 变参
    // 若多个重载的优先级相同, 则编译不通过

//    public static void sayHello(char obj) {
//        System.out.println("hello, char");
//    }
//    public static void sayHello(int obj) {
//        System.out.println("hello, int");
//    }
//    public static void sayHello(long obj) {
//        System.out.println("hello, long");
//    }
//    public static void sayHello(float obj) {
//        System.out.println("hello, float");
//    }
//    public static void sayHello(double obj) {
//        System.out.println("hello, float");
//    }

//    public static void sayHello(Character obj) {
//        System.out.println("hello, Character");
//    }
    public static void sayHello(Serializable obj) {
        System.out.println("hello, Serializable");
    }
//    public static void sayHello(Comparable<Character> obj) {
//        System.out.println("hello, Comparable");
//    }
//    public static void sayHello(Object obj) {
//        System.out.println("hello, Object");
//    }

    public static void sayHello(char... obj) {
        System.out.println("hello, char....");
    }
//    public static void sayHello(Character... obj) {
//        System.out.println("hello, Character....");
//    }

    // 永远不会转型为Integer, 虽然char->int可以转型
//    public static void sayHello(Integer obj) {
//        System.out.println("hello, Integer");
//    }

    public static void main(String[] args) {
        sayHello('a');
    }
}
