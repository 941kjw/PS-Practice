import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Solution {

	static int clientCount;

	static int[][] clients;

	static int[] start;
	static int[] end;

	static boolean[] visited;
	static int minimum;

	static void init(StreamTokenizer tokenizer) throws IOException {
		clientCount = read(tokenizer);

		minimum = Integer.MAX_VALUE;
		clients = new int[clientCount][2];
		start = new int[2];
		end = new int[2];

		start[1] = read(tokenizer);
		start[0] = read(tokenizer);

		end[1] = read(tokenizer);
		end[0] = read(tokenizer);

		visited = new boolean[clientCount];

		for (int idx = 0; idx < clientCount; idx++) {
			clients[idx][1] = read(tokenizer);
			clients[idx][0] = read(tokenizer);
		}
	}

	static void dfs(int[] pos, int count, int distanceSum) {
		if (distanceSum >= minimum)
			return;

		if (count == clientCount) {
			distanceSum += getDistance(pos, end);
			minimum = Math.min(minimum, distanceSum);
			return;
		}

		for (int idx = 0; idx < clientCount; idx++) {
			if (visited[idx])
				continue;

			visited[idx] = true;
			dfs(clients[idx], count + 1, distanceSum + getDistance(pos, clients[idx]));
			visited[idx] = false;
		}
	}

	static int getDistance(int[] from, int[] to) {
		return Math.abs(from[0] - to[0]) + Math.abs(from[1] - to[1]);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);
		StringBuilder builder = new StringBuilder();
		int testCount = read(tokenizer);

		for (int testNumber = 1; testNumber <= testCount; testNumber++) {
			init(tokenizer);

			dfs(start, 0, 0);
			builder.append('#').append(testNumber).append(' ').append(minimum).append('\n');
		}

		System.out.println(builder);
	}

	static int read(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}
}
