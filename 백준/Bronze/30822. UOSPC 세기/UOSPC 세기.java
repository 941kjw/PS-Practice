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

    private static final int U = 0;
    private static final int O = 1;
    private static final int S = 2;
    private static final int P = 3;
    private static final int C = 4;

    public static void main(String[] args) throws IOException {
        initReader();

        reader.readLine();
        String s = getInput();

        int[] result = countUospc(s);

        int max = calcMax(result);

        print(max);

        clear();
    }

    private static int calcMax(int[] result) {
        int min = 9999999;
        for (int j : result)
            min = Math.min(j, min);
        return min;
    }

    private static int[] countUospc(String s) {
        int[] result = new int[5];

        for(int i = 0; i < s.length(); ++i){
            switch (s.charAt(i)){
                case 'u' :
                    ++result[U];
                    break;
                case 'o' :
                    ++result[O];
                    break;
                case 's' :
                    ++result[S];
                    break;
                case 'p' :
                    ++result[P];
                    break;
                case 'c' :
                    ++result[C];
                    break;
            }
        }

        return result;
    }

    private static void clear() throws IOException {
        reader.close();
        writer.close();
    }

    private static void print(int result) throws IOException {
            writer.write(Integer.toString(result));
    }

    private static void initReader(){
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new BufferedWriter(new OutputStreamWriter(System.out));
        tokenizer = new StreamTokenizer(reader);
    }

    public static String getInput() throws IOException {
        tokenizer.nextToken();
        return tokenizer.sval;
    }
}
