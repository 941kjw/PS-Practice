import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Solution {
	private static int[] dy = { -1, 1, 0, 0 };
	private static int[] dx = { 0, 0, -1, 1 };

	private static int[][] map;
	private static int[][] copy;
	private static int[] selected;

	private static int max;
	private static int beadCount;
	private static int height;
	private static int width;

	private static int read(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

	private static void init(StreamTokenizer tokenizer) throws IOException {
		beadCount = read(tokenizer);
		width = read(tokenizer);
		height = read(tokenizer);
		max = 181;

		map = new int[height][width];
		copy = new int[height][width];
		selected = new int[beadCount];

		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				int value = read(tokenizer);
				map[row][col] = value;
				copy[row][col] = value;
			}
		}
	}

	private static void makeCombination(int selectedIdx) {
		if (selectedIdx == beadCount) {
			int current = throwBeads();
			max = Math.min(max, current);
			clear();
			return;
		}

		for (int col = 0; col < width; ++col) {
			selected[selectedIdx] = col;
			makeCombination(selectedIdx + 1);
			selected[selectedIdx] = 0;
		}
	}

	private static int throwBeads() {

		for (int pos : selected) {
			for (int row = 0; row < height; ++row) {
				if (copy[row][pos] != 0) {
					explode(row, pos);
					break;
				}
			}
			dropBricks();
		}
		return countBoxes();
	}

	private static void clear() {
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				copy[row][col] = map[row][col];
			}
		}
	}

	private static void dropBricks() {
		for (int col = 0; col < width; ++col) {
			int insertRow = height - 1;
			for (int row = height - 1; row >= 0; --row) {
				if (copy[row][col] != 0) {
					copy[insertRow][col] = copy[row][col];
					if (insertRow != row) {
						copy[row][col] = 0;
					}
					--insertRow;
				}
			}
		}
	}

	private static int countBoxes() {
		int sum = 0;
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				if (copy[row][col] != 0)
					++sum;
			}
		}
		return sum;
	}

	private static void explode(int row, int col) {

		int length = copy[row][col];
		copy[row][col] = 0;

		if (length <= 1)
			return;

		for (int dir = 0; dir < 4; ++dir) {
			for (int curLength = 1; curLength < length; ++curLength) {
				int nrow = row + dy[dir] * curLength;
				int ncol = col + dx[dir] * curLength;

				if (nrow < 0 || nrow >= height || ncol < 0 || ncol >= width)
					break;
				if (copy[nrow][ncol] == 0)
					continue;
				explode(nrow, ncol);
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
			makeCombination(0);
			builder.append('#').append(testNumber).append(' ').append(max).append('\n');
		}

		System.out.println(builder);
	}
}
