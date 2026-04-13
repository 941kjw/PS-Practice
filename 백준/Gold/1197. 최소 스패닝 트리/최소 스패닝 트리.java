import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {

    private static BufferedReader reader;
    private static BufferedWriter writer;
    private static StreamTokenizer tokenizer;

    public static void main(String[] args) throws IOException {
        initReader();

        int v = getNumber();
        int e = getNumber();

        int[] parent = new int[v + 1];
        int[] rank = new int[v + 1];

        for (int i = 0; i <= v; i++) {
            parent[i] = i;
        }

        List<Edge> edges = new ArrayList<>();

        for (int i = 0; i < e; ++i) {
            int first = getNumber();
            int second = getNumber();
            int weight = getNumber();

            edges.add(new Edge(first, second, weight));
        }

        Collections.sort(edges);

        int sum = 0;

        for (Edge edge : edges) {
            int firstParent = findParent(edge.first, parent);
            int secondParent = findParent(edge.second, parent);

            if (firstParent == secondParent) {
                continue;
            }

            sum += edge.weight;
            union(firstParent, secondParent, parent, rank);
        }

        writer.write(String.valueOf(sum));
        clear();
    }

    private static int findParent(int x, int[] parent) {
        if (x == parent[x]) {
            return x;
        }
        return parent[x] = findParent(parent[x], parent);
    }

    private static void union(int rootA, int rootB, int[] parent, int[] rank) {
        if (rank[rootA] < rank[rootB]) {
            parent[rootA] = rootB;
        } else if (rank[rootA] > rank[rootB]) {
            parent[rootB] = rootA;
        } else {
            parent[rootB] = rootA;
            rank[rootA]++;
        }
    }


    private static class Edge implements Comparable<Edge> {
        int first, second, weight;

        public Edge(int first, int second, int weight) {
            this.first = first;
            this.second = second;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }

        @Override
        public String toString() {
            return "[" + first + ", " + second + ", " + weight + "]";
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
