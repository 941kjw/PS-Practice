import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	static int length;
	static char[][] painting;
	static char[][] blinded;

	static int normalCount;
	static int blindCount;

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	static void init(StreamTokenizer tokenizer) throws IOException {
		length = readInt(tokenizer);

		painting = new char[length][length];
		blinded = new char[length][length];

		normalCount = 0;
		blindCount = 0;

		for (int row = 0; row < length; row++) {
			String line = readLine(tokenizer);

			for (int col = 0; col < length; col++) {
				char color = line.charAt(col);
				painting[row][col] = color;
				blinded[row][col] = (color == 'G' ? 'R' : color);
			}
		}
	}

	static void countRegion() {
		Queue <Pos> queue = new ArrayDeque <>();
		countNormal(queue);
		countBlind(queue);
	}

	static void countNormal(Queue <Pos> queue) {
		for (int row = 0; row < length; row++) {
			for (int col = 0; col < length; col++) {
				if (painting[row][col] != 'X') {
					bfs(row, col, queue, painting);
					normalCount++;
				}
			}
		}
	}

	static void countBlind(Queue <Pos> queue) {
		for (int row = 0; row < length; row++) {
			for (int col = 0; col < length; col++) {
				if (blinded[row][col] != 'X') {
					bfs(row, col, queue, blinded);
					blindCount++;
				}
			}
		}
	}

	static void bfs(int row, int col, Queue <Pos> queue, char[][] map) {
		char colorOfKind = map[row][col];
		queue.add(new Pos(row, col));
		map[row][col] = 'X';

		while (!queue.isEmpty()) {
			Pos current = queue.poll();

			for (int dir = 0; dir < 4; dir++) {
				int nrow = current.row + dy[dir];
				int ncol = current.col + dx[dir];

				if (nrow < 0 || nrow >= length || ncol < 0 || ncol >= length || map[nrow][ncol] != colorOfKind)
					continue;

				map[nrow][ncol] = 'X';
				queue.add(new Pos(nrow, ncol));
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);

		init(tokenizer);

		countRegion();

		System.out.println(normalCount + " " + blindCount);

	}

	static int readInt(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

	static String readLine(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return tokenizer.sval;
	}

	static class Pos {
		int row, col;

		public Pos(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
}
