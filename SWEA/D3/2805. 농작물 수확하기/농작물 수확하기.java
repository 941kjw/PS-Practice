import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Solution {

	static int[][] farm;
	static StringBuilder builder = new StringBuilder();

	static void init(BufferedReader reader) throws IOException {
		int length = Integer.parseInt(reader.readLine());

		farm = new int[length][length];

		for (int row = 0; row < length; row++) {
			String line = reader.readLine();
			for (int col = 0; col < farm.length; col++) {
				farm[row][col] = line.charAt(col) - '0';
			}
		}
	}

	static int harvest() {
		int length = farm.length;
		int sum = 0;
		int amount = -1;
		int step = 2;
		for (int row = 0; row < farm.length; row++) {
			if (row > length / 2)
				step = -2;
			amount += step;
			int startCol = (length / 2) - (amount / 2);
			for (int idx = 0; idx < amount; idx++) {
				sum += farm[row][startCol + idx];
			}
		}

		return sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		int testCount = Integer.parseInt(reader.readLine());

		for (int testNumber = 1; testNumber <= testCount; testNumber++) {
			init(reader);

			builder.append('#').append(testNumber).append(' ').append(harvest()).append('\n');
		}
		System.out.println(builder);
	}
}
