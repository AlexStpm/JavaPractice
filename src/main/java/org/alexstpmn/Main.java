package org.alexstpmn;

public class Main {
    public static void main(String[] args) {
        DataAggregator aggregator = new DataAggregator();
        String productName = "Ноутбук";

        System.out.println("Запущен параллельный сбор данных");
        long start = System.currentTimeMillis();

        ProductInfo info = aggregator.aggregateProductInfo(productName);

        long elapsed = System.currentTimeMillis() - start;
        System.out.println(info);
        System.out.println("Сбор завершён за " + elapsed + " мс");
    }
}
