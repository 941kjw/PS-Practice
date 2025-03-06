import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;

public class Solution {

    static int length;
    static int targetTime;
    static int groupCount;

    static int[][] microOrganismMap;
    static List<MicroOrganism> microOrganisms;
    static int microOrganismSum;

    static int[] dx = {0, 0, -1, 1};
    static int[] dy = {-1, 1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer tokenizer = new StreamTokenizer(reader);
        StringBuilder builder = new StringBuilder();

        int testCount = read(tokenizer);

        for (int testNumber = 1; testNumber <= testCount; testNumber++) {
            init(tokenizer);

            simulate();
            builder.append('#').append(testNumber).append(' ').append(microOrganismSum).append('\n');
        }

        System.out.println(builder);
    }

    static void init(StreamTokenizer tokenizer) throws IOException {
        length = read(tokenizer);
        targetTime = read(tokenizer);
        groupCount = read(tokenizer);
        microOrganisms = new ArrayList<>();

        microOrganismMap = new int[length][length];
        microOrganismSum = 0;

        for (int idx = 1; idx <= groupCount; idx++) {
            int row = read(tokenizer);
            int col = read(tokenizer);
            int amount = read(tokenizer);
            int direction = read(tokenizer) - 1;
            microOrganisms.add(new MicroOrganism(row, col, amount, direction));
            microOrganismMap[row][col] = idx;
        }
    }

    static void simulate() {
        for (int time = 0; time < targetTime; time++) {

            for (MicroOrganism microOrganism : microOrganisms) {
                microOrganism.update();
            }

            for (int idx = 0; idx < microOrganisms.size(); idx++) {
                MicroOrganism current = microOrganisms.get(idx);
                if (current.isDead()) {
                    continue;
                }

                int max = current.amount;
                for (int otherIdx = idx + 1; otherIdx < microOrganisms.size(); otherIdx++) {
                    MicroOrganism other = microOrganisms.get(otherIdx);
                    if (other.isDead()) {
                        continue;
                    }

                    if (current.isCollided(other)) {
                        current.amount += other.amount;
                        if (max < other.amount) {
                            current.direction = other.direction;
                            max = other.amount;
                        }

                        other.amount = 0;
                    }
                }
            }

            microOrganisms.removeIf(MicroOrganism::isDead);
        }

        for (MicroOrganism microOrganism : microOrganisms) {
            microOrganismSum += microOrganism.amount;
        }
    }

    static int read(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        return (int) tokenizer.nval;
    }

    static class MicroOrganism {
        int row, col;
        int amount;
        int direction;

        public MicroOrganism(int row, int col, int amount, int direction) {
            this.row = row;
            this.col = col;
            this.amount = amount;
            this.direction = direction;
        }

        void update() {
            if (this.amount == 0) {
                return;
            }

            this.row += dy[direction];
            this.col += dx[direction];

            if (this.row == 0 || this.row == length - 1 || this.col == 0 || this.col == length - 1) {
                this.direction ^= 1;
                this.amount /= 2;
            }
        }

        boolean isCollided(MicroOrganism other) {
            return (this.row == other.row) && (this.col == other.col);
        }

        boolean isDead() {
            return this.amount == 0;
        }
    }
}
