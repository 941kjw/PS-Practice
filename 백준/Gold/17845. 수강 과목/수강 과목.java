import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {

	private static int maxStudyTime;
	private static int subjectCount;
	private static int[] dp;
	private static Subject[] subjects;

	private static int read(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

	private static void init(StreamTokenizer tokenizer) throws IOException {
		maxStudyTime = read(tokenizer);
		subjectCount = read(tokenizer);

		subjects = new Subject[subjectCount + 1];
		dp = new int[maxStudyTime + 1];

		for (int idx = 1; idx <= subjectCount; idx++) {
			int importance = read(tokenizer);
			int timeRequired = read(tokenizer);
			subjects[idx] = new Subject(importance, timeRequired);
		}

	}

	private static int findCombination() {

		for (int idx = 1; idx <= subjectCount; ++idx) {
			int imp = subjects[idx].importance;
			int time = subjects[idx].timeRequired;

			for (int t = maxStudyTime; t >= time; t--) {
				dp[t] = Math.max(dp[t], dp[t - time] + imp);
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

	private static class Subject {
		int importance, timeRequired;

		public Subject(int importance, int timeRequired) {
			this.importance = importance;
			this.timeRequired = timeRequired;
		}
	}
}
