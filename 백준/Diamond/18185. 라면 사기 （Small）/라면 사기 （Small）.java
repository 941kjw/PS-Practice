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

    static int n;
    static int[] ramen;
    static long cost;

    public static void main(String[] args) throws IOException {
        initReader();

        n = getNumber();
        ramen = new int[n + 3];
        cost = 0;

        for (int i = 1; i <= n; i++) {
            ramen[i] = getNumber();
        }

        greedy();

        writer.write(cost + "\n");
        clear();
    }

    private static void greedy() {
        for (int i = 1; i <= n; i++) {
            int tmp;
            if (ramen[i + 1] > ramen[i + 2]) {
                // 두 번째 라면이 세 번째보다 많은 경우
                // 7원 구매를 최대한 하기 위해 최소한으로 5원 구매
                tmp = Math.min(ramen[i], ramen[i + 1] - ramen[i + 2]);
                cost += 5L * tmp;
                ramen[i] -= tmp;
                ramen[i + 1] -= tmp;

                tmp = Math.min(ramen[i], Math.min(ramen[i + 1], ramen[i + 2]));
                cost += 7L * tmp;
                ramen[i] -= tmp;
                ramen[i + 1] -= tmp;
                ramen[i + 2] -= tmp;
            } else {
                // 두 번째 라면이 세 번째보다 적거나 같은 경우
                // 7원 구매를 최대한 한 후 5원 구매
                tmp = Math.min(ramen[i], ramen[i + 1]);
                cost += 7L * tmp;
                ramen[i] -= tmp;
                ramen[i + 1] -= tmp;
                ramen[i + 2] -= tmp;

                tmp = Math.min(ramen[i], ramen[i + 1]);
                cost += 5L * tmp;
                ramen[i] -= tmp;
                ramen[i + 1] -= tmp;
            }
            cost += 3L * ramen[i];
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
}
