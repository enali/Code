package example.java.offer;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 题目:
 * 如何得到一个数据流中的中位数？如果从数据流中读出奇数个数值，那么中位数就是所有值排序之后位于中间的数值。
 * 如果数据流中读出偶数个数值，那么中位数就是所有数值排序之后中间两个数的平均值。
 *
 * 思路:
 * 用两个堆, pqMax是最大堆, pqMin是最小堆, pqMax中的元素都小于pqMin
 * 所有奇数插入一侧, 所有偶数插入另一侧, 重点是如何保证pqMax的元素全部小于pqMin
 */
public class Offer64 {
    private PriorityQueue<Integer> pqMax = new PriorityQueue<>(Comparator.reverseOrder());
    private PriorityQueue<Integer> pqMin = new PriorityQueue<>();

    public void insert(Integer ele) {
        if ((pqMax.size() + pqMin.size()) % 2 == 1) {  // 当前总数是奇数, 则此元素为偶数
            pqMin.add(ele);
            pqMax.add(pqMin.remove());  // 偶数插入最大堆(左侧)
        } else {
            pqMax.add(ele);
            pqMin.add(pqMax.remove());
        }
    }

    public double getMedian() {
        if ((pqMax.size() + pqMin.size()) % 2 == 0) {
            return (pqMax.peek() + pqMin.peek()) / 2.0;
        } else {
            return pqMin.peek();
        }
    }

    public static void main(String[] args) {
        Offer64 offer = new Offer64();

        for (int i=1; i<=101; i++) offer.insert(i);

        System.out.println(offer.getMedian());
    }
}
