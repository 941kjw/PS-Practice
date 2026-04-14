import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;

public class Main {

    private static BufferedReader reader;
    private static BufferedWriter writer;
    private static StreamTokenizer tokenizer;

    public static void main(String[] args) throws IOException {
        initReader();

        int playerCount = getNumber();
        int[] playerCards = new int[playerCount];
        int[] playerScores = new int[playerCount + 1];
        int[] selected = new int[1000001];

        for (int i = 0; i < playerCount; i++) {
            playerCards[i] = getNumber();
            selected[playerCards[i]] = i + 1;
        }

        for (int i = 0; i < playerCount; i++) {
            int start = playerCards[i];
            for (int j = start * 2; j < 1000001; j += start) {
                if (selected[j] > 0) {
                    --playerScores[selected[j]];
                    ++playerScores[selected[start]];
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int i = 1; i <= playerCount; i++) {
            builder.append(playerScores[i]).append(" ");
        }

        writer.write(builder.toString());

        clear();
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
