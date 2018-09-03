package example.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 验证堆内存的OOM
 *
 * 1, 生成大量对象, 2, 保持对这些对象的引用
 *
 * VMargs: -Xms20m -Xmx20m -XX:+HeapDumpOnOutOfMemoryError
 */
public class HeapOOM {
    static class OOMObject {}
    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true) {
            list.add(new OOMObject());
        }
    }
}
