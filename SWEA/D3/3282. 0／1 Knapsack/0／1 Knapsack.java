import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Solution {

	private static ObjectInfo[] objectInfos;

	private static int objectCount;
	private static int maxVolume;

	private static void init(StreamTokenizer tokenizer) throws IOException {
		objectCount = read(tokenizer);
		maxVolume = read(tokenizer);

		objectInfos = new ObjectInfo[objectCount];

		for (int count = 0; count < objectCount; count++) {
			int volume = read(tokenizer);
			int value = read(tokenizer);

			objectInfos[count] = new ObjectInfo(volume, value);
		}
	}

	private static int findMaxValue() {

		int[] dp = new int[maxVolume + 1];

		for (int idx = 0; idx < objectInfos.length; ++idx) {
			for (int curWeight = maxVolume; curWeight > 0; --curWeight) {

				if (curWeight - objectInfos[idx].volume < 0)
					continue;

				dp[curWeight] = Math.max(dp[curWeight], dp[curWeight - objectInfos[idx].volume] + objectInfos[idx].value);
			}
		}

		return dp[maxVolume];
	}

	private static int read(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);
		StringBuilder builder = new StringBuilder();
		int testCount = read(tokenizer);

		for (int testNumber = 1; testNumber <= testCount; testNumber++) {
			init(tokenizer);
			builder.append('#').append(testNumber).append(' ').append(findMaxValue()).append('\n');
		}

		System.out.println(builder);
	}

	private static class ObjectInfo {
		int volume, value;

		public ObjectInfo(int volume, int value) {
			this.volume = volume;
			this.value = value;
		}
	}
}
