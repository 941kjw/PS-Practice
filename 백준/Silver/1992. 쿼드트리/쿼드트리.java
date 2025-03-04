import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int arraySize;
    static boolean[][] array;
    static StringBuilder builder;

    static void init(BufferedReader reader) throws IOException {
        StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
        arraySize = Integer.parseInt(tokenizer.nextToken());
        array = new boolean[arraySize][arraySize];
        for (int row = 0; row < arraySize; row++) {
            String line = reader.readLine();
            for (int col = 0; col < arraySize; col++) {
                array[row][col] = line.charAt(col) == '1';
            }
        }
    }

    static void compress(int row, int col, int length) {
        if (length == 0) {
            builder.append(array[row][col] ? '1' : '0');
        }

        boolean isZeroOnly = true;
        boolean isOneOnly = true;

        for (int rowChecker = row; rowChecker < row + length; rowChecker++) {
            for (int colChecker = col; colChecker < col + length; colChecker++) {
                if (array[rowChecker][colChecker]) {
                    isZeroOnly = false;
                } else {
                    isOneOnly = false;
                }

                if (!isOneOnly && !isZeroOnly) {
                    break;
                }
            }
            if (!isOneOnly && !isZeroOnly) {
                break;
            }
        }

        if (isOneOnly) {
            builder.append(1);
        } else if (isZeroOnly) {
            builder.append(0);
        } else {
            builder.append('(');
            compress(row, col, length / 2);
            compress(row, col + length / 2, length / 2);
            compress(row + length / 2, col, length / 2);
            compress(row + length / 2, col + length / 2, length / 2);
            builder.append(')');
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        builder = new StringBuilder();

        init(reader);
        compress(0, 0, arraySize);

        System.out.println(builder);
    }

}
