import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {

	private static int[] coins;
	private static int coinCount;
	private static int target;
	private static int combinationCount;
	private static int[] dp;

	private static int read(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

	private static void init(StreamTokenizer tokenizer) throws IOException {
		coinCount = read(tokenizer);
		coins = new int[coinCount];
		combinationCount = 0;

		for (int idx = 0; idx < coins.length; idx++) {
			coins[idx] = read(tokenizer);
		}

		target = read(tokenizer);

		dp = new int[target + 1];

		dp[0] = 1;
	}

	//이전까지의 가짓수 + 현재 동전 1개로 대체하여 쓰는 가짓수
	private static void findCombination() {
		for (int i = 0; i < coinCount; i++) {
			for (int j = coins[i]; j <= target; j++) {
				dp[j] = dp[j] + dp[j - coins[i]];
			}
		}

		combinationCount = dp[target];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);
		StringBuilder builder = new StringBuilder();

		int testCount = read(tokenizer);

		for (int testNumber = 1; testNumber <= testCount; ++testNumber) {
			init(tokenizer);
			findCombination();
			builder.append(combinationCount).append('\n');
		}

		System.out.println(builder);
	}
}
