package org.example;

import java.util.Map;
import java.util.Scanner;

public class BatchProcessor implements Runnable {
    private final String chunk;
    private final Map<String, Integer> wordCounts;

    public BatchProcessor(String chunk, Map<String, Integer> wordCounts) {
        this.chunk = chunk;
        this.wordCounts = wordCounts;
    }

    @Override
    public void run() {
        Scanner scanner = new Scanner(chunk);
        while (scanner.hasNext()) {
            String word = scanner.next();
            wordCounts.put(word, wordCounts.getOrDefault(word,0)+1); // Thread-safe word count
        }
    }
}
