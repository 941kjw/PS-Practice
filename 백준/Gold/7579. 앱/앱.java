import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * 
 * 
 * 최소한의 cost 내에서 특정값 이상의 weight 확보하기.
 *
 */
public class Main {

	private static int appCount;
	private static int memoryNeeded;
	private static AppInfo[] appInfos;

	private static int read(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

	private static void init(StreamTokenizer tokenizer) throws IOException {
		appCount = read(tokenizer);
		memoryNeeded = read(tokenizer);

		appInfos = new AppInfo[appCount];

		for (int idx = 0; idx < appInfos.length; idx++) {
			int memory = read(tokenizer);
			appInfos[idx] = new AppInfo(memory);
		}

		for (int idx = 0; idx < appInfos.length; idx++) {
			int cost = read(tokenizer);
			appInfos[idx].cost = cost;
		}
	}

	private static int findMinCost() {
		int[][] dp = new int[appCount][10001];
		int ans = Integer.MAX_VALUE;

		for (int idx = 0; idx < appInfos.length; ++idx) {
			for (int cost = 0; cost <= 10000; ++cost) {
				if (idx == 0) {
					if (cost >= appInfos[idx].cost) {
						dp[idx][cost] = appInfos[idx].memory;
					}
				}
				else {
					if (cost >= appInfos[idx].cost) {
						dp[idx][cost] = Math.max(dp[idx - 1][cost], dp[idx - 1][cost - appInfos[idx].cost] + appInfos[idx].memory);
					}
					else {
						dp[idx][cost] = dp[idx - 1][cost];
					}
				}

				if (dp[idx][cost] >= memoryNeeded)
					ans = Math.min(ans, cost);

			}
		}

		return ans;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);

		init(tokenizer);
		System.out.println(findMinCost());

	}

	private static class AppInfo {
		int memory, cost;

		public AppInfo(int memory) {
			this.memory = memory;
		}
	}
}
