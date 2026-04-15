import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Main {

    private static BufferedReader reader;
    private static BufferedWriter writer;
    private static StreamTokenizer tokenizer;

    static final int MAX = 1_000_001;
    static int[] cnt     = new int[MAX];
    static int[] dist    = new int[MAX];
    static int[] visited = new int[MAX];
    static int[] parent  = new int[MAX];

    @SuppressWarnings("unchecked")
    static List<int[]>[] G = new ArrayList[MAX]; // {node, 거리}

    // cnt가 남아있는 가장 가까운 조상을 찾아 반환 (경로 압축 포함)
    static int findf(int current) {
        int root = current;
        while (cnt[root] == 0) {
            root = parent[root];
        }
        cnt[root]--;
        // current ~ root 사이 모든 노드를 root로 경로 압축
        while (current != root) {
            int next = parent[current];
            parent[current] = root;
            current = next;
        }
        return root;
    }

    static void dijkstra() {
        // {dist, node, parent}
        // C++ max-heap {{-dist, node}, parent} 과 동일한 우선순위:
        // 1) 거리 작을수록, 2) 노드 번호 클수록, 3) 부모 번호 클수록
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]); // 거리 오름차순
            if (a[1] != b[1]) return Integer.compare(b[1], a[1]); // 노드 번호 내림차순
            return Integer.compare(b[2], a[2]);                   // 부모 번호 내림차순
        });
        pq.offer(new int[]{0, 1, 0});
        while (!pq.isEmpty()) {
            int[] top = pq.poll();
            int d = top[0], cur = top[1], p = top[2];
            if (visited[cur] != 0) continue;
            dist[cur]    = d;
            parent[cur]  = p;
            visited[cur] = 1;
            for (int[] edge : G[cur]) {
                pq.offer(new int[]{d + edge[1], edge[0], cur});
            }
        }
    }

    public static void main(String[] args) throws IOException {
        initReader();

        int N = getNumber(), M = getNumber(), K = getNumber();

        cnt[0] = 1_000_000_007;
        for (int i = 0; i <= N; i++) G[i] = new ArrayList<>();
        for (int i = 1; i <= N; i++) cnt[i] = getNumber();

        for (int i = 0; i < M; i++) {
            int x = getNumber(), y = getNumber(), d = getNumber();
            G[x].add(new int[]{y, d});
            G[y].add(new int[]{x, d});
        }

        dijkstra();

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < K; i++) {
            int x = getNumber();
            if (x > 1 && dist[x] == 0) {
                sb.append(-1).append('\n');
            } else {
                int ans = findf(x);
                sb.append(ans != 0 ? ans : -1).append('\n');
            }
        }
        writer.write(sb.toString());

        clear();
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
}
