package org.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Main {
    private static final int CHUNK_SIZE = 5; // Adjust chunk size for the test string
    private static final String TEST_STRING = "Hello Hello Hello How are you doing today? Hello One morning, when Gregor Samsa woke from troubled dreams, he found himself transformed in his bed into a horrible vermin. He lay on his armour-like back, and if he lifted his head a little he could see his brown belly, slightly domed and divided by arches into stiff sections. The bedding was hardly able to cover it and seemed ready to slide off any moment. His many legs, pitifully thin compared with the size of the rest of him, waved about helplessly as he looked. \"What's happened to me?\" he thought. It wasn't a dream. His room, a proper human room although a little too small, lay peacefully between its four familiar walls. A collection of textile samples lay spread out on the table - Samsa was a travelling salesman - and above it there hung a picture that he had recently cut out of an illustrated magazine and housed in a nice, gilded frame. It showed a lady fitted out with a fur hat and fur boa who sat upright, raising a heavy fur muff that covered the whole of her lower arm towards the viewer. Gregor then turned to look out the window at the dull weather. Drops";

    public static void main(String[] args) {
        Map<String, Integer> wordCounts = new ConcurrentHashMap<>();

        // Simulate the file size based on the length of the test string
        int stringLength = TEST_STRING.length();
        int numChunks = (int) Math.ceil((double) stringLength / CHUNK_SIZE);

        Thread[] threads = new Thread[numChunks];

        for (int i = 0; i < numChunks; i++) {
            int start = i * CHUNK_SIZE;
            int end = Math.min(start + CHUNK_SIZE, stringLength);
            String chunk = TEST_STRING.substring(start, end);

            // Pass the chunk to the BatchProcessor
            threads[i] = new Thread(new BatchProcessor(chunk, wordCounts));
            threads[i].start();
        }

        // Join all threads to ensure they complete before proceeding
        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Output the word count result
        System.out.println("Word counts: " + wordCounts);
    }
}
