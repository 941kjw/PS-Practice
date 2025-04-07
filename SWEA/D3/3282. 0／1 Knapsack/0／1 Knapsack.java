import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * 
 * DP를 이용한 냅색 문제
 * 
 * 제한된 부피 내에서 가장 가치의 합이 큰 경우를 구해야 한다.
 * 
 * 1.각 물건에 대해서
 * 	1-1.최대 부피에서, 현재 물건의 부피까지
 * 		1-1-1.현재 부피 제한에서 최댓값을 구한다. 이전에 기록했던 최대치와, 현재 물건을 추가할 수 있던 시점의 최댓값에서 물건의 가치만큼 더한 값을 비교한다. 
 *
 */
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
			for (int curWeight = maxVolume; curWeight >= objectInfos[idx].volume; --curWeight) {
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
