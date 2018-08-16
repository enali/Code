package example.java.leetcode;

/**
 * 题目:
 * 对于一个正的32位整数n, 找到比n大的最小整数, 要求使用n中的数字.
 *
 * 思路:
 * 如, 4321, 从低位到高位, 数字递增, 则4|3|2|1这4个数字已经组成最大的数字了, 所以直接返回-1
 * 如, 2431, 后3位已经最大了, 因此, 需要加上高位(此处指2)进行数字重排列
 *   1) 从后3位中找到比2大的最小的数字, 与2替换
 *   2) 将替换后的后3位, 从小到大排列组成最小的数字
 */
class NextGreaterElement556 {
    public int nextGreaterElement(int n) {
        // 分解数字
        int[] ary = new int[10];
        int tmp = n, len=0;
        while (tmp != 0) {
            ary[len++] = tmp % 10;
            tmp /= 10;
        }
        // 如果数字只有1位, 则返回-1
        if (len == 1) return -1;
        // 定位第1个非递增的位置
        int i=0;
        while (ary[i] <= ary[i+1] && i < len-1) i++;
        if (i == len-1) return -1;  // 如果全部递增, 则已经是最大数字了, 返回-1
        int p = i+1;
        // 从后几位数中, 找到比高位大的最小数字并交换
        i = 0;
        while (ary[i] <= ary[p]) i++;
        tmp = ary[i]; ary[i] = ary[p]; ary[p] = tmp;
        // 用选择排序, 对后几位数字从小到大排列
        for (i=0; i<p-1; i++) {
            int k = i;
            for (int j=i+1; j<p; j++)
                if (ary[j] > ary[k]) k = j;
            if (k != i) {
                tmp = ary[i];
                ary[i] = ary[k];
                ary[k] = tmp;
            }
        }
        // 返回真正的数字, 避免溢出
        int factor=1; long sum = 0;
        for (i=0; i<len; i++) {
            sum += (long)ary[i] * factor;
            factor *= 10;
        }
        if (sum > Integer.MAX_VALUE) return -1;
        return (int)sum;
    }

    public static void main(String[] args) {
        int n = 1999999999;
        NextGreaterElement556 nge = new NextGreaterElement556();
        int res = nge.nextGreaterElement(n);
        System.out.println(res);
    }
}
