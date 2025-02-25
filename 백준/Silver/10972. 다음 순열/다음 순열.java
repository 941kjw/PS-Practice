import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int[] numbers;
	static StringBuilder builder = new StringBuilder();

	static void init(BufferedReader reader) throws IOException {

		int numberCount = Integer.parseInt(reader.readLine());

		numbers = new int[numberCount];
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
		for (int idx = 0; idx < numbers.length; idx++) {
			numbers[idx] = Integer.parseInt(tokenizer.nextToken());
		}
	}

	static void getNextPermutation() {
		int maxIdx = numbers.length - 1;

		while (maxIdx > 0 && numbers[maxIdx - 1] >= numbers[maxIdx]) {
			maxIdx--;
		}

		if (maxIdx == 0) {
			builder.append(-1);
			return;
		}

		int unsortedIdx = maxIdx - 1;
		int largerNumIdxInSortedArea = numbers.length - 1;

		while (numbers[unsortedIdx] >= numbers[largerNumIdxInSortedArea]) {
			largerNumIdxInSortedArea--;
		}

		swapNumbers(unsortedIdx, largerNumIdxInSortedArea);

		int currentIdx = numbers.length - 1;
		while (maxIdx < currentIdx) {
			swapNumbers(maxIdx, currentIdx);
			maxIdx++;
			currentIdx--;
		}

		for (int number : numbers) {
			builder.append(number).append(' ');
		}
	}

	static void swapNumbers(int from, int to) {
		int temp = numbers[to];
		numbers[to] = numbers[from];
		numbers[from] = temp;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		init(reader);

		getNextPermutation();

		System.out.println(builder);
	}
}
