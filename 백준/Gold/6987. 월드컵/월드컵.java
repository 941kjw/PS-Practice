import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {
    static int[][] score = new int[6][3];

    static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static StreamTokenizer tokenizer = new StreamTokenizer(reader);
    static StringBuilder builder = new StringBuilder();
    static boolean isInvalidInput;

    static void init() throws IOException {
        int matchCountSum = 0;
        isInvalidInput = false;
        for (int teamNumber = 0; teamNumber < 6; teamNumber++) {
            for (int idx = 0; idx < 3; idx++) {
                score[teamNumber][idx] = read();
                matchCountSum += score[teamNumber][idx];
            }
        }

        if (matchCountSum > 30) {
            isInvalidInput = true;
        }
    }

    static boolean validate(int teamNumber, int opponentNumber) {
        if (teamNumber == 5) {
            return true;
        }

        if (opponentNumber == 6) {
            return validate(teamNumber + 1, teamNumber + 2);
        }

        if (score[teamNumber][0] > 0 && score[opponentNumber][2] > 0) {
            score[teamNumber][0]--;
            score[opponentNumber][2]--;
            if (validate(teamNumber, opponentNumber + 1)) {
                return true;
            }
            score[teamNumber][0]++;
            score[opponentNumber][2]++;
        }

        if (score[teamNumber][2] > 0 && score[opponentNumber][0] > 0) {
            score[teamNumber][2]--;
            score[opponentNumber][0]--;
            if (validate(teamNumber, opponentNumber + 1)) {
                return true;
            }
            score[teamNumber][2]++;
            score[opponentNumber][0]++;
        }

        if (score[teamNumber][1] > 0 && score[opponentNumber][1] > 0) {
            score[teamNumber][1]--;
            score[opponentNumber][1]--;
            if (validate(teamNumber, opponentNumber + 1)) {
                return true;
            }
            score[teamNumber][1]++;
            score[opponentNumber][1]++;
        }

        return false;
    }

    public static void main(String[] args) throws IOException {

        for (int idx = 0; idx < 4; idx++) {
            init();

            if (isInvalidInput) {
                builder.append(0).append(' ');
            } else {
                builder.append(validate(0, 1) ? 1 : 0).append(' ');
            }
        }
        System.out.println(builder);
    }

    static int read() throws IOException {
        tokenizer.nextToken();
        return (int) tokenizer.nval;
    }
}
