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

        int doorCount = getInput();
        int firstOpenedDirection = getInput();

        if(doorCount >= 6){
            printFail();
            clear();
            return;
        }

        print(doorCount, firstOpenedDirection);

        clear();
    }

    private static void printFail() throws IOException {
        writer.write("Love is open door");
    }

    private static void clear() throws IOException {
        reader.close();
        writer.close();
    }

    private static void print(int doorCount, int firstOpenedDirection) throws IOException {
        int direction = firstOpenedDirection;
        for(int i = 0; i < doorCount - 1; ++i){
            direction ^= 1;
            writer.write(Integer.toString(direction) + '\n');
        }
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
