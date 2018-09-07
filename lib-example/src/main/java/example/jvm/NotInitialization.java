package example.jvm;

class SuperClass {
    static {
        System.out.println("SuperClass init!");
    }

    public static int value = 123;

    public static void hello() {
        System.out.println("SuperClass hello");
    }
}

class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init!");
    }
//
//    public static void hello() {
//        System.out.println("SubClass hello");
//    }
}

class ConstClass {
    static {
        System.out.println("ConstClass init!");
    }

    public static final String HELLOWORLD = "hello world";
}

/**
 * VMargs: -XX:+TraceClassLoading
 */
public class NotInitialization {
    public static void main(String[] args) {
        // 触发父类的初始化, 未触发子类的初始化
        // 对于静态字段, 只有直接定义字段的类才会被初始化
//        System.out.println(SubClass.value);  // 通过子类引用父类中定义的静态字段
        SubClass.hello();

        // 不触发父类的初始化, 因为生成数组是newarray指令, 会触发'[SuperClass'类(自动生成)的初始化
        // 每种类型的数组是个新的类型, 虽然是自动生成的, 但是它有一些方法, 如length属性和clone()方法
//        SuperClass[] sca = new SuperClass[10];

        // 常量在编译阶段会存入调用类的常量池中, 本质上并没有直接引用到定义常量的类, 不会触发类的初始化.
        // 即HELLOWORLD的常量在编译阶段存储到NotInitialization类的常量池中
//        System.out.println(ConstClass.HELLOWORLD);
    }
}
