package org.alexstpmn;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    public static void main(String[] args) {
        BlockingQueue<Integer> blockingQueue = new BlockingQueue<>(5);
        ExecutorService pool = Executors.newFixedThreadPool(2);

        Runnable producer = () -> {
            try {
                for (int i = 0; i <= 10; i++) {
                    System.out.println("Producer is trying to add number: " + i);
                    blockingQueue.enqueue(i);
                    System.out.println("Added number: " + i);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };
        Runnable consumer = () -> {
            try {
                for (int i = 0; i <= 10; i++) {
                    System.out.println("Consumer is trying to read");
                    System.out.println("Read number: " + blockingQueue.dequeue());
                    Thread.sleep(1000);
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        };
        pool.execute(consumer);
        pool.execute(producer);
        pool.shutdown();
    }
}
