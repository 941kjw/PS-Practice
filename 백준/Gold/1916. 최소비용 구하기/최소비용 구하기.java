import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;


public class Main {

    private static BufferedReader reader;
    private static BufferedWriter writer;
    private static StreamTokenizer tokenizer;

    public static void main(String[] args) throws IOException {
        initReader();

        int cityCount = getNumber();
        int busCount = getNumber();

        List<List<Edge>> busInfo = new ArrayList<>();

        for (int i = 0; i < cityCount + 1; ++i) {
            busInfo.add(new ArrayList<>());
        }

        for (int i = 0; i < busCount; ++i) {
            int from = getNumber();
            int to = getNumber();
            int cost = getNumber();

            busInfo.get(from).add(new Edge(to, cost));
        }

        int start = getNumber();
        int end = getNumber();

        long minimum = findMinimum(busInfo, start, end);

        writer.write(String.valueOf(minimum));
        clear();
    }


    private static long findMinimum(List<List<Edge>> busInfo, int start, int end) {
        int cityCount = busInfo.size();

        int[] distance = new int[cityCount];
        boolean[] visited = new boolean[cityCount];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[start] = 0;

        PriorityQueue<Edge> q = new PriorityQueue<>(Comparator.comparingInt(e -> e.cost));
        q.add(new Edge(start, 0));

        while (!q.isEmpty()) {
            Edge e = q.poll();
            if (!visited[e.to]) {
                visited[e.to] = true;

                for (Edge d : busInfo.get(e.to)) {
                    if (!visited[d.to] && distance[d.to] > e.cost + d.cost) {
                        distance[d.to] = e.cost + d.cost;
                        q.add(new Edge(d.to, distance[d.to]));
                    }
                }
            }
        }

        return distance[end];
    }

    static class Edge {
        int to, cost;

        Edge(int to, int cost) {
            this.to = to;
            this.cost = cost;
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
