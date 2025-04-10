import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main {

	private static int brickCount;
	private static int height;
	private static int resultIdx;

	private static Brick[] bricks;
	private static int[] result;
	private static int[] dp;

	private static int read(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

	private static void init(StreamTokenizer tokenizer) throws IOException {
		brickCount = read(tokenizer);
		bricks = new Brick[brickCount + 1];
		dp = new int[brickCount + 1];
		height = 0;
		resultIdx = 0;
		result = new int[brickCount];
		bricks[0] = new Brick(0, 0, 0, 0);
		for (int idx = 1; idx <= brickCount; idx++) {
			int extent = read(tokenizer);
			int height = read(tokenizer);
			int weight = read(tokenizer);

			bricks[idx] = new Brick(extent, height, weight, idx);
		}

		Arrays.sort(bricks);
	}

	private static void findCombination() {
		for (int idx = 1; idx <= brickCount; ++idx) {
			for (int subIdx = 0; subIdx < idx; ++subIdx) {
				if (bricks[idx].weight > bricks[subIdx].weight)
					dp[idx] = Math.max(dp[idx], dp[subIdx] + bricks[idx].height);
			}
			height = Math.max(height, dp[idx]);
		}
		int curHeight = height;
		for (int idx = brickCount; idx > 0; --idx) {
			if (dp[idx] == curHeight) {
				result[resultIdx++] = bricks[idx].number;
				curHeight -= bricks[idx].height;
			}
		}

		int[] temp = new int[resultIdx];

		for (int idx = 0; idx < resultIdx; idx++) {
			temp[idx] = result[resultIdx - idx - 1];
		}

		result = temp;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);

		init(tokenizer);
		findCombination();
		System.out.println(resultIdx);
		for (int brickNumber : result) {
			System.out.println(brickNumber);
		}

	}

	private static class Brick implements Comparable<Brick> {
		int extent, height, weight, number;

		public Brick(int extent, int height, int weight, int number) {
			this.extent = extent;
			this.height = height;
			this.weight = weight;
			this.number = number;
		}

		@Override
		public int compareTo(Brick o) {
			return Integer.compare(extent, o.extent);
		}
	}
}
