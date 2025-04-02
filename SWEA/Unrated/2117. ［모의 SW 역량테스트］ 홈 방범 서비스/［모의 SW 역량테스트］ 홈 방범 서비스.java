import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * 
 * 각 집은 M만큼의 비용을 지불할 수 있고, 최대한 많은 집들에 서비스를 제공하면서
 * 동시에 손해를 보지 않아야 한다.
 * 
 * 어떻게 풀까??
 * 매번 쿼리하기에는 너무 많다.
 * 최대 20*20이고, K*K만큼 확인해야 한다...
 * 각 좌표에서 집들과의 거리를 재는 편이 간단.
 * 또한, 도시 밖으로 넘어가도 비용은 동일하게 든다.
 * !! 십자 모양 범위 확인법 !!
 * 직각 거리 < 범위
 *
 */
public class Solution {

	private static int[][] homeMap;
	private static int[] homeRows;
	private static int[] homeCols;

	private static int homeCount;
	private static int homeIncluded;
	private static int profit;
	private static int payable;

	private static void init(StreamTokenizer tokenizer) throws IOException {
		int mapSize = read(tokenizer);
		payable = read(tokenizer);

		homeMap = new int[mapSize][mapSize];
		homeRows = new int[mapSize * mapSize];
		homeCols = new int[mapSize * mapSize];
		homeCount = 0;
		homeIncluded = 0;
		profit = 0;

		for (int row = 0; row < mapSize; row++) {
			for (int col = 0; col < mapSize; col++) {
				int value = read(tokenizer);
				if (value == 1) {
					homeRows[homeCount] = row;
					homeCols[homeCount] = col;
					++homeCount;
					homeMap[row][col] = value;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);
		StringBuilder builder = new StringBuilder();
		int testCount = read(tokenizer);

		for (int testNumber = 1; testNumber <= testCount; ++testNumber) {
			init(tokenizer);
			find();
			builder.append('#').append(testNumber).append(' ').append(homeIncluded).append('\n');
		}
		System.out.println(builder);
	}

	private static void find() {
		for (int row = 0; row < homeMap.length; ++row) {
			for (int col = 0; col < homeMap.length; ++col) {
				for (int range = 1; range < homeMap.length * 1.4; ++range) {
					int counter = 0;
					int tempProfit;

					for (int idx = 0; idx < homeCount; idx++) {
						if (isInRange(homeRows[idx], homeCols[idx], row, col, range))
							++counter;
					}

					tempProfit = (counter * payable) - (range * range + (range - 1) * (range - 1));

					if (tempProfit >= 0 && homeIncluded <= counter) {
						if (homeIncluded < counter) {
							homeIncluded = counter;
							profit = tempProfit;
							continue;
						}
						profit = Math.max(profit, tempProfit);
					}
				}
			}
		}

	}

	private static int read(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

	private static boolean isInRange(int rowCenter, int colCenter, int rowQuery, int colQuery, int range) {
		return (Math.abs(rowCenter - rowQuery) + Math.abs(colCenter - colQuery)) < range;
	}

}
