import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Solution {

	private static int[] dy = { -1, 1, 0, 0 };
	private static int[] dx = { 0, 0, -1, 1 };

	private static boolean[][] visited;
	private static int[][] map;
	private static Pos[] maxPoses;

	private static int digDepth;
	private static int maxPosIdx;
	private static int maxLength;
	private static int mapSize;

	private static void init(StreamTokenizer tokenizer) throws IOException {
		mapSize = read(tokenizer);
		digDepth = read(tokenizer);

		visited = new boolean[mapSize][mapSize];
		map = new int[mapSize][mapSize];
		maxPoses = new Pos[64];

		maxPosIdx = 0;
		maxLength = 0;

		int max = 0;

		for (int row = 0; row < map.length; row++) {
			for (int col = 0; col < map.length; col++) {
				int value = read(tokenizer);

				if (max < value) {
					max = value;
					maxPosIdx = 0;
				}

				if (max == value) {
					if (maxPoses[maxPosIdx] != null) {
						Pos pos = maxPoses[maxPosIdx];
						pos.row = row;
						pos.col = col;
						pos.height = value;
					}
					else {
						maxPoses[maxPosIdx] = new Pos(row, col, true, value, 1);
					}

					++maxPosIdx;
				}

				map[row][col] = value;
			}
		}
	}

	private static void dfs(Pos cur) {
		maxLength = Math.max(maxLength, cur.length);

		for (int dir = 0; dir < 4; dir++) {
			int nrow = cur.row + dy[dir];
			int ncol = cur.col + dx[dir];

			if (nrow >= 0 && nrow < map.length && ncol >= 0 && ncol < map.length && !visited[nrow][ncol]) {
				if (cur.height > map[nrow][ncol]) {
					visited[nrow][ncol] = true;
					dfs(new Pos(nrow, ncol, cur.isDigable, map[nrow][ncol], cur.length + 1));
					visited[nrow][ncol] = false;
				}

				if (cur.isDigable && cur.height > map[nrow][ncol] - digDepth && cur.height <= map[nrow][ncol]) {
					visited[nrow][ncol] = true;
					dfs(new Pos(nrow, ncol, false, cur.height - 1, cur.length + 1));
					visited[nrow][ncol] = false;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);
		StringBuilder builder = new StringBuilder();
		int testCount = read(tokenizer);

		for (int testNumber = 1; testNumber <= testCount; testNumber++) {
			init(tokenizer);

			for (int idx = 0; idx < maxPosIdx; idx++) {
				visited = new boolean[mapSize][mapSize];
				visited[maxPoses[idx].row][maxPoses[idx].col] = true;

				dfs(maxPoses[idx]);
			}

			builder.append('#').append(testNumber).append(' ').append(maxLength).append('\n');
		}

		System.out.println(builder);

	}

	private static int read(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

	private static class Pos {
		int row, col, height, length;
		boolean isDigable;

		public Pos(int row, int col, boolean isDigable, int height, int length) {
			this.row = row;
			this.col = col;
			this.isDigable = isDigable;
			this.height = height;
			this.length = length;
		}
	}
}
