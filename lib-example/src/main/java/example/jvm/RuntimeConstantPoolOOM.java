package example.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * 通过无限地String#intern(), 试图导致常量池OOM
 *
 * 但似乎在1.7后, intern()并不会将字串复制到常量池中, 因此, 此处代码也不会导致常量池(方法区)溢出, 而会导致堆溢出
 *
 * VMargs: -Xms20m -Xmx20m
 * -XX:PermSize=10m -XX:MaxPermSize=10m  // 这两个选项已经被移除, jdk8
 */
public class RuntimeConstantPoolOOM {
    public static void testStringIntern() {
        // 在jdk6中, 调用intern(), 若为首次出现, 会复制到常量池, 再返回常量池中的引用, 引自会为false
        // 在jdk7中, 调用intern(), 对于首次出现的字符串, 会在常量池中记录下引用
        String str1 = new StringBuilder("计算机").append("软件").toString();
        System.out.println(str1.intern() == str1);  // true

        // 在jdk7中, 因为java字串本身就在常量池中, 并非首次出现, 因此intern()返回的是常量池中的引用
        String str2 = new StringBuilder("ja").append("va").toString();
        System.out.println(str2.intern() == str2);  // false
    }

    public static void main(String[] args) {
        List<String> list = new ArrayList<>();
        int i = 0;
        while (true) {
            list.add(String.valueOf(i++).intern());  // 无限插入
        }
    }
}
