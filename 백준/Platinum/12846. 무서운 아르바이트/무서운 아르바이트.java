import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int[] arr, tree;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        tree = new int[4*n];

        init(1, n, 1);
        System.out.println(getMax(1, n));
        br.close();
    }

    private static void init(int s, int e, int node) {
        if (s == e) {
            tree[node] = s;
            return;
        }
        int mid = (s + e) / 2;
        init(s, mid, node * 2);
        init(mid + 1, e, node * 2 + 1);
        if (arr[tree[node * 2]] < arr[tree[node * 2 + 1]]) {
            tree[node] = tree[node * 2];
        } else {
            tree[node] = tree[node * 2 + 1];
        }
    }

    private static long getMax(int l, int r) {
        int m = query(1, n, 1, l, r);

        long area = (long) (r - l + 1) * arr[m];
        if (l <= m - 1) {
            long temp = getMax(l, m - 1);
            area = Math.max(area, temp);
        }
        if (m + 1 <= r) {
            long temp = getMax(m + 1, r);
            area = Math.max(area, temp);
        }
        return area;
    }

    private static int query(int s, int e, int node, int l, int r) {
        if (r < s || e < l) {
            return -1;
        }
        if (l <= s && e <= r) {
            return tree[node];
        }

        int mid = (s + e) / 2;
        int lNode = query(s, mid, node * 2, l, r);
        int rNode = query(mid + 1, e, node * 2 + 1, l, r);

        if (lNode == -1) {
            return rNode;
        } else if (rNode == -1) {
            return lNode;
        }

        if (arr[lNode] <= arr[rNode]) {
            return lNode;
        } else {
            return rNode;
        }
    }
}