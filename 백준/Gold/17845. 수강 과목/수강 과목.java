import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {

	private static int maxStudyTime;
	private static int subjectCount;
	private static int[][] dp;
	private static Subject[] subjects;

	private static int read(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

	private static void init(StreamTokenizer tokenizer) throws IOException {
		maxStudyTime = read(tokenizer);
		subjectCount = read(tokenizer);

		subjects = new Subject[subjectCount + 1];
		dp = new int[maxStudyTime + 1][subjectCount + 1];

		for (int idx = 1; idx <= subjectCount; idx++) {
			int importance = read(tokenizer);
			int timeRequired = read(tokenizer);
			subjects[idx] = new Subject(importance, timeRequired);
		}

	}

	private static int findCombination() {
		for (int timeLimit = 1; timeLimit <= maxStudyTime; ++timeLimit) {
			for (int idx = 1; idx <= subjectCount; ++idx) {
				if (timeLimit >= subjects[idx].timeRequired)
					dp[timeLimit][idx] = Math.max(dp[timeLimit][idx - 1], dp[timeLimit - subjects[idx].timeRequired][idx - 1] + subjects[idx].importance);
				else
					dp[timeLimit][idx] = dp[timeLimit][idx - 1];
			}
		}

		return dp[maxStudyTime][subjectCount];
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
