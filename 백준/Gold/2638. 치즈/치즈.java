import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Main {

    private static BufferedReader reader;
    private static BufferedWriter writer;
    private static StreamTokenizer tokenizer;

    private static final int[] dx = {-1, 0, 0, 1};
    private static final int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        initReader();

        int N = getNumber();
        int M = getNumber();

        boolean[][] map = new boolean[N][M];
        List<Point> cheeseList = new ArrayList<>();
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                map[i][j] = getNumber() == 1;
            }
        }

        simulate(map);

        clear();
    }

    private static void simulate(boolean[][] map) throws IOException {
        Deque<Point> queue = new ArrayDeque<>();

        int col = map[0].length;
        int row = map.length;

        for (int c = 0; c < col; ++c) {
            queue.add(new Point(0, c, 0));
            queue.add(new Point(row - 1, c, 0));
        }

        for (int r = 1; r < row - 1; ++r) {
            queue.add(new Point(r, 0, 0));
            queue.add(new Point(r, col - 1, 0));
        }

        int[][] board = new int[row][col];
        boolean[][] visited = new boolean[row][col];

        int time = 0;

        while (!queue.isEmpty()) {
            Point cur = queue.poll();

            if (visited[cur.row][cur.col]) {
                continue;
            }

            visited[cur.row][cur.col] = true;
            time = cur.day;

            for (int dir = 0; dir < 4; ++dir) {
                int nr = cur.row + dx[dir];
                int nc = cur.col + dy[dir];

                if (isOut(row, col, nr, nc) || visited[nr][nc]) {
                    continue;
                }

                if (!map[nr][nc]) {
                    queue.addFirst(new Point(nr, nc, cur.day));
                    continue;
                }

                if (++board[nr][nc] > 1) {
                    queue.addLast(new Point(nr, nc, cur.day + 1));
                }

            }
        }

        writer.write(Integer.toString(time));
    }

    private static boolean isOut(int row, int col, int r, int c) {
        return 0 > r || r >= row || 0 > c || c >= col;
    }


    private static class Point {
        int row, col, day;

        public Point(int row, int col, int day) {
            this.row = row;
            this.col = col;
            this.day = day;
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
