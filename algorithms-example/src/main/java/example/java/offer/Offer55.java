package example.java.offer;

import java.util.Arrays;

/**
 * 题目:
 * 请实现一个函数用来找出字符流中第一个只出现一次的字符。例如，当从字符流中只读出前两个字符"go"时，第一个只出现一次的字符是"g"。
 * 当从该字符流中读出前六个字符“google"时，第一个只出现一次的字符是"l"。
 *
 * 思路:
 * 参考 {@link Offer35}
 */
public class Offer55 {
    int idx = 0;
    int[] occurrence = new int[256];  // 每个字符的出现情况, -1表示未出现, -2表示出现多次, 0及0以上表示出现的索引

    public Offer55() {
        Arrays.fill(occurrence, -1);
    }

    public void insert(char ch)
    {
        if (occurrence[ch] == -1) occurrence[ch] = idx;
        else occurrence[ch] = -2;
        idx++;
    }

    public char firstAppearingOnce() {
        char c = '#';
        int minIdx = Integer.MAX_VALUE;
        for (int i=0; i<occurrence.length; i++) {
            if (occurrence[i] >= 0 && occurrence[i] < minIdx) {
                c = (char) i;
                minIdx = occurrence[i];
            }
        }
        return c;
    }

    public static void main(String[] args) {
        Offer55 offer = new Offer55();
        offer.insert('g');
        offer.insert('o');
        System.out.println(offer.firstAppearingOnce());
        offer.insert('o');
        offer.insert('g');
        offer.insert('l');
        offer.insert('e');
        System.out.println(offer.firstAppearingOnce());
    }
}
