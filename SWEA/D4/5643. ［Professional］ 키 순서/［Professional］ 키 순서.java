import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Solution {

	private static int studentNumber;
	private static int compareCount;

	private static boolean[][] students;
	private static boolean[] visited;

	private static void init(StreamTokenizer tokenizer) throws IOException {
		studentNumber = read(tokenizer);
		compareCount = read(tokenizer);

		students = new boolean[studentNumber + 1][studentNumber + 1];
		visited = new boolean[studentNumber + 1];
		for (int count = 0; count < compareCount; count++) {
			int smaller = read(tokenizer);
			int bigger = read(tokenizer);

			students[smaller][bigger] = true;
		}
	}

	private static int find() {
		int counter = 0;
		for (int cur = 1; cur <= studentNumber; cur++) {
			Arrays.fill(visited, false);
			int visitable = dfs(cur, 0, true);
			Arrays.fill(visited, false);
			int reversedVisitable = dfs(cur, 0, false);

			if ((visitable + reversedVisitable) == studentNumber + 1)
				++counter;
		}

		return counter;
	}

	private static int dfs(int cur, int sum, boolean mode) {
		visited[cur] = true;

		int counter = 1;
		for (int idx = 1; idx <= studentNumber; ++idx) {
			if (visited[idx])
				continue;

			if (mode) {
				if (students[cur][idx])
					counter += dfs(idx, sum + 1, mode);
			}
			else {
				if (students[idx][cur])
					counter += dfs(idx, sum + 1, mode);
			}

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
