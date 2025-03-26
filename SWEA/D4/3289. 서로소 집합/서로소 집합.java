import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Solution {

	private static int[] parents;
	private static int[] ranks;
	private static int calcCount;
	private static StringBuilder builder;

	private static void make() {
		for (int idx = 0; idx < parents.length; idx++) {
			parents[idx] = idx;
		}
	}

	private static int find(int element) {
		if (parents[element] == element) {
			return element;
		}

		return parents[element] = find(parents[element]);
	}

	private static void union(int element1, int element2) {
		int parentOfElement1 = find(element1);
		int parentOfElement2 = find(element2);

		if (parentOfElement1 == parentOfElement2)
			return;

		if (ranks[parentOfElement1] > ranks[parentOfElement2]) {
			parents[parentOfElement2] = parentOfElement1;
			return;
		}

		if (ranks[parentOfElement1] == ranks[parentOfElement2]) {
			++ranks[parentOfElement2];
		}

		parents[parentOfElement1] = parentOfElement2;

	}

	private static void query(int element1, int element2) {
		if (find(element1) == find(element2)) {
			builder.append(1);
			return;
		}
		builder.append(0);
	}

	private static void doCalculation(StreamTokenizer tokenizer) throws IOException {
		for (int count = 0; count < calcCount; count++) {
			int command = read(tokenizer);

			int element1 = read(tokenizer);
			int element2 = read(tokenizer);

			if (command == 0)
				union(element1, element2);
			else
				query(element1, element2);
		}
	}

	private static void init(StreamTokenizer tokenizer) throws IOException {
		int numberCount = read(tokenizer);
		calcCount = read(tokenizer);

		parents = new int[numberCount + 1];
		ranks = new int[numberCount + 1];

		make();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);
		builder = new StringBuilder();

		int testCount = read(tokenizer);

		for (int testNumber = 1; testNumber <= testCount; testNumber++) {
			init(tokenizer);
			builder.append('#').append(testNumber).append(' ');
			doCalculation(tokenizer);
			builder.append('\n');
		}
		System.out.println(builder);

	}

	private static int read(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}
}
