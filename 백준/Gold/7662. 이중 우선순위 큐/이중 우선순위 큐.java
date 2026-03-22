import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.StreamTokenizer;
import java.util.TreeMap;

public class Main {

    private static BufferedReader reader;
    private static BufferedWriter writer;
    private static StreamTokenizer tokenizer;

    public static void main(String[] args) throws IOException {
        initReader();

        int testCount = getNumber();
        for (int i = 0; i < testCount; ++i) {
            simulate();
        }
        clear();
    }

    private static void simulate() throws IOException {
        TreeMap<Integer, Integer> map = new TreeMap<>();
        int commandCount = getNumber();
        for (int i = 0; i < commandCount; ++i) {
            String command = getString();
            int number = getNumber();
            if (command.equals("I")) {
                map.merge(number, 1, Integer::sum);
            } else if (command.equals("D")) {
                if (map.isEmpty()) {
                    continue;
                }
                if (number == 1) {
                    // 최대값 삭제
                    int max = map.lastKey();
                    if (map.get(max) == 1) {
                        map.pollLastEntry();
                    } else {
                        map.put(max, map.get(max) - 1);
                    }
                } else {
                    // 최소값 삭제
                    int min = map.firstKey();
                    if (map.get(min) == 1) {
                        map.pollFirstEntry();
                    } else {
                        map.put(min, map.get(min) - 1);
                    }
                }
            }
        }
        if (map.isEmpty()) {
            writer.write("EMPTY\n");
        } else {
            writer.write(map.lastKey() + " " + map.firstKey() + "\n");
        }
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
