package example.java;

/**
 * 汉诺塔的递归算法
 */
public class Hanoi {
    private void move(char src, char dst) {
        System.out.printf("%c --> %c\n", src, dst);
    }

    public void hanoi(int n, char a, char b, char c) {
        if (n==1) move(a, c);
        else {
            hanoi(n-1, a, c, b);
            move(a, c);
            hanoi(n-1, b, a, c);
        }
    }

    public static void main(String[] args) {
        Hanoi h = new Hanoi();
        h.hanoi(3, 'A', 'B', 'C');
    }
}
