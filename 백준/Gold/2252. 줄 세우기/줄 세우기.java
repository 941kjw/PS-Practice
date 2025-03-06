import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {

	static int studentCount;
	static int comparisonCount;
	static List <List <Integer>> edges;

	static int[] inflows;

	static void init(StreamTokenizer tokenizer) throws IOException {
		studentCount = read(tokenizer);
		comparisonCount = read(tokenizer);

		inflows = new int[studentCount];
		edges = new ArrayList <>();
		for (int idx = 0; idx < studentCount; idx++) {
			edges.add(new LinkedList <>());
		}

		for (int idx = 0; idx < comparisonCount; idx++) {
			int from = read(tokenizer) - 1;
			int to = read(tokenizer) - 1;

			edges.get(from).add(to);
			inflows[to]++;
		}
	}

	static void makeLine(StringBuilder builder) {
		int count = 0;
		while (count < studentCount) {
			for (int idx = 0; idx < inflows.length; idx++) {
				if (inflows[idx] == 0) {
					inflows[idx]--;
					builder.append(idx + 1).append(' ');
					count++;
					for (int comparedStudent : edges.get(idx)) {
						inflows[comparedStudent]--;
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);
		StringBuilder builder = new StringBuilder();
		init(tokenizer);

		makeLine(builder);

		System.out.println(builder);
	}

	static int read(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

}
