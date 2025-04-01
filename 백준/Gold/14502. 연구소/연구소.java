import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 
 * 
 * 
 *
 */
public class Main {

	private static int[][] laboratory;
	private static int[][] copiedLab;
	private static Pos[] selected;
	private static List<Integer> emptyList;

	private static int labHeight;
	private static int labWidth;
	private static int wallCount;
	private static int maxSafeZone;

	private static int[] dy = { -1, 0, 1, 0 };
	private static int[] dx = { 0, 1, 0, -1 };

	private static void init(StreamTokenizer tokenizer) throws IOException {

		labHeight = read(tokenizer);
		labWidth = read(tokenizer);
		maxSafeZone = 0;
		wallCount = 0;
		selected = new Pos[3];
		emptyList = new ArrayList<>();
		laboratory = new int[labHeight][labWidth];
		copiedLab = new int[labHeight][labWidth];

		for (int row = 0; row < labHeight; row++) {
			for (int col = 0; col < labWidth; col++) {
				int value = read(tokenizer);
				laboratory[row][col] = value;
				if (value == 1)
					++wallCount;
				if (value == 0)
					emptyList.add(row * 10 + col);
			}
		}
	}

	private static void spreadVirus() {
		int tolerableVirusCount = labHeight * labWidth - wallCount - 3 - maxSafeZone;
		Queue<Pos> queue = new ArrayDeque<>();

		resetLabMap();
		int virusCounter = 0;

		for (int row = 0; row < labHeight; row++) {
			for (int col = 0; col < labWidth; col++) {
				if (copiedLab[row][col] == 2) {
					++virusCounter;
					queue.add(new Pos(row, col));
					copiedLab[row][col] = -2;
				}
			}
		}

		while (!queue.isEmpty()) {
			Pos cur = queue.poll();

			if (virusCounter > tolerableVirusCount)
				return;

			copiedLab[cur.row][cur.col] = -2;

			for (int dir = 0; dir < 4; ++dir) {
				int nrow = cur.row + dy[dir];
				int ncol = cur.col + dx[dir];

				if (nrow < 0 || nrow >= labHeight || ncol < 0 || ncol >= labWidth || copiedLab[nrow][ncol] != 0)
					continue;
				copiedLab[nrow][ncol] = -2;
				++virusCounter;
				queue.offer(new Pos(nrow, ncol));
			}
		}
		int currentSafeZone = labHeight * labWidth - wallCount - 3 - virusCounter;
		maxSafeZone = Math.max(maxSafeZone, currentSafeZone);
	}

	private static void tryPlaceWall(int selected, int element) {
		if (selected == 3) {
			spreadVirus();
			return;
		}

		if (element == emptyList.size())
			return;

		Integer pos = emptyList.get(element);

		int row = pos / 10;
		int col = pos % 10;

		laboratory[row][col] = 1;

		tryPlaceWall(selected + 1, element + 1);

		laboratory[row][col] = 0;
		tryPlaceWall(selected, element + 1);

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

		tryPlaceWall(0, 0);

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
