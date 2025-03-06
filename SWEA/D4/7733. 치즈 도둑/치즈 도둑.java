import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {

	static int[][] cheese;
	static int max;
	static int maxDay;
	static int length;

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	static void init(StreamTokenizer tokenizer) throws IOException {
		length = read(tokenizer);
		cheese = new int[length][length];
		max = 1;
		maxDay = 0;
		for (int row = 0; row < length; row++) {
			for (int col = 0; col < cheese.length; col++) {
				cheese[row][col] = read(tokenizer);
				maxDay = Math.max(maxDay, cheese[row][col]);
			}
		}
	}

	static void simulateFairy() {
		Queue <Pos> queue = new ArrayDeque <>();
		for (int day = 1; day <= maxDay; day++) {
			boolean[][] visited = new boolean[length][length];

			magicHappens(visited, day);

			int currentPieces = countPieces(visited, queue);
			max = Math.max(max, currentPieces);
		}
	}

	static void magicHappens(boolean[][] visited, int day) {
		for (int row = 0; row < length; row++) {
			for (int col = 0; col < length; col++) {
				if (cheese[row][col] <= day)
					visited[row][col] = true;
			}
		}
	}

	static int countPieces(boolean[][] visited, Queue <Pos> queue) {
		int count = 0;
		for (int row = 0; row < length; row++) {
			for (int col = 0; col < length; col++) {
				if (!visited[row][col]) {
					count++;
					markPiece(row, col, visited, queue);
				}
			}
		}

		return count;
	}

	static void markPiece(int row, int col, boolean[][] visited, Queue <Pos> queue) {

		visited[row][col] = true;
		queue.add(new Pos(row, col));

		while (!queue.isEmpty()) {
			Pos current = queue.poll();

			visited[current.row][current.col] = true;

			for (int dir = 0; dir < 4; dir++) {
				int nrow = current.row + dy[dir];
				int ncol = current.col + dx[dir];

				if (nrow < 0 || nrow >= length || ncol < 0 || ncol >= length || visited[nrow][ncol])
					continue;

				visited[nrow][ncol] = true;
				queue.add(new Pos(nrow, ncol));
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);
		StringBuilder builder = new StringBuilder();
		int testCount = Integer.parseInt(reader.readLine());

		for (int testNumber = 1; testNumber <= testCount; testNumber++) {
			init(tokenizer);
			simulateFairy();
			builder.append('#').append(testNumber).append(' ').append(max).append('\n');
		}
		System.out.println(builder);
	}

	static int read(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

	static class Pos {
		int row, col;

		public Pos(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}

}
