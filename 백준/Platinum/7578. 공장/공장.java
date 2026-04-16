import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;
import java.util.HashMap;
import java.util.Map;

public class Main {

    private static BufferedReader reader;
    private static BufferedWriter writer;
    private static StreamTokenizer tokenizer;

    static long[] tree;

    static long sum(int start, int end, int node, int left, int right) {
        if (end < left || right < start) return 0;
        if (left <= start && end <= right) return tree[node];
        int mid = (start + end) / 2;
        return sum(start, mid, node * 2, left, right)
             + sum(mid + 1, end, node * 2 + 1, left, right);
    }

    static void update(int start, int end, int node, int idx) {
        if (idx < start || idx > end) return;
        tree[node]++;
        if (start != end) {
            int mid = (start + end) / 2;
            update(start, mid, node * 2, idx);
            update(mid + 1, end, node * 2 + 1, idx);
        }
    }

    public static void main(String[] args) throws IOException {
        initReader();

        int n = getNumber();

        int[] a = new int[n + 1];
        for (int i = 1; i <= n; i++) a[i] = getNumber();

        Map<Integer, Integer> bPos = new HashMap<>();
        for (int i = 1; i <= n; i++) bPos.put(getNumber(), i);

        tree = new long[n * 4];
        long ans = 0;

        for (int i = 1; i <= n; i++) {
            int vb = bPos.get(a[i]);
            ans += sum(1, n, 1, vb + 1, n); // vb보다 큰 위치에 이미 삽입된 수
            update(1, n, 1, vb);
        }

        writer.write(ans + "\n");
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
