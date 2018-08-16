package cn.enali;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 一个范围类, 可演示实现迭代器
 */
public class Range implements Iterable<Long> {
    private long start;
    private long end;
    private long step;

    public Range(long start, long end, long step) {
        this.start = start;
        this.end = end;
        this.step = step;
    }

    public Range(long start, long end) {
        this(start, end, 1);
    }

    public Range(long end) {
        this(0, end, 1);
    }

    @Override
    public Iterator<Long> iterator() {
        return new RangeIterator();
    }

    private class RangeIterator implements Iterator<Long> {
        private long current;

        public RangeIterator() {
            this.current = start;
        }

        @Override
        public boolean hasNext() {
            return current < end;
        }

        @Override
        public Long next() {
            if (current >= end) throw new NoSuchElementException();
            long now = current;
            current += step;
            return now;
        }
    }

    public static void main(String[] args) {
        Range r1 = new Range(1, 20, 4);
        for (long e : r1) System.out.println(e);
    }
}
