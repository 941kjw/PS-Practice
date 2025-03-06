import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Solution {

    static int length;
    static int targetTime;
    static int groupCount;

    static MicroOrganism head;
    static MicroOrganism tail;
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
        head = null;
        tail = null;
        microOrganismSum = 0;

        for (int idx = 0; idx < groupCount; ++idx) {
            int row = read(tokenizer);
            int col = read(tokenizer);
            int amount = read(tokenizer);
            int direction = read(tokenizer) - 1;
            if (head == null) {
                head = new MicroOrganism(row, col, amount, direction);
                tail = head;
            } else {
                tail.attach(new MicroOrganism(row, col, amount, direction));
                tail = tail.next;
            }
        }
    }

    static void simulate() {
        for (int time = 0; time < targetTime; ++time) {
            MicroOrganism current = head;

            while (current != null) {
                current.update();
                current = current.next;
            }

            current = head;
            while (current != null) {
                if (current.isDead()) {
                    current = current.next;
                    continue;
                }

                int max = current.amount;

                MicroOrganism other = current.next;

                while (other != null) {
                    if (other.isDead()) {
                        other = other.next;
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
                    other = other.next;
                }
                current = current.next;
            }

            current = head;
            while (current != null) {
                MicroOrganism next = current.next;
                if (current.isDead()) {
                    current.removeSelf();
                }
                current = next;
            }
        }

        MicroOrganism iterator = head;
        while (iterator != null) {
            microOrganismSum += iterator.amount;
            iterator = iterator.next;
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

        MicroOrganism before;
        MicroOrganism next;

        public MicroOrganism(int row, int col, int amount, int direction) {
            this.row = row;
            this.col = col;
            this.amount = amount;
            this.direction = direction;
            this.before = null;
            this.next = null;
        }

        void attach(MicroOrganism next) {
            this.next = next;
            next.before = this;
        }

        void update() {
            if (isDead()) {
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

        void removeSelf() {
            if (this != head) {
                this.before.next = this.next;
            } else {
                this.next.before = null;
                head = next;
            }

            if (this != tail) {
                this.next.before = this.before;
            } else {
                this.before.next = null;
                tail = this.before;
            }
        }
    }
}
