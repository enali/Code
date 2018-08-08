package example.java.offer;

/**
 * 题目:
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字，例如，如果输入如下4 X 4矩阵：
 * 1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 则依次打印出数字1,2,3,4,8,12,16,15,14,13,9,5,6,7,11,10.
 *
 * 思路:
 * 仔细分析打印时每一步的前提条件。
 * 第一步总是需要的， 因为打印一圈至少有一步。如果只有一行，那么就不用第二步了。
 * 也就是需要第二步的前提条件是终止行号大于起始行号。
 * 需要第三步打印的前提条件是圈内至少有两行两列，也就是说除了要求终止行号大于起始行号之外，还要求终止列号大于起始列号。
 * 同理，需要打印第四步的前提条件是至少有三行两列，因此要求终止行号比起始行号至少大2 ， 同时终止列号大于起始列号。
 */
public class Offer20 {
    public static void printMatrix(int[][] numbers) {
        if (numbers == null) return;

        int x = 0, y = 0;  // 每一圈打印的起始坐标
        while (x * 2 < numbers.length && y * 2 < numbers[0].length) {
            printMatrixInCircle(numbers, x, y);
            x++;  // 每一圈结束, 起始坐标都会左移一步下移一步
            y++;
        }
    }

    // 打印一圈
    private static void printMatrixInCircle(int[][] numbers, int x, int y) {
        int rows = numbers.length;
        int cols = numbers[0].length;

        // [y, cols-y-1]
        for (int j = y; j <= cols - y - 1; j++) {  // 第一步, 上
            System.out.print(numbers[x][j] + " ");
        }

        // [x+1, rows-x-1]
        for (int i=x+1; i <= rows-x-1; i++) {  // 第二步, 右
            System.out.print(numbers[i][cols-y-1] + " ");
        }

        if (rows-x-1 > x) {  // 因为下只变化列, 所以, 要证明至少有两行
            // [cols-y-2, y]
            for (int j=cols-y-2; j>=y; j--) {  // 第三步, 下
                System.out.print(numbers[rows-x-1][j] + " ");
            }
        }

        // 因为左只变化行, 所以, 要证明至少有两列
        if (cols-y-1 > y) {
            // [rows-x-2, x+1]
            for (int i=rows-x-2; i>=x+1; i--) {  // 第四步, 左
                System.out.print(numbers[i][x] + " ");
            }
        }
    }

    public static void main(String[] args) {
        int[][] numbers = {
                {1,2,3,4},
                {5,6,7,8},
                {9,10,11,12},
                {13,14,15,16}
        };
        printMatrix(numbers);
        System.out.println();

        int[][] numbers2 = {  // 只有上
                {1,2,3,4}
        };
        printMatrix(numbers2);
        System.out.println();

        int[][] numbers3 = {  // 只有上
                {1},
                {2},
                {3},
                {4}
        };
        printMatrix(numbers3);
        System.out.println();


        int[][] numbers4 = {  // 只有上
                {1, 2},
                {3,4}
        };
        printMatrix(numbers4);
        System.out.println();
    }
}
