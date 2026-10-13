package org.alexstpmn;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;

public class BlockingQueue<E> {
    private final Queue<E> elements = new LinkedList<>();
    private final int capacity;

    public BlockingQueue(int capacity) {
        if (capacity <= 0)
            throw new IllegalArgumentException("capacity must be > 0");
        this.capacity = capacity;
    }

    public synchronized void enqueue(E e) throws InterruptedException {
        Objects.requireNonNull(e);
        while (elements.size() >= capacity) {
            System.out.println("BlockingQueue is full! Waiting...");
            wait();
        }
        elements.add(e);
        notifyAll();
    }

    public synchronized E dequeue() throws InterruptedException {
        while (elements.isEmpty()) {
            System.out.println("BlockingQueue is empty! Waiting...");
            wait();
        }
        E e = elements.poll();
        notifyAll();
        return e;
    }

    public synchronized int size() {
        return elements.size();
    }
}