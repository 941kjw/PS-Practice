import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[][] sums;
	static int[][][] queries;
	static int numberAmount;
	static int questCount;
	static StringBuilder builder = new StringBuilder();

	static void init(BufferedReader reader) throws IOException {
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

		numberAmount = Integer.parseInt(tokenizer.nextToken());
		questCount = Integer.parseInt(tokenizer.nextToken());

		sums = new int[numberAmount + 1][numberAmount + 1];

		for (int row = 1; row <= numberAmount; row++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for (int col = 1; col <= numberAmount; col++)
				sums[row][col] = sums[row][col - 1] + Integer.parseInt(tokenizer.nextToken());
		}
		queries = new int[questCount][2][2];

		for (int idx = 0; idx < questCount; idx++) {
			tokenizer = new StringTokenizer(reader.readLine());

			queries[idx][0][0] = Integer.parseInt(tokenizer.nextToken());
			queries[idx][0][1] = Integer.parseInt(tokenizer.nextToken());
			queries[idx][1][0] = Integer.parseInt(tokenizer.nextToken());
			queries[idx][1][1] = Integer.parseInt(tokenizer.nextToken());
		}
	}

	static void query() {

		for (int idx = 0; idx < questCount; idx++) {
			int sum = 0;
			int currentRow = queries[idx][0][0];

			while (currentRow <= queries[idx][1][0]) {
				sum += sums[currentRow][queries[idx][1][1]] - sums[currentRow][queries[idx][0][1] - 1];
				currentRow++;
			}
			builder.append(sum).append('\n');
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		init(reader);
		query();
		System.out.println(builder);
		reader.close();
	}

}
