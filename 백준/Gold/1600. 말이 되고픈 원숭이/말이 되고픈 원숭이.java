import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {
	private static int[][] worldMap;
	private static boolean[][][] visited;

	private static int[] dy = { -1, 1, 0, 0 };
	private static int[] dx = { 0, 0, -1, 1 };

	private static int[] specialy = { -2, -2, -1, -1, 1, 1, 2, 2 };
	private static int[] specialx = { -1, 1, -2, 2, -2, 2, -1, 1 };

	private static int specialMoveCount;
	private static int width;
	private static int height;

	private static void init(StreamTokenizer tokenizer) throws IOException {
		specialMoveCount = read(tokenizer);

		width = read(tokenizer);
		height = read(tokenizer);

		worldMap = new int[height][width];
		visited = new boolean[height][width][specialMoveCount + 1];

		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				int value = read(tokenizer);
				if (value == 1) {
					worldMap[row][col] = -1;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);

		init(tokenizer);

		int result = bfs();

		System.out.println(result);
	}

	private static int bfs() {

		Queue<Pos> queue = new ArrayDeque<>();

		queue.add(new Pos(0, 0, specialMoveCount, 0));
		visited[0][0][specialMoveCount] = true;
		int min = -1;

		while (!queue.isEmpty()) {
			Pos cur = queue.poll();

			if (cur.row == height - 1 && cur.col == width - 1)
				return cur.step;

			//능력 없이 그냥.
			for (int dir = 0; dir < 4; ++dir) {
				int nrow = cur.row + dy[dir];
				int ncol = cur.col + dx[dir];

				if (nrow > -1 && nrow < height && ncol > -1 && ncol < width && !visited[nrow][ncol][cur.specialCount] && worldMap[nrow][ncol] == 0) {
					visited[nrow][ncol][cur.specialCount] = true;
					queue.add(new Pos(nrow, ncol, cur.specialCount, cur.step + 1));
				}
			}

			//능력 사용 가능할 경우?
			if (cur.specialCount > 0) {
				for (int dir = 0; dir < 8; ++dir) {
					int nrow = cur.row + specialy[dir];
					int ncol = cur.col + specialx[dir];

					if (nrow > -1 && nrow < height && ncol > -1 && ncol < width && !visited[nrow][ncol][cur.specialCount - 1] && worldMap[nrow][ncol] == 0) {
						visited[nrow][ncol][cur.specialCount - 1] = true;
						queue.add(new Pos(nrow, ncol, cur.specialCount - 1, cur.step + 1));
					}
				}
			}

		}

		return min;
	}

	private static int read(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

	private static class Pos {
		int row, col, specialCount, step;

		public Pos(int row, int col, int specialCount, int step) {
			this.row = row;
			this.col = col;
			this.specialCount = specialCount;
			this.step = step;
		}
	}
}
