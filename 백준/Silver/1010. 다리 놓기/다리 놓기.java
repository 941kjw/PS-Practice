import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {

	private static int[][] dp;
	private static int west;
	private static int east;

	private static int read(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

	private static void init(StreamTokenizer tokenizer) throws IOException {
		west = read(tokenizer);
		east = read(tokenizer);
		dp = new int[west + 1][east + 1];
	}

	private static int dp(int maxWest, int maxEast) {

		if (dp[maxWest][maxEast] > 0)
			return dp[maxWest][maxEast];

		if (maxEast == maxWest || maxWest == 0)
			return dp[maxWest][maxEast] = 1;

		return dp[maxWest][maxEast] = dp(maxWest - 1, maxEast - 1) + dp(maxWest, maxEast - 1);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);
		StringBuilder builder = new StringBuilder();
		int testCase = read(tokenizer);

		while (testCase-- > 0) {
			init(tokenizer);
			builder.append(dp(west, east)).append('\n');
		}
		System.out.println(builder);
	}
}
