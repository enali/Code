package example.jvm;

/**
 * 确认Java GC不是采用引用计数
 *
 * VMargs: -Xms30m -Xmx30m -verbose:gc -XX:+PrintGCDetails
 */
public class ReferenceCountingGC {
    public Object instance = null;

    private static final int _1MB = 1024 * 1024;

    // 实例变量, 用于占内存
    private byte[] bigsize = new byte[2 * _1MB];

    public static void testGC() {
        ReferenceCountingGC obj1 = new ReferenceCountingGC();
        ReferenceCountingGC obj2 = new ReferenceCountingGC();
        obj1.instance = obj2;
        obj2.instance = obj1;  // 循环引用
        obj1 = null;
        obj2 = null;

        System.gc();  // 是能进行GC的
    }

    public static void main(String[] args) {
        testGC();
    }
}
