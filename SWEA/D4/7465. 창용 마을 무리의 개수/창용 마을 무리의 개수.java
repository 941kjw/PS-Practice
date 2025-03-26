import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Solution {

	private static int[] group;
	private static int[] rank;

	static int relationCount;

	private static void make() {
		for (int idx = 0; idx < group.length; idx++) {
			group[idx] = idx;
		}
	}

	private static int find(int element) {
		if (group[element] == element)
			return element;

		return group[element] = find(group[element]);
	}

	private static void union(int element1, int element2) {
		int groupLeaderOfElement1 = find(element1);
		int groupLeaderOfElement2 = find(element2);

		if (groupLeaderOfElement1 == groupLeaderOfElement2)
			return;

		if (rank[groupLeaderOfElement1] > rank[groupLeaderOfElement2]) {
			group[groupLeaderOfElement2] = groupLeaderOfElement1;
			return;
		}

		if (rank[groupLeaderOfElement1] == rank[groupLeaderOfElement2])
			++rank[groupLeaderOfElement2];

		group[groupLeaderOfElement1] = groupLeaderOfElement2;
	}

	private static void init(StreamTokenizer tokenizer) throws IOException {
		int manCount = read(tokenizer);
		relationCount = read(tokenizer);

		group = new int[manCount + 1];
		rank = new int[manCount + 1];

		make();
	}

	private static void makeGroup(StreamTokenizer tokenizer) throws IOException {
		for (int idx = 0; idx < relationCount; ++idx) {
			int element1 = read(tokenizer);
			int element2 = read(tokenizer);

			union(element1, element2);
		}
	}

	private static int countGroup() {
		int count = 0;
		boolean[] heads = new boolean[group.length];

		for (int idx = 1; idx < heads.length; ++idx) {
			int head = find(idx);
			heads[head] = true;
		}

		for (boolean b : heads) {
			if (b)
				++count;
		}

		return count;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);
		StringBuilder builder = new StringBuilder();
		int testCount = read(tokenizer);

		for (int testNumber = 1; testNumber <= testCount; testNumber++) {
			init(tokenizer);
			makeGroup(tokenizer);
			int groupCount = countGroup();
			builder.append('#').append(testNumber).append(' ').append(groupCount).append('\n');
		}
		System.out.println(builder);
	}

	private static int read(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

}
