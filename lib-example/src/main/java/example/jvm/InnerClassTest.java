package example.jvm;

/**
 * Java的内部类, 在实例化时会生成classname$1.class, 作为一个点位符
 */
public class InnerClassTest {
    // 内部类其实是个语法糖, 其会被编译为一个单独的类, 而外部类的构造函数是私有的
    static class TestHolder {
        // 在内部静态类的<cinit>类构造函数中, 调用了外部类的私有构造函数, 按理是不可能的
        // Javac做了个trick, 生成一个$1.class, 为InnerClassTest生成一个公开的以$1.class对象为参数的构造函数
        // 然后, 在此处调用公开的以$1.class为参的构造函数. 因为Java的类名不能有$, 因此, 纯Java代码是不能调用
        // 自动生成的那个公开的构造函数, 有点类似python的私有方法实现, 是生成__ClassName__为前缀的方法
        private static final InnerClassTest INSTANCE = new InnerClassTest();
    }

    private InnerClassTest() {}

    public static InnerClassTest getInstance() {
        return TestHolder.INSTANCE;
    }

    public static void main(String[] args) {

    }
}
