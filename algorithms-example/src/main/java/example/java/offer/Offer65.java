package example.java.offer;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;

/**
 * 题目:
 * 给定一个数组和滑动窗口的大小，找出所有滑动窗口里数值的最大值。例如，如果输入数组{2,3,4,2,6,2,5,1}及滑动窗口的大小3，
 * 那么一共存在6个滑动窗口，他们的最大值分别为{4,4,6,6,6,5}； 针对数组{2,3,4,2,6,2,5,1}的滑动窗口有以下6个：
 * {[2,3,4],2,6,2,5,1}， {2,[3,4,2],6,2,5,1}， {2,3,[4,2,6],2,5,1}， {2,3,4,[2,6,2],5,1}，
 * {2,3,4,2,[6,2,5],1}， {2,3,4,2,6,[2,5,1]}。
 *
 * 思考:
 * 大体的思想是记录每个窗口最大值的索引, 在下一个窗口时, 如果最大值索引还在此窗口, 则只需要比较
 * 此最大值和窗口最右侧元素即可, 如果最大值索引不在此窗口了, 则遍历查找最大值
 */
public class Offer65 {
    public static ArrayList<Integer> maxInWindow(@NotNull int[] num, int size) {
        if (size <= 0) return null;
        ArrayList<Integer> rst = new ArrayList<>();
        if (num.length == 0 || size <= 0 || size > num.length) return rst;

        int len = num.length;
        int maxIdx = -1;  // 当前最大值的索引
        for (int i=0; i<=len-size; i++) {
            if (maxIdx < i) {  // 如果最大值索引在界外, 则遍历求最大值
                maxIdx = i;
                for (int j=i+1; j<i+size; j++)
                    if (num[maxIdx] <= num[j])
                        maxIdx = j;
            } else if (num[maxIdx] <= num[i+size-1]) {  // 在界内, 则只跟刚进入窗口的元素比较
                maxIdx = i + size - 1;
            }
            rst.add(num[maxIdx]);
        }
        return rst;
    }

    public static void main(String[] args) {
        int[] num = {2,3,4,2,6,2,5,1};
        int k = 3;
        System.out.println(maxInWindow(num, k));
    }
}
