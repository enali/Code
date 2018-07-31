package example.java.offer;

/**
 * 题目描述:
 * 把一个数组最开始的若干个元素搬到数组的末尾，我们称之为数组的旋转。 输入一个非减排序的数组的一个旋转，输出旋转数组的最小元素。
 * 例如数组{3,4,5,1,2}为{1,2,3,4,5}的一个旋转，该数组的最小值为1。 NOTE：给出的所有元素都大于0，若数组大小为0，请返回0。
 *
 * 思路：
 * 两个有序序列的拼接, 虽然不能利用二分查找, 但仍然可以通过mid元素的性质来加快查询
 * 关键是理清: [mid], [lo], [hi]三者的关系, 穷举
 */
public class Offer08 {
    public static int minNumberInRotateArray(int [] array) {
        if (array == null) return -1;
        if (array.length == 0) return 0;

        int lo = 0, hi = array.length-1;
        while (array[lo] >= array[hi]) {  // 确保[lo]在前半部分, hi在后半部分
            if (hi - lo == 1) return array[hi];  // 差1的情况下, 先后者

            int mid = (lo + hi) / 2;
            if (array[mid] == array[hi] && array[mid] == array[lo]) {  // 无法判断, 遍历
                return minNum(array, lo, hi);
            } else if (array[mid] < array[lo]) hi = mid;  // 在前半部分
            else if (array[mid] > array[hi]) lo = mid;  // 在后半部分
        }
        return array[lo];  // 如果一开始就有序
    }

    // 从数组中找到最小的数
    private static int minNum(int[] ary, int lo, int hi) {
        int idx = lo;
        while (idx < hi && ary[idx+1] >= ary[idx]) idx++;
        if (idx == hi) return ary[lo];
        else return ary[idx + 1];
    }

    public static void main(String[] args) {
        // 典型输入，单调升序的数组的一个旋转
        int[] array1 = {3, 4, 5, 1, 2};
        System.out.println(minNumberInRotateArray(array1));

        // 有重复数字，并且重复的数字刚好的最小的数字
        int[] array2 = {3, 4, 5, 1, 1, 2};
        System.out.println(minNumberInRotateArray(array2));

        // 有重复数字，但重复的数字不是第一个数字和最后一个数字
        int[] array3 = {3, 4, 5, 1, 2, 2};
        System.out.println(minNumberInRotateArray(array3));

        // 有重复的数字，并且重复的数字刚好是第一个数字和最后一个数字
        int[] array4 = {1, 0, 1, 1, 1};
        System.out.println(minNumberInRotateArray(array4));
//
        // 单调升序数组，旋转0个元素，也就是单调升序数组本身
        int[] array5 = {1, 2, 3, 4, 5};
        System.out.println(minNumberInRotateArray(array5));

        // 数组中只有一个数字
        int[] array6 = {2};
        System.out.println(minNumberInRotateArray(array6));

        // 数组中数字都相同
        int[] array7 = {1, 1, 1, 1, 1, 1, 1};
        System.out.println(minNumberInRotateArray(array7));
        System.out.println(minNumberInRotateArray(array6));

        // 输入NULL
        System.out.println(minNumberInRotateArray(null));


    }
}