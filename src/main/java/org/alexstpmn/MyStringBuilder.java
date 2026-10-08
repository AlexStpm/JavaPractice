package org.alexstpmn;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class MyStringBuilder {
    private char[] value;
    private int count;
    private final Deque<Snapshot> history = new ArrayDeque<>();

    private record Snapshot(char[] value, int count) {
        Snapshot {
            value = Arrays.copyOf(value, value.length);
        }
    }

    public MyStringBuilder() {
        value = new char[16];
    }

    public MyStringBuilder(int capacity) {
        if (capacity < 0) throw new NegativeArraySizeException();
        value = new char[capacity];

    }

    public MyStringBuilder(String str) {
        int length = str.length();
        int capacity = (length < Integer.MAX_VALUE - 16)
                ? length + 16 : Integer.MAX_VALUE;
        value = new char[capacity];
        append(str);
    }

    public MyStringBuilder append(String str) {
        save();
        int len = str.length();
        ensureCapacity(count + len);
        str.getChars(0, len, value, count);
        count += len;
        return this;
    }

    public MyStringBuilder append(char c) {
        save();
        ensureCapacity(count + 1);
        value[count++] = c;
        return this;
    }

    public MyStringBuilder delete(int start, int end) {
        int count = this.count;
        if (end > count) {
            end = count;
        }
        if (start < 0 || start > end) throw new StringIndexOutOfBoundsException();
        save();
        int len = end - start;
        if (len > 0) {
            System.arraycopy(value, start + len, value, start, count - end);
            this.count = count - len;
        }
        return this;
    }

    public void undo() {
        if (history.isEmpty()) return;
        Snapshot s = history.pop();
        value = Arrays.copyOf(s.value(), s.value().length);
        count = s.count();
    }

    private void ensureCapacity(int minCapacity) {
        if (minCapacity < 0 || minCapacity == Integer.MAX_VALUE) throw new OutOfMemoryError();
        if (minCapacity > value.length) {
            expandCapacity(minCapacity);
        }
    }

    private void expandCapacity(int minCapacity) {
        int newCapacity = Math.max(minCapacity, value.length * 2 + 2);
        value = Arrays.copyOf(value, newCapacity);
    }

    private void save() {
        history.push(new Snapshot(value, count));
    }

    @Override
    public String toString() {
        return new String(value, 0, count);
    }
}
