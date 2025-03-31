import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;

public class Solution {

    private static final int[][] delta = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};
    private static int[][] map;
    private static List<Pos> cores;

    private static int maxConnected;
    private static int minLength;

    private static void init(StreamTokenizer tokenizer) throws IOException {
        int mapSize = read(tokenizer);
        map = new int[mapSize][mapSize];
        cores = new ArrayList<>();
        maxConnected = Integer.MIN_VALUE;
        minLength = Integer.MAX_VALUE;

        for (int row = 0; row < mapSize; ++row) {
            for (int col = 0; col < mapSize; ++col) {
                int value = read(tokenizer);
                if (value == 1) {
                    map[row][col] = value;
                    if (row == 0 || row == mapSize - 1 || col == 0 || col == mapSize - 1) {
                        continue;
                    }
                    cores.add(new Pos(row, col));
                }
            }
        }
    }

    private static void tryConnect(int number, int coreCount, int length) {
        if (number == cores.size()) {
            if (maxConnected < coreCount) {
                maxConnected = coreCount;
                minLength = length;
            } else if (maxConnected == coreCount) {
                minLength = Math.min(minLength, length);
            }
            return;
        }

        for (int idx = 0; idx < 4; ++idx) {
            int row = cores.get(number).row;
            int col = cores.get(number).col;

            int placedLength = place(row, col, idx);
            if (placedLength > 0) {
                tryConnect(number + 1, coreCount + 1, length + placedLength);
                clear(row, col, idx);
            }
        }
        tryConnect(number + 1, coreCount, length);
    }

    private static int place(int row, int col, int direction) {
        int lineLength = 0;
        int curRow = row + delta[direction][0];
        int curCol = col + delta[direction][1];
        while (curRow > -1 && curRow < map.length && curCol > -1 && curCol < map.length) {
            if (map[curRow][curCol] == 1 || map[curRow][curCol] == 2) {
                lineLength = 0;
                break;
            }
            map[curRow][curCol] = 2;
            ++lineLength;
            curRow += delta[direction][0];
            curCol += delta[direction][1];
        }

        if (lineLength == 0 && (curRow != row || curCol != col)) {
            curRow -= delta[direction][0];
            curCol -= delta[direction][1];
            while (curRow != row || curCol != col) {
                map[curRow][curCol] = 0;
                curRow -= delta[direction][0];
                curCol -= delta[direction][1];
            }
        }
        return lineLength;
    }

    private static void clear(int row, int col, int direction) {
        int curRow = row + delta[direction][0];
        int curCol = col + delta[direction][1];
        while (curRow > -1 && curRow < map.length && curCol > -1 && curCol < map.length) {
            map[curRow][curCol] = 0;
            curRow += delta[direction][0];
            curCol += delta[direction][1];
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer tokenizer = new StreamTokenizer(reader);
        StringBuilder builder = new StringBuilder();
        int testCount = read(tokenizer);

        for (int testNumber = 1; testNumber <= testCount; testNumber++) {
            init(tokenizer);
            tryConnect(0, 0, 0);
            builder.append('#').append(testNumber).append(' ').append(minLength).append('\n');
        }
        System.out.println(builder);
    }

    private static int read(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        return (int) tokenizer.nval;
    }

    private static class Pos {
        int row, col;

        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
}
