import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {

	private static int itemCount;
	private static int weightLimit;
	private static int[] dp;

	private static int[] weights;
	private static int[] values;

	private static int read(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

	private static void init(StreamTokenizer tokenizer) throws IOException {
		itemCount = read(tokenizer);
		weightLimit = read(tokenizer);
		dp = new int[weightLimit + 1];

		weights = new int[itemCount];
		values = new int[itemCount];

		for (int idx = 0; idx < itemCount; idx++) {
			weights[idx] = read(tokenizer);
			values[idx] = read(tokenizer);
		}

	}

	private static int findCombination() {

		for (int idx = 0; idx < itemCount; idx++) {
			int weight = weights[idx];
			int value = values[idx];

			for (int curLimit = weightLimit; curLimit >= weight; --curLimit) {
				dp[curLimit] = Math.max(dp[curLimit], dp[curLimit - weight] + value);
			}
		}

		return dp[weightLimit];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);

		init(tokenizer);

		System.out.println(findCombination());

	}
}
