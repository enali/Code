package example.java.other;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 最近最久未使用, 常用于缓存替换算法.
 *
 * 首先肯定是个Map, 用于存储键值对. 其次要有某种方式记录访问的顺序, 以双向链表为例, 如果某节点被访问, 则可以将该拆下来插入
 * 链表头, 则最后的尾结点就是最近最久没被访问的节点.
 *
 * 问题是, 如何高效地找到链表的节点, 而不能顺序遍历, 最佳方式自然是通过HashMap来存储
 */
public class LRU {
    public static void main(String[] args) {
        int cacheSize = 5;
        // loadFactor是指, 当表中超过75%的位置已经填入元素, 则这个表就会用双倍的桶数自动地进行再散列
        // 那就意味着, 在知道确定元素数时, 为避免再散列, 将初始容量设置为cacheSize / 0.75
        Map<String, Integer> lru = new LinkedHashMap<String, Integer>(
                (int) Math.ceil(cacheSize / 0.75f) + 1,
                0.75f,
                true) {  // 按访问序, 而非插入序
            @Override
            protected boolean removeEldestEntry(Map.Entry eldest) {
                return size() > cacheSize;
            }
        };
        lru.put("a", 1);
        lru.put("b", 2);
        lru.put("c", 3);

        lru.get("c"); lru.get("a"); lru.get("b");

        for (Map.Entry<String, Integer> entry : lru.entrySet()) {
            System.out.println(entry.getKey() + ", " + entry.getValue());
        }  // c, a, b
    }
}
