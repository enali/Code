package example.java.other;

public class Heap {
    private int[] data;
    private int cnt;

    public Heap(int initialCapacity) {
        this.data = new int[initialCapacity+1];
        this.cnt = 0;
    }

    public void add(int ele) {
        cnt++;
        if (cnt == data.length)
            throw new RuntimeException("the head is full");
        data[cnt] = ele;
        swim(cnt);
    }

    public int remove() {
        if (cnt == 0)
            throw new RuntimeException("the heap is empty");
        int rst = data[1];
        exch(1, cnt);
        cnt--;
        sink(1);
        return rst;
    }

    private void sink(int k) {
        while (k * 2 <= cnt) {
            int j = k * 2;
            if (j < cnt && data[j] > data[j+1]) j += 1;
            if (data[k] > data[j]) exch(k, j);
            k = j;
        }
    }

    private void swim(int k) {
        while (k / 2 != 0) {
            if (data[k] < data[k/2])
                exch(k, k/2);
            k /= 2;
        }
    }

    private void exch(int i, int j) {
        if (i == j) return;
        int tmp = data[i];
        data[i] = data[j];
        data[j] = tmp;
    }

    public static void main(String[] args) {
        Heap h = new Heap(3);
        h.add(3); h.add(9); h.add(7); h.add(1);
        System.out.println(h.remove());
        System.out.println(h.remove());
        System.out.println(h.remove());
        System.out.println(h.remove());
    }
}
