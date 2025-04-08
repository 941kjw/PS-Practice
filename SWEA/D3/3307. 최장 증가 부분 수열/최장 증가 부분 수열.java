import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Solution {

	private static int[] numbers;

	private static void init(StreamTokenizer tokenizer) throws IOException {
		int length = read(tokenizer);
		numbers = new int[length];

		for (int idx = 0; idx < numbers.length; idx++) {
			numbers[idx] = read(tokenizer);
		}
	}

	private static int read(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

	private static int getLisLength() {
		int[] subsequence = new int[numbers.length];
		int length = 0;

		for (int number : numbers) {
			int pos = Arrays.binarySearch(subsequence, 0, length, number);
			if (pos < 0)
				pos = -(pos + 1);

			subsequence[pos] = number;

			if (pos == length)
				++length;
		}
		return length;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);
		StringBuilder builder = new StringBuilder();
		int testCount = read(tokenizer);

		for (int testNumber = 1; testNumber <= testCount; ++testNumber) {
			init(tokenizer);
			builder.append('#').append(testNumber).append(' ').append(getLisLength()).append('\n');
		}

		System.out.println(builder);
	}

}
