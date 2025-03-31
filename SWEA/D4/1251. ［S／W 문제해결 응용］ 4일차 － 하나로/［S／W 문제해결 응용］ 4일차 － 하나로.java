import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 모든 섬을 잇되, 최소 비용으로 이어야 한다.
 * 섬의 좌표만 주어지고, 간선은 주어지지 않으므로 모든 간선을 직접 만들어야 한다.
 *
 * 1.입력받은 섬들의 모든 간선을 생성
 * 2.간선을 비용이 적은 순서대로 정렬한다.
 * 3.union-find를 통해, 모든 간선을 순환하다가 선택한 간선 갯수가 섬 개수 -1이 되면 종료.
 *
 *
 */
public class Solution {

    private static int islandCount;

    private static int[] islandX;
    private static int[] islandY;
    private static boolean[] visited;
    private static double[] minEdge;
    private static double taxRate;
    private static double minTax;
    private static List<Edge>[] graph;

    private static void init(StreamTokenizer tokenizer) throws IOException {

        islandCount = read(tokenizer);

        visited = new boolean[islandCount];
        islandX = new int[islandCount];
        islandY = new int[islandCount];
        minEdge = new double[islandCount];
        graph = new List[islandCount];

        minTax = 0;

        for (int idx = 0; idx < islandCount; idx++) {
            islandX[idx] = read(tokenizer);
            minEdge[idx] = Double.MAX_VALUE;
        }

        for (int idx = 0; idx < islandCount; idx++) {
            islandY[idx] = read(tokenizer);
        }

        taxRate = readDouble(tokenizer);

        makeEdges();
    }

    private static void makeEdges() {
        for (int from = 0; from < islandCount; from++) {
            for (int to = from + 1; to < islandCount; to++) {
                double price = getDistance(from, to) * taxRate;
                if (graph[from] == null) {
                    graph[from] = new ArrayList<>();
                }
                graph[from].add(new Edge(to, price));
                if (graph[to] == null) {
                    graph[to] = new ArrayList<>();
                }
                graph[to].add(new Edge(from, price));
            }
        }
    }

    private static void findMinCombination() {
        minEdge[0] = 0;
        for (int idx = 0; idx < islandCount; idx++) {
            int minIdx = -1;
            double min = Double.MAX_VALUE;

            // 최소 간선 찾기 (비용이 가장 작은 정점 u)
            for (int subIdx = 0; subIdx < islandCount; subIdx++) {
                if (!visited[subIdx] && minEdge[subIdx] < min) {
                    min = minEdge[subIdx];
                    minIdx = subIdx;
                }
            }

            if (minIdx == -1) {
                break;
            }

            visited[minIdx] = true;
            minTax += minEdge[minIdx];

            for (Edge e : graph[minIdx]) {
                if (!visited[e.to] && e.price < minEdge[e.to]) {
                    minEdge[e.to] = e.price;
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
            findMinCombination();
            builder.append('#').append(testNumber).append(' ').append(Math.round(minTax)).append('\n');
        }

        System.out.println(builder);
    }

    private static int read(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        return (int) tokenizer.nval;
    }

    private static double readDouble(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        return tokenizer.nval;
    }

    private static double getDistance(int island1, int island2) {
        return Math.pow(Math.abs(islandX[island1] - islandX[island2]), 2) + Math.pow(
                Math.abs(islandY[island1] - islandY[island2]), 2);
    }

    private static class Edge implements Comparable<Edge> {
        int to;
        double price;

        public Edge(int to, double price) {
            this.to = to;
            this.price = price;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.price, o.price);
        }
    }

}
