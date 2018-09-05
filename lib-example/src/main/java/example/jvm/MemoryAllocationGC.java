package example.jvm;

public class MemoryAllocationGC {
    private static final int _1MB = 1024 * 1024;

    /**
     * VMargs: -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+UseSerialGC -XX:+PrintGCDetails -XX:SurvivorRatio=8
     *
     * 相当于8mb的eden, 1mb的survivor, 10mb的tenure
     */
    public static void testAllocation() {
        byte[] allo1, allo2, allo3, allo4;
        allo1 = new byte[2 * _1MB];
        allo2 = new byte[2 * _1MB];
        allo3 = new byte[2 * _1MB];
        allo4 = new byte[4 * _1MB];  // 不够了
    }

    /**
     * VMargs: -verbose:gc -Xms20m -Xmx20m -Xmn10m -XX:+UseSerialGC -XX:+PrintGCDetails -XX:SurvivorRatio=8
     * -XX:PretenureSizeThreshold=3145728
     *
     * 现象: 直接在老年代分配对象, 不会触发GC
     */
    public static void testPretenureSizeThreshold() {
        byte[] allo;
        allo = new byte[4 * _1MB];
    }

    public static void main(String[] args) {
//        testAllocation();
        testPretenureSizeThreshold();
    }
}
