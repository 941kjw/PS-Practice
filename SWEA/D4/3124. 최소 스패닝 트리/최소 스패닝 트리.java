import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 * 단순한 프림 알고리즘 문제
 *
 *
 * 1. 입력받은 간선을 간선 리스트에 저장한다. 이때, 양뱡향으로 저장해야 함.
 * 2. 우선순위 큐에 0을 향하는 0 만큼의 길이의 간선을 넣고(0부터 시작하도록)
 * 3. 정점 수 -1 만큼 선택하면 종료한다.
 *
 *
 */

public class Solution {

    private static int vertexCount;
    private static int edgeCount;


    private static long weightSum;
    private static boolean[] visited;
    private static List<Edge>[] edges;

    private static void init(StreamTokenizer tokenizer) throws IOException {

        vertexCount = read(tokenizer);
        edgeCount = read(tokenizer);

        edges = new List[vertexCount];
        visited = new boolean[vertexCount];
        weightSum = 0;

        for (int idx = 0; idx < edgeCount; idx++) {
            int from = read(tokenizer) - 1;
            int to = read(tokenizer) - 1;
            int weight = read(tokenizer);
            if (edges[from] == null) {
                edges[from] = new ArrayList<>();
            }
            edges[from].add(new Edge(to, weight));

            if (edges[to] == null) {
                edges[to] = new ArrayList<>();
            }
            edges[to].add(new Edge(from, weight));
        }
    }

    private static void makeTree() {
        PriorityQueue<Edge> priorityQueue = new PriorityQueue<>();

        priorityQueue.add(new Edge(0, 0));
        int count = 0;
        while (!priorityQueue.isEmpty() && count <= vertexCount) {
            Edge cur = priorityQueue.poll();

            if (visited[cur.to]) {
                continue;
            }

            visited[cur.to] = true;
            ++count;
            weightSum += cur.weight;

            for (Edge next : edges[cur.to]) {
                if (!visited[next.to]) {
                    priorityQueue.add(next);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer tokenizer = new StreamTokenizer(reader);
        StringBuilder builder = new StringBuilder();

        int testCount = read(tokenizer);

        for (int testNumber = 1; testNumber <= testCount; testNumber++) {
            init(tokenizer);
            makeTree();
            builder.append('#').append(testNumber).append(' ').append(weightSum).append('\n');

        }
        System.out.println(builder);
    }

    private static int read(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        return (int) tokenizer.nval;
    }

    private static class Edge implements Comparable<Edge> {
        int to, weight;

        public Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }

        @Override
        public int compareTo(Edge o) {
            return Integer.compare(this.weight, o.weight);
        }
    }
}
