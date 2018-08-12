package cn.enali;

public class CommonUtils {
    public static <T> void swap(T[] ary, int i, int j) {
        int len = ary.length;
        if (i < 0 || i >= len || j < 0 || j>= len)
            throw new IllegalArgumentException("i,j must be in range [0, ary.length)");
        T tmp = ary[i];
        ary[i] = ary[j];
        ary[j] = tmp;
    }

    public static long toByte(String mem) {
        int len = mem.length();
        char c = mem.charAt(len-1);
        long rst;
        if (c >= '0' && c <= '9') {
            rst = Long.parseLong(mem);
        } else {
            long tmp = Long.parseLong(mem.substring(0, len-2));
            switch (c) {
                case 'k':
                case 'K':
                    rst = tmp * 1024L;
                    break;
                case 'm':
                case 'M':
                    rst = tmp * 1024 * 1024L;
                    break;
                case 'g':
                case 'G':
                    rst = tmp * 1024 * 1024 * 1024L;
                    break;
                default:
                    throw new IllegalArgumentException("mem must be number, or numbers ends with k/m/g");
            }
        }
        return rst;
    }

    public static void main(String[] args) {
        String mem = "128g";
        System.out.println(toByte(mem));
    }
}
