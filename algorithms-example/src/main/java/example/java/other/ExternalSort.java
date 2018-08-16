package example.java.other;

import cn.enali.Range;

import java.util.*;

/**
 * 外部排序, 主要演示的是多路归并
 *
 * 多路归并并不复杂, 通过使用最小堆不断地提取当前最小元素即可. 难点在于, 有多个迭代器, 当移除的最小元素属于
 * 某个迭代器时, 要从此迭代器中再补充一个元素. 即要想办法知道, 移除的元素是哪个迭代器.
 */
public class ExternalSort {
    /**
     * 封装迭代器和当前元素, 这样, 在移除时, 通过value获取元素, 通过source获取此元素位于的迭代器, 方便继续添加元素
     * @param <T>
     */
    private class ResultEntry<T extends Comparable<T>> implements Comparable<ResultEntry<T>>{
        private final T value;
        private final Iterator<T> source;

        public ResultEntry(T value, Iterator<T> source) {
            this.value = value;
            this.source = source;
        }

        @Override
        public int compareTo(ResultEntry<T> o) {
            return value.compareTo(o.value);
        }
    }

    /**
     * 将迭代器合并为迭代器
     * @param <T>
     */
    private class MergeResultIterator<T extends Comparable<T>> implements Iterator<T> {
        private final PriorityQueue<ResultEntry<T>> queue;  // 通过最小堆

        public MergeResultIterator(List<Iterable<T>> data) {
            queue = new PriorityQueue<>();
            for (Iterable<T> d : data) {
                Iterator<T> iter = d.iterator();
                queue.add(new ResultEntry<>(iter.next(), iter));
            }
        }

        @Override
        public boolean hasNext() {
            return !queue.isEmpty();
        }

        @Override
        public T next() {
            if (queue.isEmpty()) throw new NoSuchElementException();
            ResultEntry<T> rst = queue.remove();
            if (rst.source.hasNext())  // 移除元素再补充元素
                queue.add(new ResultEntry<>(rst.source.next(), rst.source));
            return rst.value;
        }
    }

    public <T extends Comparable<T>> Iterable<T> merge(List<Iterable<T>> data) {
        return () -> new MergeResultIterator<>(data);
    }

    public static void main(String[] args) {
        Iterable<Long> data1 = new Range(1L, 1000000000000L, 1L);
        Iterable<Long> data2 = new Range(1L, 1000000000000L, 2L);
        Iterable<Long> data3 = new Range(1L, 1000000000000L, 3L);
        Iterable<Long> data4 = new Range(1L, 1000000000000L, 5L);
        Iterable<Long> data5 = new Range(1L, 1000000000000L, 7L);

        Iterable<Long> smallData1 = new Range(1L, 10L, 1L);
        // 1, 2, 3, 4, 5, ... 9
        Iterable<Long> smallData2 = new Range(1L, 10L, 2L);
        // 1, 3, 5, 7, 9

        ExternalSort sort = new ExternalSort();

        System.out.println("Testing small data set.");
        Iterable<Long> resultSmall = sort.merge(
                Arrays.asList(smallData1, smallData2));
        printInitialResults(resultSmall, 100);

        System.out.println("Testing normal data set.");
        Iterable<Long> result = sort.merge(
                Arrays.asList(data1, data2, data3, data4, data5));
        printInitialResults(result, 100);

        System.out.println("Testing normal data set again.");
        Iterable<Long> anotherResult =
                sort.merge(Arrays.asList(
                        sort.merge(Arrays.asList(data1, data2)),
                        sort.merge(Arrays.asList(data3, data4)),
                        data5));
        printInitialResults(anotherResult, 100);
    }

    private static void printInitialResults(
            Iterable<Long> resultSmall, int resultsToPrint) {
        int count = 0;
        for (Long value : resultSmall) {
            System.out.print(value);
            System.out.print(" ");
            count++;
            if (count >= resultsToPrint) {
                break;
            }
        }
        System.out.println();
    }
}
