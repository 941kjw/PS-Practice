import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Main {

    private static BufferedReader reader;
    private static BufferedWriter writer;
    private static StreamTokenizer tokenizer;

    public static void main(String[] args) throws IOException {
        initReader();

        int N = getNumber();
        int K = getNumber();

        Jewel[] jewels = new Jewel[N];
        int[] bagMaxWeight = new int[K];

        for (int i = 0; i < N; i++) {
            int weight = getNumber();
            int price = getNumber();
            jewels[i] = new Jewel(weight, price);
        }

        for (int i = 0; i < K; i++) {
            int maxWeight = getNumber();
            bagMaxWeight[i] = maxWeight;
        }

        Arrays.sort(jewels);
        Arrays.sort(bagMaxWeight);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        long sum = 0;

        for (int i = 0, j = 0; i < K; i++) {
            while (j < N && jewels[j].weight <= bagMaxWeight[i]) {
                pq.add(jewels[j++].price);
            }

            if (!pq.isEmpty()) {
                sum += pq.poll();
            }
        }

        writer.write(String.valueOf(sum));
        clear();
    }

    private static class Jewel implements Comparable<Jewel> {
        int weight, price;

        public Jewel(int weight, int price) {
            this.weight = weight;
            this.price = price;
        }

        @Override
        public int compareTo(Jewel o) {
            if (weight == o.weight) {
                return Integer.compare(o.price, price);
            }

            return Integer.compare(weight, o.weight);
        }
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

