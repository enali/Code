package example.java.offer;

/**
 * 题目：请实现一个函数，把字符串中的每个空格替换成"%20"，例如“We are happy.”，则输出“We%20are%20happy.”。
 */
public class Offer04 {
    /**
     * 最大的核心：从后往前替换, 因为"%20"是固定的3个字符， 则当从后往前时， 每个空格对应的位置都是确定的。这样避免字符的移动。
     *
     * @param str 字符数组
     * @param usedLength 当前字符使用的长度
     * @return 替换后的字符使用的长度， 如果无法处理， 返回-1
     */
    public static int replaceBlank(char[] str, int usedLength) {
        if (str == null || str.length < usedLength) return -1;

        int blankLen = 0;  // 统计空白字符的个数
        for (int i=0; i<usedLength; i++) {
            if (str[i] == ' ') blankLen += 1;
        }
        int targetLen = usedLength + blankLen * 2;

        if (str.length < targetLen) return -1;
        if (blankLen == 0) return usedLength;

        // 从后往前替换
        for (int i=usedLength-1, j=targetLen-1; i>=0 && j>=0; i--) {
            if (str[i] == ' ') {
                str[j--] = '0';
                str[j--] = '2';
                str[j--] = '%';
            } else {
                str[j--] = str[i];
            }
        }
        return targetLen;
    }

    public static void main(String[] args) {
        char[] str = new char[50];
        str[0] = ' ';
        str[1] = 'e';
        str[2] = ' ';
        str[3] = ' ';
        str[4] = 'r';
        str[5] = 'e';
        str[6] = ' ';
        str[7] = ' ';
        str[8] = 'a';
        str[9] = ' ';
        str[10] = 'p';
        str[11] = ' ';

        System.out.println(new String(str, 0, 12));
        int targetLen = replaceBlank(str, 12);
        System.out.println(new String(str, 0, targetLen));
    }
}
