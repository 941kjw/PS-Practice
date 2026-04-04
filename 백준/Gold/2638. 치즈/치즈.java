import java.awt.Point;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

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

        int[][] map = new int[N][M];
        List<Point> cheeseList = new ArrayList<>();
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < M; ++j) {
                map[i][j] = getNumber();
                if (map[i][j] == 1) {
                    cheeseList.add(new Point(i, j));
                }
            }
        }

        simulate(map, cheeseList);

        clear();
    }

    private static void simulate(int[][] map, List<Point> cheeseList) throws IOException {
        int timeCount = 0;

        while (!cheeseList.isEmpty()) {
            ++timeCount;
            map = markAir(map);

            for (int idx = 0; idx < cheeseList.size(); ++idx) {
                int x = cheeseList.get(idx).x;
                int y = cheeseList.get(idx).y;

                int count = 0;

                for (int i = 0; i < 4; ++i) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (map[nx][ny] == 2) {
                        ++count;
                    }
                }

                if (count >= 2) {
                    map[x][y] = 0;
                    cheeseList.remove(idx);
                    --idx;
                }
            }
        }
        writer.write(Integer.toString(timeCount));
    }

    private static int[][] markAir(int[][] map) {
        boolean[][] visited = new boolean[map.length][map[0].length];

        Queue<Point> queue = new LinkedList<>();
        queue.add(new Point(0, 0));
        visited[0][0] = true;
        map[0][0] = 2;

        while (!queue.isEmpty()) {
            int x = queue.peek().x;
            int y = queue.poll().y;

            for (int i = 0; i < 4; ++i) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || nx >= map.length || ny < 0 || ny >= map[0].length || map[nx][ny] == 1
                        || visited[nx][ny]) {
                    continue;
                }

                map[nx][ny] = 2;
                queue.add(new Point(nx, ny));
                visited[nx][ny] = true;
            }
        }

        return map;
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
