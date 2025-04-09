import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 
 * BFS 탐색을 하자.
 * 열쇠가 있어야 문을 열 수 있다.
 * 열쇠가 아예 없거나, 접근 불가능할 수도 있음.
 * 열쇠는 여러 번 쓸 수 있다.
 * 
 *
 */
public class Main {

	private static int[] dy = { -1, 1, 0, 0 };
	private static int[] dx = { 0, 0, -1, 1 };

	private static int height;
	private static int width;
	private static char[][] map;
	private static boolean[][][] visited;
	private static Pos start;

	private static void init(StreamTokenizer tokenizer) throws IOException {
		height = read(tokenizer);
		width = read(tokenizer);

		map = new char[height][width];
		visited = new boolean[height][width][64];

		for (int row = 0; row < height; row++) {
			String line = readLine(tokenizer);
			for (int col = 0; col < width; col++) {
				char c = line.charAt(col);
				map[row][col] = c;

				if (c == '0')
					start = new Pos(row, col, 0, 0);
			}
		}
	}

	private static void bfs() {
		Queue<Pos> queue = new ArrayDeque<>();
		queue.add(start);
		visited[start.row][start.col][start.keys] = true;

		while (!queue.isEmpty()) {
			Pos cur = queue.poll();

			int row = cur.row;
			int col = cur.col;
			int steps = cur.steps;
			int keys = cur.keys;

			if (map[row][col] == '1') {
				System.out.println(steps);
				return;
			}

			if (map[row][col] >= 'a' && map[row][col] <= 'z') {
				keys |= (1 << (map[row][col] - 'a'));
			}

			for (int i = 0; i < 4; i++) {
				int nrow = row + dy[i];
				int ncol = col + dx[i];

				if (nrow < 0 || nrow >= height || ncol < 0 || ncol >= width || visited[nrow][ncol][keys])
					continue;

				if (map[nrow][ncol] != '#' && isOpenable(map[nrow][ncol], keys)) {
					visited[nrow][ncol][keys] = true;
					queue.add(new Pos(nrow, ncol, cur.steps + 1, keys));
				}
			}
		}
		System.out.println(-1);
	}

	private static boolean isOpenable(char door, int keys) {
		if (door < 'A' || door > 'Z')
			return true;
		return (keys & (1 << door - 'A')) != 0;
	}

	private static int read(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return Integer.parseInt(tokenizer.sval);
	}

	private static String readLine(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return tokenizer.sval;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);

		tokenizer.resetSyntax();
		tokenizer.wordChars(0x23, 0xFF);
		tokenizer.whitespaceChars(0x00, 0x20);

		init(tokenizer);
		bfs();
	}

	private static class Pos {
		int row, col, steps, keys;

		public Pos(int row, int col, int steps, int keys) {
			this.row = row;
			this.col = col;
			this.steps = steps;
			this.keys = keys;
		}

	}
}
