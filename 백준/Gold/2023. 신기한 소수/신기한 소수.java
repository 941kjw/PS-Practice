import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    static int radix;
    static int[] base = {2, 3, 5, 7};
    static int[] element = {1, 3, 7, 9};
    static StringBuilder builder;

    static boolean isPrime(int n) {
        if (n == 1) {
            return false;
        }
        if (n <= 3) {
            return true;
        }
        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    static void combination(int selectedIdx, int current) {
        if (selectedIdx == radix) {
            System.out.println(current);
            return;
        }

        for (int num : element) {
            int currentNumber = current * 10 + num;
            if (isPrime(currentNumber)) {
                combination(selectedIdx + 1, currentNumber);
            }
        }
    }

    static void init(BufferedReader reader) throws IOException {
        radix = Integer.parseInt(reader.readLine());
        builder = new StringBuilder();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        init(reader);

        for (int idx = 0; idx < 4; idx++) {
            combination(1, base[idx]);
        }

//        writer.write(builder.toString());
//        writer.close();
    }
}
