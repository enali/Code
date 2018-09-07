package example.jvm;

// 静态分派的典型应用是方法重载
public class StaticDispatch {
    static abstract class Human {}
    static class Man extends Human {}
    static class Woman extends Human {}

    public void sayHello(Human guy) {
        System.out.println("hello, guy");
    }
    public void sayHello(Man guy) {
        System.out.println("hello, gentleman");
    }
    public void sayHello(Woman guy) {
        System.out.println("hello, lady");
    }

    // Java知道每个变量引用的类型, 也知道每个对象的类型(通过对象头的类指针)
    public static void main(String[] args) {
        // 对于变量man而言, Human是静态类型, Man是实际类型
        // 静态类型的变化仅在使用时发生, 变量本身的静态类型不会被改变, 并且最终的静态类型是在编译期可知的
        // 实际类型变化的结果在运行期才可确定, 编译器在编译程序的时候并不知道一个对象的实际类型是什么
        Human man = new Man();
        Human woman = new Woman();

        StaticDispatch sd = new StaticDispatch();

        sd.sayHello(man);  // 所以, 此处是按Human这个静态类型来处理的, 即invokeVirtual
        sd.sayHello(woman);

        sd.sayHello((Man) man);  // 改变静态类型
        sd.sayHello((Woman) woman);
    }
}
