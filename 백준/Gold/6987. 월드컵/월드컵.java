import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {

    static int[] home = {0, 0, 0, 0, 0, 1, 1, 1, 1, 2, 2, 2, 3, 3, 4};
    static int[] away = {1, 2, 3, 4, 5, 2, 3, 4, 5, 3, 4, 5, 4, 5, 5};

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

    static boolean validate(int gameIdx) {
        if (gameIdx == 15) {
            return true;
        }

        if (score[home[gameIdx]][0] > 0 && score[away[gameIdx]][2] > 0) {
            score[home[gameIdx]][0]--;
            score[away[gameIdx]][2]--;
            if (validate(gameIdx + 1)) {
                return true;
            }
            score[home[gameIdx]][0]++;
            score[away[gameIdx]][2]++;
        }

        if (score[home[gameIdx]][2] > 0 && score[away[gameIdx]][0] > 0) {
            score[home[gameIdx]][2]--;
            score[away[gameIdx]][0]--;
            if (validate(gameIdx + 1)) {
                return true;
            }
            score[home[gameIdx]][2]++;
            score[away[gameIdx]][0]++;
        }

        if (score[home[gameIdx]][1] > 0 && score[away[gameIdx]][1] > 0) {
            score[home[gameIdx]][1]--;
            score[away[gameIdx]][1]--;
            if (validate(gameIdx + 1)) {
                return true;
            }
            score[home[gameIdx]][1]++;
            score[away[gameIdx]][1]++;
        }

        return false;
    }

    public static void main(String[] args) throws IOException {

        for (int idx = 0; idx < 4; idx++) {
            init();

            if (isInvalidInput) {
                builder.append(0).append(' ');
            } else {
                builder.append(validate(0) ? 1 : 0).append(' ');
            }
        }
        System.out.println(builder);
    }

    static int read() throws IOException {
        tokenizer.nextToken();
        return (int) tokenizer.nval;
    }
}
