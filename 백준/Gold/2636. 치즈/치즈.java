import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * 치즈를 녹이는 시뮬레이션을 한다.
 * bfs로 모든 칸을 방문하고, 1일 경우, 치즈를 지우고 큐에 넣지는 않는다.
 * 
 * 1.입력 초기화
 * 2.시간 시뮬레이션
 * 		2-1. BFS를 이용해 겉면만 녹인다.
 * 		2-2. 방문처리한 구역을 초기화.
 * 		2-3. 남아있는 치즈의 갯수를 세고, 0개가 아니라면 최솟값을 갱신.
 * 		2-4. 남아있는 치즈가 있다면, 반복.
 * 3.전부 녹는데 걸린 시간과, 전부 녹기 1시간 전의 치즈 갯수 출력.
 * 
 *
 */
public class Main {

	static int[][] cheese;
	static int cheeseRow;
	static int cheeseCol;
	static int time;
	static int minRemaining;

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	static void init(StreamTokenizer tokenizer) throws IOException {
		cheeseRow = read(tokenizer);
		cheeseCol = read(tokenizer);

		cheese = new int[cheeseRow][cheeseCol];
		time = 0;

		for (int row = 0; row < cheeseRow; row++) {
			for (int col = 0; col < cheeseCol; col++) {
				cheese[row][col] = read(tokenizer);
			}
		}

		minRemaining = cleanUpAndCountCheese();
	}

	static boolean checkRemaining() {
		for (int row = 0; row < cheeseRow; row++) {
			for (int col = 0; col < cheeseCol; col++) {
				if (cheese[row][col] == 1)
					return true;
			}
		}
		return false;
	}

	static void printCheese() {
		for (int row = 0; row < cheeseRow; row++) {
			for (int col = 0; col < cheeseCol; col++) {
				System.out.print(cheese[row][col] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	static void meltCheese(int row, int col, Queue <Pos> queue) {
		cheese[row][col] = -1;
		queue.add(new Pos(row, col));

		while (!queue.isEmpty()) {
			Pos current = queue.poll();

			for (int dir = 0; dir < 4; dir++) {
				int nrow = current.row + dy[dir];
				int ncol = current.col + dx[dir];

				if (ncol >= cheeseCol || ncol < 0 || nrow < 0 || nrow >= cheeseRow || cheese[nrow][ncol] == -1)
					continue;

				if (cheese[nrow][ncol] == 0) {
					queue.add(new Pos(nrow, ncol));
				}

				cheese[nrow][ncol] = -1;
			}
		}
	}

	static int cleanUpAndCountCheese() {
		int count = 0;
		for (int row = 0; row < cheeseRow; row++) {
			for (int col = 0; col < cheeseCol; col++) {
				if (cheese[row][col] == -1)
					cheese[row][col] = 0;
				else if (cheese[row][col] == 1) {
					count++;
				}
			}
		}
		return count;
	}

	static void simulate() {
		Queue <Pos> queue = new ArrayDeque <>();
		while (true) {
			meltCheese(0, 0, queue);

			int count = cleanUpAndCountCheese();
			time++;
			if (count != 0) {
				minRemaining = Math.min(minRemaining, count);
			} else {
				break;
			}
		}
	}

	static int read(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);

		init(tokenizer);
		simulate();
		System.out.println(time);
		System.out.println(minRemaining);
	}

	static class Pos {
		int row, col;

		public Pos(int row, int col) {
			this.row = row;
			this.col = col;
		}
	}
}
