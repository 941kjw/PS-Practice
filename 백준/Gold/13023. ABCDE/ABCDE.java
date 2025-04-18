import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.LinkedList;
import java.util.List;

public class Main {

	private static List<Integer>[] relationList;
	private static boolean[] visited;

	private static int manCount;
	private static int relationCount;

	private static void init(StreamTokenizer tokenizer) throws IOException {
		manCount = read(tokenizer);
		relationCount = read(tokenizer);
		visited = new boolean[manCount];
		relationList = new List[manCount];

		for (int idx = 0; idx < manCount; idx++) {
			relationList[idx] = new LinkedList<>();
		}

		for (int idx = 0; idx < relationCount; idx++) {
			int element1 = read(tokenizer);
			int element2 = read(tokenizer);

			relationList[element1].add(element2);
			relationList[element2].add(element1);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);

		init(tokenizer);

		searchForFive();
	}

	private static void searchForFive() {
		for (int man = 0; man < manCount; man++) {
			visited[man] = true;
			if (dfs(man, 0)) {
				System.out.println(1);
				return;
			}
			visited[man] = false;
		}
		System.out.println(0);
	}
    
	private static boolean dfs(int number, int depth) {
		if (depth == 4)
			return true;

		boolean result = false;
		for (Integer idx : relationList[number]) {
			if (!visited[idx]) {
				visited[idx] = true;
				if (dfs(idx, depth + 1)) {
					result = true;
					break;
				}
				visited[idx] = false;
			}
		}
		return result;
	}

	private static int read(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}
}
