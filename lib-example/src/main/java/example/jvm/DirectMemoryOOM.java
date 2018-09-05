package example.jvm;

import sun.misc.Unsafe;

import java.lang.reflect.Field;

/**
 * 通过Unsafe的allocateMemory来制造直接内存的溢出OOM
 *
 * VMargs: -Xmx20m -XX:MaxDirectMemorySize=10m, 指定最大直接内存
 */
public class DirectMemoryOOM {
    private static final int _1MB = 1024 * 1024;

    // Unsafe类的getUnsafe方法限制了只有bootstrap类加载器加载的类才能调用, 即只有rt.jar中的类可以用, 而当前类DirectMemoryOOM是
    // App classLoader加载的
    public static void main(String[] args) throws Exception {
        // 获取类对象声明的所有域, 包括public/protected/default/private, 但不包括继承的域
        Field unsafeField = Unsafe.class.getDeclaredFields()[0];  // Unsafe的第1个声明域为Unsafe, 但它是私有的
        // 跳过对此域的访问性检查
        unsafeField.setAccessible(true);
        // 获取对象的此域的值, 如果为静态域, 则忽略对象(即为null), 若为实例域, 则必须指定对象, 否则抛出NPE
        Unsafe unsafe = (Unsafe) unsafeField.get(null);
        while (true) {
            unsafe.allocateMemory(_1MB);  // 真正的内存申请
        }
    }
}
