package example.java.leetcode;

public class Test6 {
    public boolean IsPopOrder(int [] pushA,int [] popA) {
        if (pushA == null || popA == null || pushA.length != popA.length) return false;
        if (pushA.length < 1) return false;  // 空序列, false
        if (pushA.length == 1) return pushA[0] == popA[0];  // 只有1个元素

        int popIdx = 0;  // 遍历popA
        int delIdx = 0;  // pushA中要删除的元素
        int endIdx = pushA.length - 1;
        while (pushA[delIdx] != popA[popIdx]) delIdx++;
        if (delIdx == pushA.length) return false;  // 未在pushA序列中找到popA[0]元素

        while (popIdx < popA.length-1) {
            move(pushA, delIdx, endIdx);
            popIdx++;
            if (delIdx == 0) {
                if (pushA[delIdx] != popA[popIdx]) return false;
            } else if (delIdx == endIdx) {
                delIdx--;
                if (pushA[delIdx] != popA[popIdx]) return false;
            } else {
                if (pushA[delIdx] != popA[popIdx]) delIdx--;
                if (pushA[delIdx] != popA[popIdx]) return false;
            }
            endIdx--;
        }
        return true;
    }

    private void move(int[] ary, int delIdx, int endIdx) {
        if (delIdx == endIdx) return;
        for (int i=delIdx+1; i<=endIdx; i++) ary[i-1] = ary[i];
    }

    public static void main(String[] args) {
        Test6 s = new Test6();
        int[] a = {1, 2, 3, 4, 5};
        int[] b = {4, 5, 3, 2, 1};
        int[] b2 = {4, 3, 5, 1, 2};
        System.out.println(s.IsPopOrder(a, b2));
    }
}
