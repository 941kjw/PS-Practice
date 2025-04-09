import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Solution {

	private static int[][] distance;
	private static int[] sum;

	private static final int MAX = 100000;

	private static int length;

	private static void init(StreamTokenizer tokenizer) throws IOException {
		length = read(tokenizer);

		distance = new int[length][length];
		sum = new int[length];

		for (int row = 0; row < length; row++) {
			for (int col = 0; col < length; col++) {
				int value = read(tokenizer);

				if (row == col)
					distance[row][col] = 0;
				else if (value == 0)
					distance[row][col] = MAX;
				else if (value == 1)
					distance[row][col] = 1;
			}
		}
	}

	private static int floyd() {

		for (int passPoint = 0; passPoint < length; passPoint++) {
			for (int from = 0; from < length; from++) {
				for (int to = 0; to < length; to++) {
					distance[from][to] = Math.min(distance[from][to], distance[from][passPoint] + distance[passPoint][to]);
				}
			}
		}

		int min = MAX;
		for (int idx = 0; idx < length; idx++) {
			for (int to = 0; to < length; to++) {
				sum[idx] += distance[idx][to];
			}
			min = Math.min(min, sum[idx]);
		}

		return min;
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

		for (int testNumber = 1; testNumber <= testCount; ++testNumber) {
			init(tokenizer);
			builder.append('#').append(testNumber).append(' ').append(floyd()).append('\n');
		}

		System.out.println(builder);
	}

}
