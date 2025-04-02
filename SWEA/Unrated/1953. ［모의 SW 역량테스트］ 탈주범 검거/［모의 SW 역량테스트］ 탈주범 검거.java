import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {

	private static int[][] sewerMap;

	private static int[] dy = { -1, 1, 0, 0 };
	private static int[] dx = { 0, 0, -1, 1 };

	private static boolean[][] isAcceptable = { null, { true, true, true, true }, { true, true, false, false }, { false, false, true, true }, { true, false, false, true }, { false, true, false, true }, { false, true, true, false }, { true, false, true, false } };

	private static int height;
	private static int width;
	private static int holeRow;
	private static int holeCol;
	private static int elapsed;

	private static void init(StreamTokenizer tokenizer) throws IOException {
		height = read(tokenizer);
		width = read(tokenizer);
		holeRow = read(tokenizer);
		holeCol = read(tokenizer);
		elapsed = read(tokenizer);

		sewerMap = new int[height][width];

		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				int value = read(tokenizer);
				if (value != 0) {
					sewerMap[row][col] = value;
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);
		StringBuilder builder = new StringBuilder();
		int testCount = read(tokenizer);

		for (int testNumber = 1; testNumber <= testCount; ++testNumber) {
			init(tokenizer);

			builder.append('#').append(testNumber).append(' ').append(bfs()).append('\n');
		}
		System.out.println(builder);
	}

	private static int bfs() {

		Queue<Pos> queue = new ArrayDeque<>();

		queue.add(new Pos(holeRow, holeCol));

		int counter = 0;
		int depth = 0;

		while (!queue.isEmpty() && depth < elapsed) {
			int curDepthSize = queue.size();
			++depth;
			for (int depthCount = 0; depthCount < curDepthSize; ++depthCount) {
				Pos cur = queue.poll();

				int tunnelMod = sewerMap[cur.row][cur.col];

				if (tunnelMod == 0) {
					continue;
				}

				counter++;

				sewerMap[cur.row][cur.col] = 0;

				for (int dir = 0; dir < 4; dir++) {
					if (!isAcceptable[tunnelMod][dir])
						continue;

					int nrow = cur.row + dy[dir];
					int ncol = cur.col + dx[dir];

					if (nrow < 0 || nrow >= height || ncol < 0 || ncol >= width || sewerMap[nrow][ncol] == 0)
						continue;

					int dirAsOpponent = dir ^ 1;

					if (isAcceptable[sewerMap[nrow][ncol]][dirAsOpponent]) {
						queue.add(new Pos(nrow, ncol));
					}

				}
			}
		}
		return counter;
	}

	private static int read(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

	private static class Pos {
		int row, col;

		public Pos(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
}
