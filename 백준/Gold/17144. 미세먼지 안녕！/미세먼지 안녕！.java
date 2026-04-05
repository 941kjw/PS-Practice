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

    private static final int[] dx = {-1, 0, 1, 0};
    private static final int[] dy = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        initReader();

        int R = getNumber();
        int C = getNumber();
        int T = getNumber();

        int purifierR = 0;

        int[][] map = new int[R][C];
        for (int i = 0; i < R; ++i) {
            for (int j = 0; j < C; ++j) {
                map[i][j] = getNumber();
                if (map[i][j] == -1) {
                    purifierR = i;
                }
            }
        }

        simulate(map, R, C, T, purifierR);

        clear();
    }

    private static void simulate(int[][] map, int R, int C, int T, int purifierR) throws IOException {
        while (T-- > 0) {
            map = spread(map, R, C, purifierR);

            rotateUpper(map, purifierR - 1, C);
            rotateLower(map, purifierR, R, C);
        }
        int sum = 0;

        for (int i = 0; i < R; ++i) {
            for (int j = 0; j < C; ++j) {
                if (map[i][j] <= 0) {
                    continue;
                }
                sum += map[i][j];
            }
        }

        writer.write(String.valueOf(sum));
    }

    private static int[][] spread(int[][] map, int R, int C, int purifierR) {
        int[][] after = new int[R][C];
        after[purifierR - 1][0] = -1;
        after[purifierR][0] = -1;

        for (int i = 0; i < R; ++i) {
            for (int j = 0; j < C; ++j) {
                if (map[i][j] <= 0) {
                    continue;
                }

                int spreadAmount = map[i][j] / 5;
                int count = 0;

                for (int d = 0; d < 4; ++d) {
                    int nx = i + dx[d];
                    int ny = j + dy[d];

                    if (nx < 0 || nx >= R || ny < 0 || ny >= C || map[nx][ny] == -1) {
                        continue;
                    }

                    after[nx][ny] += spreadAmount;
                    ++count;
                }

                after[i][j] += map[i][j] - spreadAmount * count;
            }
        }

        return after;
    }

    private static void rotateUpper(int[][] map, int top, int C) {
        for (int i = top - 1; i > 0; --i) {
            map[i][0] = map[i - 1][0];
        }

        for (int j = 0; j < C - 1; ++j) {
            map[0][j] = map[0][j + 1];
        }

        for (int i = 0; i < top; ++i) {
            map[i][C - 1] = map[i + 1][C - 1];
        }

        for (int j = C - 1; j > 1; --j) {
            map[top][j] = map[top][j - 1];
        }

        map[top][1] = 0;
    }

    private static void rotateLower(int[][] map, int bottom, int R, int C) {
        for (int i = bottom + 1; i < R - 1; ++i) {
            map[i][0] = map[i + 1][0];
        }

        for (int j = 0; j < C - 1; ++j) {
            map[R - 1][j] = map[R - 1][j + 1];
        }

        for (int i = R - 1; i > bottom; --i) {
            map[i][C - 1] = map[i - 1][C - 1];
        }

        for (int j = C - 1; j > 1; --j) {
            map[bottom][j] = map[bottom][j - 1];
        }

        map[bottom][1] = 0;
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
