package example.java.offer;

/**
 * 题目:
 * 输入一个n, 求从1到n这n个整数的十进制表示中1出现的次数
 *
 * 思考:
 * 以3413为例, 其可分解为1-413, 414-1413, 1414-2413, 2414-3413, 看后面3个
 * 1, 414-1413和1414-2413, 2414-3413, 都有1000个数, 其中1的个数等于0-999的1的个数, 此处, 从000-999, 每个位固定1, 则其余位可从0-9, 共有3 * 100个
 * 2, 最高位为1有点特殊, 1000-1999, 最高位的1共有1000个.
 * 3, 后3个处理完, 则递归处理第1个, 即413
 */
public class Offer32 {
    public static int numberOf1Between1AndN(int n) {
        if (n <= 0) return 0;

        int[] num = new int[32];
        int idx = 0;
        while (n != 0) {
            num[idx++] = n % 10;
            n /= 10;
        }
        return numberOf1(num, idx-1);
    }

    private static int numberOf1(int[] num, int idx) {
        if (idx == 0) {  // 对于个位数
            return num[idx] >= 1 ? 1 : 0;
        }

        int sum = 0;
        if (num[idx] > 1) sum += powerBase10(idx);  // 最高位比1大, 则有1000-1999, 计算最高位中1的个数
        else if (num[idx] == 1) sum += getNum(num, idx-1) + 1;  // 最高位为1, 则只有1413中, 从1000-1413中的414个数

        sum += num[idx] * idx * powerBase10(idx-1);  // 计算除最高位外, 其余位为1的个数

        sum += numberOf1(num, idx-1);  // 递归
        return sum;
    }

    // 获取第n位(0索引)的基数, 比如0对应1, 1对应10, 2对应100, 3对应1000
    private static int powerBase10(int n) {
        int sum = 1;
        while (n-- > 0)
            sum *= 10;
        return sum;
    }

    // 左侧低位, 右侧高位, 获取0-idx的num中, 对应的数字
    private static int getNum(int[] num, int idx) {
        int sum = 0;
        while (idx >= 0)
            sum = sum * 10 + num[idx--];
        return sum;
    }

    public static void main(String[] args) {
        int n = 1;
        System.out.println(numberOf1Between1AndN(n));  // 1

        int n1 = 10;
        System.out.println(numberOf1Between1AndN(n1));  // 2

        int n2 = 11;
        System.out.println(numberOf1Between1AndN(n2));  // 4

        int n3 = 101;
        System.out.println(numberOf1Between1AndN(n3));  // 23
    }
}
