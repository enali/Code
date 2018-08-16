package example.java.leetcode;

import java.util.Arrays;

public class Test9 {
    // 实现最大堆
    private class MaxPQ {
        private int[] pq;  // 从1索引
        private int count;  // 元素数量, 指向最后一个元素

        // 从1索引, 所以数组至少cap+1, 又因为要固定容量,
        // 则要选出k个元素, 至少同时保留k+1个元素, 其中k是当前最小的, +1是新加入的
        public MaxPQ(int cap) {
            pq = new int[cap + 2];
            count = 0;
        }
        public int size() { return count; }
        public void add(int ele) {
            count++;
            pq[count] = ele;
            swim(count);
        }
        public void del() {
            if (count == 0) return;  // 空
            exch(1, count);
            count--;
            sink(1);
        }
        private void swim(int k) {
            while (k/2 != 0) {
                if (pq[k/2] < pq[k])
                    exch(k/2, k);
                k /= 2;
            }
        }
        private void sink(int k) {
            while (k * 2 <= count) {  // 关键, 要<, 因为还访问了j+1
                int j = k * 2;
                if (j < count && pq[j] < pq[j+1]) j++;
                if (pq[k] < pq[j]) exch(k, j);
                k = j;
            }
        }
        private void exch(int p, int q) {
            if (p == q) return;
            int tmp = pq[p];
            pq[p] = pq[q];
            pq[q] = tmp;
        }
        public int[] toArray() {
            int[] rst = new int[count];
            for (int i=1; i<=count; i++) {
                rst[i-1] = pq[i];
            }
            return rst;
        }
    }

    public int[] GetLeastNumbers_Solution(int [] input, int k) {
        MaxPQ pq = new MaxPQ(k);
        for (int i=0; i<input.length; i++) {
            pq.add(input[i]);
            if (pq.size() == k+1) pq.del();
        }
        return pq.toArray();
    }

    public static void main(String[] args) {
        int[] ary = {4, 5, 1, 6, 2, 7, 3, 8};
        Test9 t9 = new Test9();
        int[] rst = t9.GetLeastNumbers_Solution(ary, 2);
        System.out.println(Arrays.toString(rst));
    }
}