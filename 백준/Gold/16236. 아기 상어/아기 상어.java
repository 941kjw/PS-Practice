import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;
import java.util.PriorityQueue;

public class Main {

    private static BufferedReader reader;
    private static BufferedWriter writer;
    private static StreamTokenizer tokenizer;

    private static final int[] dx = {-1, 0, 0, 1};
    private static final int[] dy = {0, -1, 1, 0};

    public static void main(String[] args) throws IOException {
        initReader();

        int mapSize = getNumber();
        Point start = new Point(0, 0, 0);
        int[][] map = new int[mapSize][mapSize];

        for (int i = 0; i < mapSize; ++i) {
            for (int j = 0; j < mapSize; ++j) {
                int number = getNumber();
                map[i][j] = number;
                if (number == 9) {
                    start = new Point(i, j, 0);
                    map[i][j] = 0;
                }
            }
        }
        simulate(map, mapSize, start);
        clear();
    }

    private static void simulate(int[][] map, int mapSize, Point start) throws IOException {
        int time = 0;
        int sharkSize = 2;
        int eatCount = 0;

        while (true) {
            PriorityQueue<Point> q = new PriorityQueue<>();
            boolean[][] visited = new boolean[mapSize][mapSize];

            visited[start.x][start.y] = true;
            q.add(start);

            boolean eaten = false;

            while (!q.isEmpty()) {
                Point cur = q.poll();

                if (map[cur.x][cur.y] != 0 && map[cur.x][cur.y] < sharkSize) {
                    map[cur.x][cur.y] = 0;
                    ++eatCount;
                    time += cur.distance;
                    eaten = true;
                    start = new Point(cur.x, cur.y, 0);
                    break;
                }

                for (int d = 0; d < 4; ++d) {
                    int nx = cur.x + dx[d];
                    int ny = cur.y + dy[d];

                    if (nx < 0 || ny < 0 || nx >= mapSize || ny >= mapSize || visited[nx][ny]
                            || map[nx][ny] > sharkSize) {
                        continue;
                    }

                    visited[nx][ny] = true;
                    q.add(new Point(nx, ny, cur.distance + 1));
                }
            }

            if (!eaten) {
                break;
            }

            if (sharkSize == eatCount) {
                ++sharkSize;
                eatCount = 0;
            }
        }

        writer.write(String.valueOf(time));
    }

    private static class Point implements Comparable<Point> {
        int x, y, distance;

        Point(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.distance = d;
        }


        @Override
        public int compareTo(Point o) {
            if (this.distance != o.distance) {
                return Integer.compare(this.distance, o.distance);
            }

            if (this.x != o.x) {
                return Integer.compare(this.x, o.x);
            }

            return Integer.compare(this.y, o.y);
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
