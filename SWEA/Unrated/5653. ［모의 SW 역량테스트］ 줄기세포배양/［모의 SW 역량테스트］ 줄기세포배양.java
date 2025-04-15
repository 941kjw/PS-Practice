import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Solution {

	private static int[][] map;
	private static PriorityQueue<Cell> pQueue;
	private static int height;
	private static int width;
	private static int time;

	private static int[] dx = { -1, 1, 0, 0 };
	private static int[] dy = { 0, 0, -1, 1 };

	private static int read(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

	private static void init(StreamTokenizer tokenizer) throws IOException {
		height = read(tokenizer);
		width = read(tokenizer);
		time = read(tokenizer);

		pQueue = new PriorityQueue<>();
		map = new int[height + 2 * time][width + 2 * time];

		for (int row = time; row < time + height; row++) {
			for (int col = time; col < time + width; col++) {
				int value = read(tokenizer);
				if (value > 0) {
					map[row][col] = value;
					pQueue.add(new Cell(row, col, value, 0));
				}
			}
		}
	}

	private static int spread() {
		int curTime = 1;
		List<Cell> deactivatedCells = new ArrayList<>();
		while (++curTime <= time) {
			int cellsAtTheMoment = pQueue.size();

			for (int count = 0; count < cellsAtTheMoment; ++count) {
				Cell cur = pQueue.poll();

				if (cur.createdAt + cur.life < curTime) {

					if (cur.createdAt + cur.life + 1 == curTime) {
						for (int dir = 0; dir < 4; ++dir) {
							int nrow = cur.row + dy[dir];
							int ncol = cur.col + dx[dir];

							if (map[nrow][ncol] != 0)
								continue;

							map[nrow][ncol] = cur.life;

							pQueue.add(new Cell(nrow, ncol, cur.life, curTime));
						}
					}

					//사망 시각은 생성 시간 + 잠복기 + 생명
					if (cur.createdAt + 2 * cur.life > curTime) {
						deactivatedCells.add(cur);
						continue;
					}
				}
				else {
					deactivatedCells.add(cur);
				}
			}
			for (Cell item : deactivatedCells)
				pQueue.add(item);
			deactivatedCells.clear();
		}
		return pQueue.size();
	}

	private static void printMap() {
		for (int row = 0; row < 2 * time + height; row++) {
			for (int col = 0; col < 2 * time + width; col++) {
				if (map[row][col] >= 0)
					System.out.print("  " + map[row][col]);
				else
					System.out.print(" " + map[row][col]);
			}
			System.out.println();
		}

		System.out.println(pQueue);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);
		StringBuilder builder = new StringBuilder();
		int testCount = read(tokenizer);

		for (int testNumber = 1; testNumber <= testCount; testNumber++) {
			init(tokenizer);

			int result = spread();
			//			printMap();
			builder.append('#').append(testNumber).append(' ').append(result).append('\n');
		}

		System.out.println(builder);
	}

	private static class Cell implements Comparable<Cell> {
		int row, col, life, createdAt;

		public Cell(int row, int col, int life, int createdAt) {
			this.row = row;
			this.col = col;
			this.life = life;
			this.createdAt = createdAt;
		}

		@Override
		public int compareTo(Cell o) {
			if (life + createdAt == o.life + o.createdAt)
				return -Integer.compare(life, o.life);

			return Integer.compare(life + createdAt, o.life + o.createdAt);
		}

		@Override
		public String toString() {
			return "Cell [row=" + row + ", col=" + col + ", life=" + life + ", createdAt=" + createdAt + "]";
		}

	}
}
