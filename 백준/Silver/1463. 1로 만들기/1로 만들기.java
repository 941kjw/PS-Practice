import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {

	private static int[] memo;
	private static int number;

	private static void init(StreamTokenizer tokenizer) throws IOException {
		memo = new int[1000001];
		number = read(tokenizer);
	}

	private static void find() {
		for (int curNumber = 2; curNumber <= number; ++curNumber) {
			memo[curNumber] = memo[curNumber - 1] + 1;

			if (curNumber % 2 == 0)
				memo[curNumber] = Math.min(memo[curNumber], memo[curNumber / 2] + 1);
			if (curNumber % 3 == 0)
				memo[curNumber] = Math.min(memo[curNumber], memo[curNumber / 3] + 1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);

		init(tokenizer);
		find();

		System.out.println(memo[number]);
	}

	private static int read(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}
}