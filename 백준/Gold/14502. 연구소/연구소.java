import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayDeque;
import java.util.Queue;

public class Main {

	private static int[][] laboratory;
	private static int[][] copiedLab;
	private static int labHeight;
	private static int labWidth;
	private static int maxSafeZone;

	private static int[] dy = { -1, 0, 1, 0 };
	private static int[] dx = { 0, 1, 0, -1 };

	private static void init(StreamTokenizer tokenizer) throws IOException {

		labHeight = read(tokenizer);
		labWidth = read(tokenizer);
		maxSafeZone = 0;

		laboratory = new int[labHeight][labWidth];
		copiedLab = new int[labHeight][labWidth];

		for (int row = 0; row < labHeight; row++) {
			for (int col = 0; col < labWidth; col++) {
				int value = read(tokenizer);
				laboratory[row][col] = value;
			}
		}
	}

	private static void spreadVirus() {
		Queue<Pos> queue = new ArrayDeque<>();

		resetLabMap();

		for (int row = 0; row < labHeight; row++) {
			for (int col = 0; col < labWidth; col++) {
				if (copiedLab[row][col] == 2)
					queue.add(new Pos(row, col));
			}
		}

		while (!queue.isEmpty()) {
			Pos cur = queue.poll();

			copiedLab[cur.row][cur.col] = -2;

			for (int dir = 0; dir < 4; ++dir) {
				int nrow = cur.row + dy[dir];
				int ncol = cur.col + dx[dir];

				if (nrow < 0 || nrow >= labHeight || ncol < 0 || ncol >= labWidth || copiedLab[nrow][ncol] != 0)
					continue;

				queue.offer(new Pos(nrow, ncol));
			}
		}
	}

	private static int countSafeZone() {
		int counter = 0;
		for (int row = 0; row < labHeight; row++) {
			for (int col = 0; col < labWidth; col++) {
				if (copiedLab[row][col] == 0)
					counter++;
			}
		}
		return counter;
	}

	private static void tryPlaceWall(int count) {
		if (count == 3) {
			spreadVirus();
			maxSafeZone = Math.max(maxSafeZone, countSafeZone());
			return;
		}

		for (int row = 0; row < labHeight; row++) {
			for (int col = 0; col < labWidth; col++) {
				if (laboratory[row][col] == 0) {
					laboratory[row][col] = 1;
					tryPlaceWall(count + 1);
					laboratory[row][col] = 0;
				}
			}
		}
	}

	private static void resetLabMap() {
		for (int row = 0; row < labHeight; row++) {
			for (int col = 0; col < labWidth; col++) {
				copiedLab[row][col] = laboratory[row][col];
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);

		init(tokenizer);

		tryPlaceWall(0);

		System.out.println(maxSafeZone);
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
