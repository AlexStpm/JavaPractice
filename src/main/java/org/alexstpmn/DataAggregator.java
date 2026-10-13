package org.alexstpmn;

import java.util.concurrent.CompletableFuture;

public class DataAggregator {
    public ProductInfo aggregateProductInfo(String productName) {
        CompletableFuture<Double> price = CompletableFuture
                .supplyAsync(() -> fetchPrice(productName))
                .exceptionally(ex -> 0.0);
        CompletableFuture<String> description = CompletableFuture
                .supplyAsync(() -> fetchDescription(productName))
                .exceptionally(ex -> "Нет данных");
        CompletableFuture<Double> rating = CompletableFuture
                .supplyAsync(() -> fetchRating(productName))
                .exceptionally(ex -> 0.0);
        return price.thenCombine(description, (p, d) -> new ProductInfo(productName, p, d, 0.0))
                .thenCombine(rating, (p, r) -> new ProductInfo(p.name(), p.price(), p.description(), r))
                .join();
    }

    private double fetchPrice(String productName) {
        simulateDelay();
        if (Math.random() < 0.2) throw new RuntimeException();
        return 899.99;
    }

    private String fetchDescription(String productName) {
        simulateDelay();
        if (Math.random() < 0.2) throw new RuntimeException();
        return "Описание ноутбука";
    }

    private double fetchRating(String productName) {
        simulateDelay();
        if (Math.random() < 0.2) throw new RuntimeException();
        return 4.7;
    }

    private void simulateDelay() {
        try {
            Thread.sleep(1000 + (long) (Math.random() * 2000));
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
