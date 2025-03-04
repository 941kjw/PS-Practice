import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Solution {

    static int depth;
    static int width;
    static int standard;
    static int dose;

    static boolean[][] section = new boolean[13][20];
    static boolean[][] origin = new boolean[13][20];

    static void init(StreamTokenizer tokenizer) throws IOException {
        depth = read(tokenizer);
        width = read(tokenizer);
        standard = read(tokenizer);
        dose = Integer.MAX_VALUE;

        for (int row = 0; row < depth; row++) {
            for (int col = 0; col < width; col++) {
                boolean value = read(tokenizer) == 1;
                section[row][col] = value;
                origin[row][col] = value;
            }
        }
    }

    static void dfs(int combination, int step, int row) {
        if (step >= dose) {
            return;
        }

        if (row == depth) {
            if (test()) {
                dose = Math.min(dose, step);
            }
            return;
        }

        if ((combination & (1 << row)) != 0) {
            Arrays.fill(section[row], false);
            dfs(combination, step + 1, row + 1);

            Arrays.fill(section[row], true);
            dfs(combination, step + 1, row + 1);
        } else {
            dfs(combination, step, row + 1);
        }
    }

    static boolean test() {
        for (int col = 0; col < width; col++) {
            boolean isA = section[0][col];
            int sequence = 1;
            for (int row = 1; row < depth; row++) {
                if (isA == section[row][col]) {
                    sequence++;
                } else {
                    isA = section[row][col];
                    sequence = 1;
                }
                if (sequence == standard) {
                    break;
                }
            }
            if (sequence != standard) {
                return false;
            }
        }
        return true;
    }

    static void restoreMap() {
        for (int row = 0; row < depth; row++) {
            System.arraycopy(origin[row], 0, section[row], 0, width);
        }
    }

    static void tryDose() {
        for (int combination = 0; combination < (1 << depth); combination++) {
            dfs(combination, 0, 0);
            restoreMap();
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer tokenizer = new StreamTokenizer(reader);
        StringBuilder builder = new StringBuilder();

        int testCount = read(tokenizer);

        for (int testNumber = 1; testNumber <= testCount; testNumber++) {
            init(tokenizer);
            tryDose();
            builder.append('#').append(testNumber).append(' ').append(dose).append('\n');
        }
        System.out.println(builder);
    }

    static int read(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        return (int) tokenizer.nval;
    }
}
