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

        int[] seats = new int[10];

        getInput(seats);

        print(seats);

        clear();
    }

    private static void clear() throws IOException {
        reader.close();
        writer.close();
    }

    private static void print(int[] seats) throws IOException {
        StringBuilder s = new StringBuilder();
        for(int row : seats){
            for(int i = 0 ; i < 20; ++i){
                if((row & (1<<i)) != 0)
                    s.append('o');
                else
                    s.append('.');
            }
            s.append('\n');
        }
        writer.write(s.toString());
    }

    private static void initReader(){
        reader = new BufferedReader(new InputStreamReader(System.in));
        writer = new BufferedWriter(new OutputStreamWriter(System.out));
        tokenizer = new StreamTokenizer(reader);
    }

    public static void getInput(int[] seats) throws IOException {
        int count = getNumber();

        for(int i = 0 ; i < count; ++i){
            String info = getString();

            int row = info.charAt(0) - 'A';
            int col = Integer.parseInt(info.substring(1)) - 1;

            seats[row] |= (1<<col);
        }
    }

    private static String getString() throws IOException{
        tokenizer.nextToken();

        return tokenizer.sval;
    }

    private static int getNumber() throws IOException {
        tokenizer.nextToken();
        return (int) tokenizer.nval;
    }
}
