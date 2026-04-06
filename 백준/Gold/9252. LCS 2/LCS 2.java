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
        int s1len = s1.length();
        int s2len = s2.length();

        for (int i = 1; i < s1len + 1; ++i) {
            for (int j = 1; j < s2len + 1; ++j) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        writer.write(String.valueOf(dp[s1len][s2len]));

        if (dp[s1len][s2len] > 0) {
            StringBuilder b = new StringBuilder();

            int x = s1len;
            int y = s2len;

            while (x > 0 && y > 0) {
                if (s1.charAt(x - 1) == s2.charAt(y - 1)) {
                    b.append(s1.charAt(x - 1));
                    --x;
                    --y;
                } else if (dp[x - 1][y] > dp[x][y - 1]) {
                    --x;
                } else {
                    --y;
                }
            }

            writer.write("\n" + b.reverse());
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
