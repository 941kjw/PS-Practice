import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Main {

    private static BufferedReader reader;
    private static BufferedWriter writer;
    private static StreamTokenizer tokenizer;

    public static void main(String[] args) throws IOException {
        initReader();

        int testCount = getNumber();
        for (int i = 0; i < testCount; ++i) {
            simulate();
        }
        clear();
    }

    private static void simulate() throws IOException {
        PriorityQueue<Integer> maxQ = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minQ = new PriorityQueue<>();
        Map<Integer, Integer> countMap = new HashMap<>();

        int commandCount = getNumber();

        for (int i = 0; i < commandCount; ++i) {
            String command = getString();

            int number = getNumber();
            if (command.equals("I")) {
                maxQ.add(number);
                minQ.add(number);

                if (countMap.get(number) == null) {
                    countMap.put(number, 1);
                    continue;
                }
                countMap.merge(number, 1, Integer::sum);
            }

            if (command.equals("D")) {
                if (number == 1) {
                    while (!maxQ.isEmpty() && countMap.getOrDefault(maxQ.peek(), 0) == 0) {
                        maxQ.poll();
                    }
                    if (!maxQ.isEmpty()) {
                        int top = maxQ.poll();
                        countMap.compute(top, (k, v) -> v - 1);
                    }
                }
                if (number == -1) {
                    while (!minQ.isEmpty() && countMap.getOrDefault(minQ.peek(), 0) == 0) {
                        minQ.poll();
                    }
                    if (!minQ.isEmpty()) {
                        int top = minQ.poll();
                        countMap.compute(top, (k, v) -> v - 1);
                    }
                }
            }
        }

        while (!maxQ.isEmpty() && countMap.getOrDefault(maxQ.peek(), 0) == 0) {
            maxQ.poll();
        }

        Integer max = maxQ.isEmpty() ? null : maxQ.poll();

        while (!minQ.isEmpty() && countMap.getOrDefault(minQ.peek(), 0) == 0) {
            minQ.poll();
        }

        Integer min = minQ.isEmpty() ? null : minQ.poll();

        if (max == null || min == null) {
            writer.write("EMPTY\n");
            return;
        }

        writer.write(max + " " + min + "\n");
    }


    private static void clear() throws IOException {
        reader.close();
        writer.close();
    }

    private static void initReader() {
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new BufferedWriter(new OutputStreamWriter(System.out));
        tokenizer = new StreamTokenizer(reader);
    }

    private static int getNumber() throws IOException {
        tokenizer.nextToken();
        return (int) tokenizer.nval;
    }

    private static String getString() throws IOException {
        tokenizer.nextToken();
        return tokenizer.sval;
    }
}
