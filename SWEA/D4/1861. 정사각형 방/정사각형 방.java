import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Solution {

    static int[][] rooms = new int[1000][1000];
    static int[][] footSteps = new int[1000][1000];
    static int[] dx = {0, -1, 0, 1};
    static int[] dy = {1, 0, -1, 0};
    static int roomLength;
    static int roomNumber;
    static int moves;
    static StringBuilder builder = new StringBuilder();

    static void init(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        roomLength = (int) tokenizer.nval;
        moves = Integer.MIN_VALUE;
        for (int row = 0; row < roomLength; row++) {
            for (int col = 0; col < roomLength; col++) {
                tokenizer.nextToken();
                rooms[row][col] = (int) tokenizer.nval;
                footSteps[row][col] = 1;
            }
        }
    }

    static void move() {

        for (int row = 0; row < roomLength; row++) {
            for (int col = 0; col < roomLength; col++) {
                int streak = findRoute(row, col);
                if (moves < streak) {
                    roomNumber = rooms[row][col];
                    moves = streak;
                } else if (moves == streak) {
                    roomNumber = Math.min(roomNumber, rooms[row][col]);
                }
            }
        }
    }

    static int findRoute(int row, int col) {
        if (footSteps[row][col] != 1) {
            return footSteps[row][col];
        }

        int sum = footSteps[row][col];
        for (int dir = 0; dir < 4; dir++) {
            int nextRow = row + dy[dir];
            int nextCol = col + dx[dir];
            if (nextRow < 0 || nextCol < 0 || nextRow > roomLength - 1 || nextCol > roomLength - 1
                    || rooms[nextRow][nextCol] != rooms[row][col] + 1) {
                continue;
            }
            sum += findRoute(nextRow, nextCol);
        }
        return footSteps[row][col] = sum;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer tokenizer = new StreamTokenizer(reader);

        tokenizer.nextToken();
        int testCount = (int) tokenizer.nval;

        for (int testNumber = 1; testNumber <= testCount; testNumber++) {
            init(tokenizer);

            move();
            builder.append('#').append(testNumber).append(' ').append(roomNumber).append(' ').append(moves)
                    .append('\n');
        }

        System.out.println(builder);
    }

}