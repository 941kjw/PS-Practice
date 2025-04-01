import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Solution {

	private static int[][] honeyComb;
	private static int[][] profitMap;
	private static int honeyCombSize;
	private static int selectableCount;
	private static int maxHoneyAmount;
	private static int maxProfit;

	private static void init(StreamTokenizer tokenizer) throws IOException {

		honeyCombSize = read(tokenizer);
		selectableCount = read(tokenizer);
		maxHoneyAmount = read(tokenizer);

		honeyComb = new int[honeyCombSize][honeyCombSize];
		profitMap = new int[honeyCombSize][honeyCombSize];

		for (int row = 0; row < honeyCombSize; row++) {
			for (int col = 0; col < honeyCombSize; col++) {
				honeyComb[row][col] = read(tokenizer);
			}
		}
	}

	private static void preprocessHoneyPot() {
		for (int row = 0; row < honeyCombSize; row++) {
			for (int col = 0; col < honeyCombSize - selectableCount + 1; col++) {
				int maxProfit = findMaxProfit(row, col);
				profitMap[row][col] = maxProfit;
			}
		}
	}

	private static int findMaxProfit(int row, int col) {
		int maxSum = 0;
		for (int selected = 0; selected < (1 << selectableCount); ++selected) {
			int profitSum = 0;
			int sum = 0;
			for (int idx = 0; idx < selectableCount; idx++) {
				if ((selected & (1 << idx)) != 0) {
					profitSum += honeyComb[row][col + idx] * honeyComb[row][col + idx];
					sum += honeyComb[row][col + idx];
				}
			}

			if (sum > maxHoneyAmount)
				continue;

			maxSum = Math.max(maxSum, profitSum);
		}
		return maxSum;
	}

	private static void chooseTwoHoneyPot() {
		Pos first = findMax();
		int profitFirst = profitMap[first.row][first.col];
		eraseUnselectable(first);
		Pos second = findMax();
		int profitSecond = profitMap[second.row][second.col];

		maxProfit = profitFirst + profitSecond;
	}

	private static void eraseUnselectable(Pos pos) {
		int start = pos.col - selectableCount + 1 < 0 ? 0 : pos.col - selectableCount + 1;
		int end = pos.col + selectableCount > honeyCombSize ? honeyCombSize : pos.col + selectableCount;
		for (int idx = start; idx < end; ++idx) {
			profitMap[pos.row][idx] = 0;
		}
	}

	private static Pos findMax() {
		int maxProfit = 0;
		int maxRow = 0;
		int maxCol = 0;

		for (int row = 0; row < honeyCombSize; row++) {
			for (int col = 0; col < honeyCombSize - selectableCount + 1; col++) {
				if (maxProfit < profitMap[row][col]) {
					maxProfit = profitMap[row][col];
					maxRow = row;
					maxCol = col;
				}
			}
		}

		return new Pos(maxRow, maxCol);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);
		StringBuilder builder = new StringBuilder();
		int testCount = read(tokenizer);

		for (int testNumber = 1; testNumber <= testCount; testNumber++) {
			init(tokenizer);

			preprocessHoneyPot();

			chooseTwoHoneyPot();

			builder.append('#').append(testNumber).append(' ').append(maxProfit).append('\n');
		}
		System.out.println(builder);
	}

	private static int read(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

	private static class Pos {
		int row, col;

		public Pos(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
}
