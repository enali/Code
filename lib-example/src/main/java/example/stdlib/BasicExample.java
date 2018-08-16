package example.stdlib;

import java.util.*;

public class BasicExample {
    public static void arraysExample() {
        int[] ary = {1, 2, 3, 4, 5};

        int[] to = new int[10];  // 在局部作用域, 新建数组会默认初始化为0
        System.out.println(Arrays.toString(to));

        int[] b = Arrays.copyOf(ary, 20);  // 扩充
        System.out.println(Arrays.toString(b));

        int[] c = Arrays.copyOf(ary, 3);  // 截断
        System.out.println(Arrays.toString(c));
    }

    public static void mapExample() {
        Map<String, Integer> m = new HashMap<>();
        m.put("a", 1);
        m.put("b", 2);
        m.put("c", 3);  // 存入键值

        System.out.println(m.get("a"));
        System.out.println(m.get("d"));  // 键不存在则返回null
        System.out.println(m.getOrDefault("d", 30));  // 读取键, 不存在则返回默认值

        m.remove("a");  // 移除

        m.putIfAbsent("b", 20);  // 如果键不存在则存入
        System.out.println(m.get("b"));  // 2

        // 遍历
        for (Map.Entry<String, Integer> entry : m.entrySet()) {
            System.out.print(String.format("(%s, %s) ", entry.getKey(), entry.getValue()));
        }
        System.out.println();

        // 遍历key
        for (String key : m.keySet()) {
            System.out.print(String.format("(%s, %s) ", key, m.get(key)));
        }
        System.out.println();

        // 遍历值
        for (Integer val : m.values()) {
            System.out.print(val + " ");
        }
        System.out.println();
    }

    public static void listExample() {
        List<Integer> l = new ArrayList<>();
        l.add(3);
        l.add(9);
        l.add(7);
        l.addAll(Arrays.asList(0, 4, 8));

        // list可以直接输出, 但数组不可以, 因为虽然数组是对象, 但其实并没有显式类型, 没办法定义toString
        System.out.println(l);

        System.out.println(l.get(0));  // 取索引0处的元素

        l.remove(0);  // 移除索引0处的元素
        System.out.println(l.get(0));

        System.out.println(l.get(1));
        l.remove(Integer.valueOf(7));  // 移除对象7, 当类型为装箱类型时, 要小心, 不能直接remove(7)
        System.out.println(l.get(1));

        l.removeIf(e -> e > 4);  // 删除所有大于4的元素, 用lambda表达式替换Predicate接口
        System.out.println(l);
    }

    public static void main(String[] args) {
        System.out.println("==== Arrays Example ====");
        arraysExample();

        System.out.println("==== Map Example ====");
        mapExample();

        System.out.println("==== List Example ====");
        listExample();
    }
}
