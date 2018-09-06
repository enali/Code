package example.jvm;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodType;

import static java.lang.invoke.MethodHandles.lookup;

/**
 * 模拟动态语言的动态方法调用
 */
public class MethodHandleTest {
    static class ClassA {
        public void println(String s) {
            System.out.println(s);
        }
    }

    public static void main(String[] args) throws Throwable {
        Object obj = System.currentTimeMillis() % 2 == 0 ? System.out : new ClassA();
        // 从obj对象中获取println方法并调用, 此时, 只要对象有println()方法即可, 而不关心obj是否为PrintStream或其子类
        getPrintlnMH(obj).invokeExact("icyfenix");
    }

    // 模拟invokevirtual指令的执行过程
    private static MethodHandle getPrintlnMH(Object receiver) throws NoSuchMethodException, IllegalAccessException {
        // 方法类型, arg1表示返回类型, arg2...表示参数类型
        MethodType mt = MethodType.methodType(void.class, String.class);
        // lookup()在指定的类中查找符合给定的方法名称, 方法类型, 并且符合调用权限的方法句柄
        // bindTo()绑定到接收对象
        return lookup().findVirtual(receiver.getClass(), "println", mt).bindTo(receiver);
    }
}
