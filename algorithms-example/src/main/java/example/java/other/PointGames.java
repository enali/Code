package example.java.other;

/**
 * 24点问题
 *
 * A = {1, 2, 3, 4}, 定义函数f(A)为对集合A中的元素进行所有可能的四则混合运算所得到的值.
 *
 * 首先从集合A中任意取出两个数, 如取出1和2, A = A - {1,2}, 对取出来的数分别进行不同的四则运算, 1+2=3, 1-2=-1, 1/2=0.5, 1*2=2, 将所
 * 得到的结果再分别加入集合A, 可得到B={3,3,4}, C={-1,3,4}, D={0.5,3,4}, E={2,3,4}四个新的集合, 那么f(A) = f(B) + f(C) + f(D) + f(E)
 * 通过以上的计算就达到了分而治之的目的, 问题规模就从4个数降到了3个数
 */
public class PointGames {
    private static double DIFF = 1e-16;

    /**
     * 对number中数字进行 + - * \/ 组合, 以及括号, 要求其结果等于target, 典型问题即24点问题. 本质是枚举
     *
     * 4个数的表达式排列有 4! 种方式, 每两个数间有4种可能的运算符, 4^4. 对每个确定的数排列和运算符, 加括号的话有5种方式.
     * 即共有 4! * 4^4 * 5
     *
     * @param number 参与运算的操作数
     * @param result 保存运算后的表达式
     * @param target 目标值
     * @param n 数的个数
     * @return 是否能组合
     */
    public static boolean compute(double[] number, String[] result, double target, int n) {
        if (n == 1) {  // 组合结束
            return Math.abs(number[0] - target) < DIFF;
        }
        // 挑选两个数开始组合
        for (int i=0; i<n-1; i++) {
            for (int j=i+1; j<n; j++) {
                // 获取要操作的数
                double a = number[i], b = number[j];  // 操作数
                String expra = result[i], exprb = result[j];  // 表达式
                // [i]位置存储计算后的结果和表达式, 将最后的数放置到j的位置, 这样才能递归处理n-1
                number[j] = number[n-1]; result[j] = result[n-1];

                // 处理 a + b
                number[i] = a + b;
                result[i] = String.format("(%s+%s)", expra, exprb);
                if (compute(number, result, target, n-1)) return true;

                // 处理 a - b
                // 因为数据挑选时, i永远在j前面, 而-号的操作数互换并不相等
                number[i] = a - b;
                result[i] = String.format("(%s-%s)", expra, exprb);
                if (compute(number, result, target, n-1)) return true;

                // 处理 b - a
                number[i] = b - a;
                result[i] = String.format("(%s-%s)", exprb, expra);
                if (compute(number, result, target, n-1)) return true;

                // 处理 a * b
                number[i] = a * b;
                result[i] = String.format("(%s*%s)", expra, exprb);
                if (compute(number, result, target, n-1)) return true;

                // 处理 a / b
                if (b != 0) {
                    number[i] = a / b;
                    result[i] = String.format("(%s/%s", expra, exprb);
                    if (compute(number, result, target, n-1)) return true;
                }

                // 处理 b / a
                if (a != 0) {
                    number[i] = b / a;
                    result[i] = String.format("(%s/%s)", exprb, expra);
                    if (compute(number, result, target, n-1)) return true;
                }

                // 这两个数的所有可能的结果已经搜索完毕, 这里是恢复现场, 挑选下两个数
                number[i] = a; number[j] = b;
                result[i] = expra; result[j] = exprb;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int n = 4;
        double target = 24;
        double[] number = {3, 3, 7, 7};
        String[] result = new String[n];
        for (int i=0; i<number.length; i++) result[i] = String.format("%d", (int)number[i]);
        if (compute(number, result, target, n)) System.out.println(result[0]);;
    }
}
