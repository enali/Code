package example.java.other;

/**
 * 题目:
 * 在8×8格的国际象棋上摆放八个皇后，使其不能互相攻击，即任意两个皇后都不能处于同一行、同一列或同一斜线
 *
 * 思路:
 * 按行来规定皇后，第一行放第一个皇后，第二行放第二个，然后通过遍历所有列，来判断下一个皇后能否放在该列。直到所有皇后都放完，或者放哪都不行。
 *
 * 详细一点说，第一个皇后先放第一行第一列，然后第二个皇后放在第二行第一列、然后判断是否OK，然后第二列、第三列、依次把所有列都放完，
 * 找到一个合适，继续第三个皇后，还是第一列、第二列……直到第8个皇后也能放在一个不冲突的位置，算是找到了一个正确解。
 * 然后回头继续第一个皇后放第二列，后面继续循环……
 */
public class WolfQueen {
    int max = 8;  // 皇后个数

    int[] ary = new int[max];  // 保存结果, 第一个皇后存在ary[0]列, 第二个皇后在ary[1]列

    // n 表示第n个皇后
    private void check(int n) {
        // 摆完最后一行, 打印
        if (n == max) {
            print();
            return;
        }

        // 从第一列开始放, 无冲突则下一行
        for (int i=0; i<max; i++) {
            ary[n] = i;
            if (judge(n)) {
                check(n + 1);
            }
        }
    }

    private boolean judge(int n) {
        for (int i=0; i<n; i++) {
            // 不同列, 不同斜线
            if (ary[i] == ary[n] || Math.abs(n-i) == Math.abs(ary[n]-ary[i])) {
                return false;
            }
        }
        return true;
    }

    private void print() {
        for (int i = 0; i < ary.length; i++) {
            System.out.print(ary[i] + 1 + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        new WolfQueen().check(0);
    }
}
