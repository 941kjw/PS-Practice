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

        int age = getInput();
        int tier = getInput();

        int otakuStat = calcOtaku(age, tier);

        print(otakuStat);

        clear();
    }

    private static void clear() throws IOException {
        reader.close();
        writer.close();
    }

    private static void print(int otakuStat) throws IOException {
        writer.write(Integer.toString(otakuStat));
    }

    private static int calcOtaku(int age , int tier) {
        int result;

        result = 10 + 2 * (25 - age + tier);

        return Math.max(result, 0);
    }

    private static void initReader(){
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new BufferedWriter(new OutputStreamWriter(System.out));
        tokenizer = new StreamTokenizer(reader);
    }

    public static int getInput() throws IOException {
        tokenizer.nextToken();
        return (int) tokenizer.nval;
    }
}
