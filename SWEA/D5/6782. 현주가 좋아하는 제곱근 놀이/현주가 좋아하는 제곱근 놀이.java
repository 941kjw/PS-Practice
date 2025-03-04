import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Solution {

    static long objectNumber;
    static long minControl;

    static void init(StreamTokenizer tokenizer) throws IOException {
        minControl = Integer.MAX_VALUE;
        objectNumber = read(tokenizer);
    }

    static void doRootGame(long currentNumber, long step) {
        if (currentNumber == 2) {
            minControl = Math.min(minControl, step);
            return;
        }

        if (Math.sqrt(currentNumber) % 1 == 0) {
            doRootGame((long) Math.sqrt(currentNumber), step + 1);
        } else {
            long nextPower = (long) Math.sqrt(currentNumber) + 1;
            long difference = (long) Math.pow(nextPower, 2) - currentNumber;
            doRootGame(nextPower, step + difference + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer tokenizer = new StreamTokenizer(reader);
        StringBuilder builder = new StringBuilder();
        int testCount = (int) read(tokenizer);

        for (int testNumber = 1; testNumber <= testCount; testNumber++) {
            init(tokenizer);
            doRootGame(objectNumber, 0);
            builder.append('#').append(testNumber).append(' ').append(minControl).append('\n');
        }
        System.out.println(builder);
    }

    static long read(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        return (long) tokenizer.nval;
    }

}
