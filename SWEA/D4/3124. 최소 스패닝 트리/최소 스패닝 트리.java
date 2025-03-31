import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 *
 * 단순한 크루스칼 알고리즘 문제
 *
 *
 * 1. 입력받은 간선을 작은 순서대로 정렬한다.
 * 2. 최상단 간선부터 선택하며, union-find를 통해 같은 그래프 상에 존재하는 정점이면 건너뛴다.
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

        while (!priorityQueue.isEmpty()) {
            Edge cur = priorityQueue.poll();

            if (visited[cur.to]) {
                continue;
            }

            visited[cur.to] = true;
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
