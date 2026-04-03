import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    private static BufferedReader reader;
    private static BufferedWriter writer;
    private static StreamTokenizer tokenizer;

    private static final int[] dx = {-1, 0, 0, 1};
    private static final int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        initReader();

        int cityCount = getNumber();
        int busCount = getNumber();

        List[] map = new List[cityCount];
        for (int idx = 0; idx < cityCount; ++idx) {
            map[idx] = new ArrayList<Bus>();
        }

        for (int i = 0; i < busCount; ++i) {
            int from = getNumber() - 1;
            int to = getNumber() - 1;
            int price = getNumber();

            map[from].add(new Bus(to, price));
        }

        int start = getNumber();
        int end = getNumber();

        simulate(map, start, end);

        clear();
    }

    private static void simulate(List<Bus>[] map, int start, int end) throws IOException {
        start = start - 1;
        end = end - 1;
        int[] price = new int[map.length];
        int[] prev = new int[map.length];
        Arrays.fill(price, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        price[start] = 0;

        PriorityQueue<Bus> pq = new PriorityQueue<>();
        boolean[] visited = new boolean[map.length];
        pq.add(new Bus(start, 0));

        while (!pq.isEmpty()) {
            Bus cur = pq.poll();
            if (visited[cur.end]) {
                continue;
            }

            visited[cur.end] = true;

            for (Bus b : map[cur.end]) {
                if (price[cur.end] + b.price < price[b.end]) {
                    price[b.end] = price[cur.end] + b.price;
                    prev[b.end] = cur.end;
                    pq.add(new Bus(b.end, price[b.end]));
                }
            }
        }

        int min = price[end];

        List<Integer> path = new ArrayList<>();
        for (int node = end; node != -1; node = prev[node]) {
            path.add(node);
        }
        Collections.reverse(path);

        int count = path.size();
        writer.write(min + "\n");
        writer.write(count + "\n");
        for (Integer i : path) {
            writer.write((i + 1) + " ");
        }
    }

    private static class Bus implements Comparable<Bus> {
        int end, price;

        public Bus(int end, int price) {
            this.end = end;
            this.price = price;
        }

        @Override
        public int compareTo(Bus o) {
            return Integer.compare(this.price, o.price);
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
