package example.jvm;

public class BoxAndUnboxTest {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 2;
        Integer c = 3;
        Integer d = 3;
        Integer e = 321;
        Integer f = 321;  // 此及之前, 全部是调用 Integer.valueOf, 注意缓存
        Long g = 3L;
        System.out.println(c == d);  // true
        System.out.println(e == f);  // false
        // a + b, 会自动拆箱, 然后==会将c自动拆箱, 直接进行值比较
        System.out.println(c == (a + b));  // true, 遇到运算符+, 会自动拆箱
        // a + b, 有运算符, 会自动拆箱, 又因为equals, 又会将运算结果自动装箱
        System.out.println(c.equals(a + b));  // true
        // a/b/g都会拆箱, 最后又进行类型转换
        System.out.println(g == a + b);  // true
        System.out.println(g.equals(a + b));  // false, equals不处理非继承的类型转换
    }
}
