import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {

	private static int maxStudyTime;
	private static int subjectCount;
	private static int[] dp;
	private static int[] importances;
	private static int[] requiredTimes;

	private static int read(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

	private static void init(StreamTokenizer tokenizer) throws IOException {
		maxStudyTime = read(tokenizer);
		subjectCount = read(tokenizer);

		importances = new int[subjectCount];
		requiredTimes = new int[subjectCount];
		dp = new int[maxStudyTime + 1];

		for (int idx = 0; idx < subjectCount; idx++) {
			importances[idx] = read(tokenizer);
			requiredTimes[idx] = read(tokenizer);
		}

	}

	private static int findCombination() {

		for (int idx = 0; idx < subjectCount; ++idx) {
			int importance = importances[idx];
			int time = requiredTimes[idx];

			for (int timeLimit = maxStudyTime; timeLimit >= time; timeLimit--) {
				dp[timeLimit] = Math.max(dp[timeLimit], dp[timeLimit - time] + importance);
			}
		}

		return dp[maxStudyTime];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);

		init(tokenizer);

		System.out.println(findCombination());
	}
}
