package example.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class IntervalMerge {
    // 区间合并
    // 思路:
    // 1, 先根据左点进行排序
    // 2, 记录当前最大的右点, 如果maxRight比左点小, 则不能合并, 取区间
    // 3, 如果maxRight比左点大, 比右点小, 则能合并, 更新maxRight
    // 4, 如果maxRight比右点大, 什么也不做
    // 据说, 也可以用线段树做
    public static List<Integer[]> merge(List<Integer[]> intervals) {
        Collections.sort(intervals, (a, b) -> (a[0] - b[0]));
        List<Integer[]> rst = new ArrayList<>();

        int maxRight = Integer.MIN_VALUE;
        Integer[] cur = null;
        for (Integer[] item : intervals) {
            if (maxRight < item[0]) {
                rst.add(cur);
                cur = item;
                maxRight = item[1];
            } else if (maxRight < item[1]) {
                cur[1] = item[1];
                maxRight = item[1];
            }
        }
        rst.add(cur);
        return rst.subList(1, rst.size());
    }

    public static void main(String[] args) {
        List<Integer[]> intervals = new ArrayList<>();
        intervals.add(new Integer[]{1, 3});
        intervals.add(new Integer[]{4, 7});
        intervals.add(new Integer[]{2, 6});
        intervals.add(new Integer[]{9, 10});
        intervals.add(new Integer[]{8, 9});

        List<Integer[]> rst = IntervalMerge.merge(intervals);
        for (Integer[] item : rst) {
            System.out.println(Arrays.toString(item));
        }
    }
}
