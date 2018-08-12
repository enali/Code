package example.java.offer;

/**
 * 题目:
 * 数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。例如输入一个长度为9的数组{1,2,3,2,2,2,5,4,2}。
 * 由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。如果不存在则输出0。
 *
 * 思路:
 * 以cnt计数, 若元素相同则cnt+1, 不然的话, cnt-1
 * 当cnt==0时, 表示前i个元素, 没有超过i/2个的元素
 * 如果数组中有超过一半的元素, 则仍然在剩余的n-i个元素中,
 *
 * 如果数组中有个元素的个数超过一半, 那么无论把这个数组分成多少份, 至少有一份, 这个元素是超过一半的. 反证法: 如果
 * 在所有份中, 都没有元素超过这份元素总数的一半, 那么所有份数加起来, 就肯定没有元素超过一半.
 */
public class Offer29 {
    public static int moreThanHalfNumber(int[] ary) {
        if (ary == null || ary.length <= 1) return -1;

        int cnt = 1;
        int majority = ary[0];

        for (int i=1; i<ary.length; i++) {
            if (ary[i] == majority) cnt++;
            else cnt--;

            if (cnt == 0) {
                cnt = 1;
                majority = ary[i];
            }
        }
        // cnt其实并不是主元素的计数
        cnt = 0;
        for (int i=0; i<ary.length; i++) {
            if (ary[i] == majority)
                cnt++;
        }
        return cnt > ary.length/2 ? majority : -1;
    }

    public static void main(String[] args) {
        int[] ary = {1,2,3,4,2,2};
        System.out.println(moreThanHalfNumber(ary));
        int[] ary2 = {1,2};
        System.out.println(moreThanHalfNumber(ary2));
        int[] ary3 = {2,2};
        System.out.println(moreThanHalfNumber(ary3));
    }
}
