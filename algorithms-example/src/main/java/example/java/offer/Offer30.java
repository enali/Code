package example.java.offer;

import java.util.ArrayList;
import java.util.PriorityQueue;

/**
 * 题目:
 * 从数组中获取最小的K个数字
 */
public class Offer30 {
    // 用最大堆来实现
    public static ArrayList<Integer> getLeastKNumbers(int[] ary, int k) {
        if (k <= 0) return new ArrayList<>();

        // 大堆
        PriorityQueue<Integer> pq = new PriorityQueue<>((o1, o2) -> o2 - o1);
        for (int item : ary) {
            pq.add(item);
            // 如果堆大小超过限制, 则去除最大的元素
            if (pq.size() > k) {
                pq.poll();
            }
        }
        ArrayList<Integer> rst = new ArrayList<>(pq);
        return rst;
    }

    // 快速选择
    // 用快速排序找到分隔点的位置, 分隔点刚好表示前j个元素都比分隔点小
    public static ArrayList<Integer> getLeastKNumbers2(int[] ary, int k) {
        ArrayList<Integer> rst = new ArrayList<>();
        int l = 0, h = ary.length-1;
        while (l < h) {
            int j = partition(ary, l, h);
            if (j == k) {  // 刚好是k个元素, 则可以返回
                for (int i=0; i<j; i++) {
                    rst.add(ary[i]);
                }
                return rst;
            } else if (j > k) h = j-1;
            else l = j + 1;
        }
        return rst;
    }

    private static int partition(int[] ary, int l, int h) {
        int part = ary[l];
        int left = l+1, right = h;
        while (true) {
            while (left < h && ary[left] < part) left++;
            while (right > l && ary[right] > part) right--;
            if (left >= right) break;
            swap(ary, left, right);
        }
        swap(ary, l, right);
        return right;
    }

    private static void swap(int[] ary, int left, int right) {
        int tmp = ary[left];
        ary[left] = ary[right];
        ary[right] = tmp;
    }

    public static void main(String[] args) {
        int[] ary = {1, 2, 3, 4, 5, 6};
        System.out.println(getLeastKNumbers2(ary, 3));
    }
}
