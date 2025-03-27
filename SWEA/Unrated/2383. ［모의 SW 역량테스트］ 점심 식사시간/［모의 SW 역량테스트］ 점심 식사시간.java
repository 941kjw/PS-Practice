import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Solution {

    private static Stair[] stairs;
    private static List<Pos> mans;
    private static int minTime;

    private static void init(StreamTokenizer tokenizer) throws IOException {
        int roomSize = read(tokenizer);
        mans = new ArrayList<>();
        stairs = new Stair[2];

        int stairIdx = 0;

        for (int row = 0; row < roomSize; row++) {
            for (int col = 0; col < roomSize; col++) {
                int value = read(tokenizer);
                if (value == 1) {
                    mans.add(new Pos(row, col));
                }

                if (value > 1) {
                    stairs[stairIdx++] = new Stair(row, col, value);
                }
            }
        }
        minTime = Integer.MAX_VALUE;
    }

    private static void rushDown() {
        List<Man> stair1User = new ArrayList<>();
        List<Man> stair2User = new ArrayList<>();
        for (int selected = 0; selected < (1 << mans.size()); ++selected) {
            for (int radix = 0; radix < mans.size(); ++radix) {
                int number = selected & (1 << radix);

                Pos cur = mans.get(radix);
                if (number == 0) {
                    stair1User.add(new Man(cur.row, cur.col, getDistance(radix, 0)));
                    continue;
                }
                stair2User.add(new Man(cur.row, cur.col, getDistance(radix, 1)));
            }

            Collections.sort(stair1User);
            Collections.sort(stair2User);

            minTime = Math.min(minTime, simulate(stair1User, stair2User));
            stair1User.clear();
            stair2User.clear();
        }
    }

    private static int simulate(List<Man> stair1User, List<Man> stair2User) {
        return Math.max(calcTime(0, stair1User), calcTime(1, stair2User));
    }

    private static int calcTime(int stairNumber, List<Man> users) {
        int time = 0;
        int stairTime = stairs[stairNumber].length;

        for (int i = 0; i < users.size(); i++) {
            if (i - 3 < 0) {
                time = users.get(i).distance + 1 + stairTime;
            } else {
                if (users.get(i - 3).distance + 1 + stairTime <= users.get(i).distance) {
                    time = users.get(i).distance + 1 + stairTime;
                } else {
                    time = users.get(i - 3).distance + 1 + stairTime + stairTime;
                }
            }
        }

        return time;
    }
    
    private static int getDistance(int manIdx, int stairIdx) {
        return Math.abs(mans.get(manIdx).row - stairs[stairIdx].row) + Math.abs(
                mans.get(manIdx).col - stairs[stairIdx].col);
    }

    private static int read(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        return (int) tokenizer.nval;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer tokenizer = new StreamTokenizer(reader);
        StringBuilder builder = new StringBuilder();
        int testCount = read(tokenizer);

        for (int testNumber = 1; testNumber <= testCount; testNumber++) {
            init(tokenizer);

            rushDown();
            builder.append('#').append(testNumber).append(' ').append(minTime).append('\n');
        }
        System.out.println(builder);
    }

    private static class Man extends Pos implements Comparable<Man> {
        int distance;

        public Man(int row, int col, int distance) {
            super(row, col);
            this.distance = distance;
        }

        @Override
        public int compareTo(Man o) {
            return Integer.compare(distance, o.distance);
        }
    }

    private static class Stair extends Pos {
        int length;

        public Stair(int row, int col, int length) {
            super(row, col);
            this.length = length;
        }
    }

    private static class Pos {
        int row, col;

        public Pos(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }


}
