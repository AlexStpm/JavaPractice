package org.alexstpmn;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static <T> Map<T, Integer> countOccurrences(T[] array) {
        Map<T, Integer> result = new HashMap<>();
        for (T element : array) {
            result.merge(element, 1, Integer::sum);
        }
        return result;
    }

    public static void main(String[] args) {
        Double[] numbers = {1.0, 2.0, 2.0, 2.0, 3.0, 1.0};
        System.out.println(countOccurrences(numbers)); //{2.0=3, 1.0=2, 3.0=1}
        String[] words = {"one", "hello", "one", "world", "hello", "one"};
        System.out.println(countOccurrences(words)); //{world=1, one=3, hello=2}
    }
}
