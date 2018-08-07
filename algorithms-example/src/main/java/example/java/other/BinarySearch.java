package example.java.other;

public class BinarySearch {
    // [a, b]
    public int binarySearch1(int[] ary, int k) {
        int a = 0;
        int b = ary.length - 1;
        while (a <= b) {
//            int mid = (a + b) / 2;  // TODO: a+b may be overflow
            int mid = a + (b-a) / 2;
            if (k > ary[mid]) a = mid + 1;
            else if (k < ary[mid]) b = mid - 1;
            else return mid;
        }
        return -1;
    }

    // [a, b)
    public int binarySearch2(int[] ary, int k) {
        int a = 0;
        int b = ary.length;  // 注意: 是[a, b)是半开半闭区间, 总是不包含b
        while (a < b) {
            int m = (a + b) / 2;  // 当a==b时, m=a=b, 则ary[m]会越界
            if (k > ary[m]) { // k在右半边, 因为[m]已经判断, 所以, a=m+1
                a = m+1;
            } else if (k < ary[m]) {  // k 在左半边, 虽然[m]已经判断, 但是b总是不被包含的, 所以, 下一次的区间, b=m
                b = m;
            } else {
                return m;
            }
        }
        return -1;
    }

    // 有重复元素的最左侧位置
    public int binarySearchLeft(int[] ary, int k) {
        int l = 0;
        int h = ary.length - 1;
        while (l < h) {  // IMP: 若包括==,则可能循环无法退出
            int mid = l + (h - l) / 2;
            if (ary[mid] >= k) h = mid;  // IMP: [l,mid]是解, 包括mid
            else l = mid + 1;
        }
        return l;
    }

    public static void main(String[] args) {
        int[] ary = {1,2,10,15,100};

        BinarySearch bs = new BinarySearch();
        System.out.println(bs.binarySearch1(ary, 15));
        System.out.println(bs.binarySearch1(ary, -2));
        System.out.println(bs.binarySearch1(ary, 101));
        System.out.println(bs.binarySearch1(ary, 13));

        System.out.println("====");

        System.out.println(bs.binarySearch1(new int[]{}, 13));
        System.out.println(bs.binarySearch1(new int[]{12}, 13));
        System.out.println(bs.binarySearch1(new int[]{13}, 13));

        System.out.println("====");

        System.out.println(bs.binarySearch1(new int[]{12, 13}, 12));
        System.out.println(bs.binarySearch1(new int[]{12, 13}, 13));

    }
}
