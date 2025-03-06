import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

public class Solution {

	static char[][] map;
	static int clickCount;
	static int length;

	static int[] dx = { -1, 0, 1, 1, 1, 0, -1, -1 };
	static int[] dy = { -1, -1, -1, 0, 1, 1, 1, 0 };

	static void init(BufferedReader reader) throws IOException {

		length = Integer.parseInt(reader.readLine());
		map = new char[length][length];
		clickCount = 0;

		for (int row = 0; row < length; row++) {
			String line = reader.readLine();
			for (int col = 0; col < length; col++) {
				map[row][col] = line.charAt(col);
			}
		}
	}

	static void blackSheepWall(int row, int col) {
		map[row][col] = (char) (markMine(row, col) + '0');

		if (map[row][col] != '0')
			return;

		Queue <Pos> queue = new ArrayDeque <>();
		queue.add(new Pos(row, col));

		while (!queue.isEmpty()) {
			Pos nPos = queue.poll();

			for (int dir = 0; dir < 8; dir++) {
				int nrow = nPos.row + dy[dir];
				int ncol = nPos.col + dx[dir];

				if (nrow < 0 || nrow >= length || ncol >= length || ncol < 0)
					continue;

				if (map[nrow][ncol] == '.') {
					map[nrow][ncol] = (char) (markMine(nrow, ncol) + '0');

					if (map[nrow][ncol] == '0')
						queue.add(new Pos(nrow, ncol));
				}

			}
		}
	}

	static int markMine(int row, int col) {
		int count = 0;
		for (int dir = 0; dir < 8; dir++) {
			int nrow = row + dy[dir];
			int ncol = col + dx[dir];

			if (nrow < 0 || nrow >= length || ncol >= length || ncol < 0)
				continue;

			if (map[nrow][ncol] == '*')
				count++;
		}

		return count;
	}

	static void findMinClick() {
		for (int row = 0; row < length; row++) {
			for (int col = 0; col < length; col++) {
				if (map[row][col] == '.') {
					blackSheepWall(row, col);
				}
			}
		}

		for (int row = 0; row < length; row++) {
			for (int col = 0; col < length; col++) {
				if (map[row][col] == '0') {
					markOneClickedRegion(row, col);
					clickCount++;
				}
			}
		}

		for (int row = 0; row < length; row++) {
			for (int col = 0; col < length; col++) {
				if (map[row][col] != '*') {
					clickCount++;
				}
			}
		}

	}

	static void markOneClickedRegion(int row, int col) {
		Queue <Pos> queue = new ArrayDeque <>();
		queue.add(new Pos(row, col));
		map[row][col] = '*';

		while (!queue.isEmpty()) {
			Pos nPos = queue.poll();

			for (int dir = 0; dir < 8; dir++) {
				int nrow = nPos.row + dy[dir];
				int ncol = nPos.col + dx[dir];

				if (nrow < 0 || nrow >= length || ncol >= length || ncol < 0)
					continue;

				if (map[nrow][ncol] == '0') {
					queue.add(new Pos(nrow, ncol));
				}

				map[nrow][ncol] = '*';

			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder builder = new StringBuilder();
		int testCount = Integer.parseInt(reader.readLine());

		for (int testNumber = 1; testNumber <= testCount; testNumber++) {
			init(reader);

			findMinClick();

			builder.append('#').append(testNumber).append(' ').append(clickCount).append('\n');
		}
		System.out.println(builder);
	}

	static class Pos {
		int row, col;

		public Pos(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
}
