import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;

public class Main {

    private static BufferedReader reader;
    private static BufferedWriter writer;
    private static StreamTokenizer tokenizer;

    public static void main(String[] args) throws IOException {
        initReader();

        String s1 = getString();
        String s2 = getString();
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        lcs(dp, s1, s2);
        clear();
    }

    private static void lcs(int[][] dp, String s1, String s2) throws IOException {
        char[] a = s1.toCharArray();
        char[] b = s2.toCharArray();

        int n = a.length;
        int m = b.length;

        for (int i = 1; i <= n; ++i) {
            int[] cur = dp[i];
            int[] prev = dp[i - 1];
            char ca = a[i - 1];

            for (int j = 1; j <= m; ++j) {
                if (ca == b[j - 1]) {
                    cur[j] = prev[j - 1] + 1;
                } else {
                    cur[j] = Math.max(prev[j], cur[j - 1]);
                }
            }
        }

        writer.write(String.valueOf(dp[n][m]));

        if (dp[n][m] > 0) {
            int len = dp[n][m];
            char[] ans = new char[len];
            int idx = len - 1;

            int x = n;
            int y = m;

            while (x > 0 && y > 0) {
                if (a[x - 1] == b[y - 1]) {
                    ans[idx--] = a[x - 1];
                    --x;
                    --y;
                } else if (dp[x - 1][y] >= dp[x][y - 1]) {
                    --x;
                } else {
                    --y;
                }
            }

            writer.write("\n" + String.valueOf(ans));
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
