import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution {

	private static int studentNumber;
	private static int compareCount;

	private static List<Integer>[] students;
	private static List<Integer>[] reverse;

	private static boolean[] visited;

	private static void init(StreamTokenizer tokenizer) throws IOException {
		studentNumber = read(tokenizer);
		compareCount = read(tokenizer);

		students = new List[studentNumber + 1];
		reverse = new List[studentNumber + 1];
		visited = new boolean[studentNumber + 1];
		for (int count = 0; count < compareCount; count++) {
			int smaller = read(tokenizer);
			int bigger = read(tokenizer);

			if (students[smaller] == null)
				students[smaller] = new ArrayList<>();
			students[smaller].add(bigger);

			if (reverse[bigger] == null)
				reverse[bigger] = new ArrayList<>();

			reverse[bigger].add(smaller);
		}
	}

	private static int find() {
		int counter = 0;
		for (int cur = 1; cur <= studentNumber; cur++) {
			Arrays.fill(visited, false);
			int visitable = dfs(cur, 0, true);
			Arrays.fill(visited, false);
			int reversedVisitable = dfs(cur, 0, false);
			//			System.out.println(visitable + reversedVisitable);
			if ((visitable + reversedVisitable) == studentNumber + 1)
				++counter;
		}

		return counter;
	}

	private static int dfs(int cur, int sum, boolean mode) {
		visited[cur] = true;

		int counter = 1;

		if (mode && students[cur] != null) {
			for (int next : students[cur])
				if (!visited[next])
					counter += dfs(next, sum + 1, mode);
		}

		else if (!mode && reverse[cur] != null) {
			for (int next : reverse[cur])
				if (!visited[next])
					counter += dfs(next, sum + 1, mode);
		}

		return counter;
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

		for (int testNumber = 1; testNumber <= testCount; testNumber++) {
			init(tokenizer);
			builder.append('#').append(testNumber).append(' ').append(find()).append('\n');
		}

		System.out.println(builder);
	}

}
