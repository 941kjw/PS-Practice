import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main {

    private static BufferedReader reader;
    private static BufferedWriter writer;
    private static StreamTokenizer tokenizer;

    public static void main(String[] args) throws IOException {
        initReader();

        int N = getNumber();
        int[] solutions = new int[N];

        for (int i = 0; i < N; i++) {
            solutions[i] = getNumber();
        }

        Arrays.sort(solutions);

        int minimum = Integer.MAX_VALUE;
        int[] minimumSolutions = new int[2];

        int start = 0;
        int end = N - 1;

        while (start < end) {
            int sum = solutions[start] + solutions[end];
            int abs = Math.abs(sum);

            if (abs < minimum) {
                minimum = abs;
                minimumSolutions[0] = start;
                minimumSolutions[1] = end;
            }

            if (sum < 0) {
                ++start;
            } else {
                --end;
            }
        }

        writer.write(solutions[minimumSolutions[0]] + " " + solutions[minimumSolutions[1]]);

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

    private static String getString() throws IOException {
        tokenizer.nextToken();
        return tokenizer.sval;
    }
}
