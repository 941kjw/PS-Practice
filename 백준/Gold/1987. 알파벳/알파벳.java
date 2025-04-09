import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
특정 지점에서, 말이 최대한 움직일 수 있는 경로의 길이는 어떠한 방향에서 들어오든 동일합니다.
따라서, 이를 기록해두면 재사용이 가능합니다.
*/
public class Main {

	static int[][] board;
	static int[][] visitedMemo;
	static int boardRow;
	static int boardCol;
	static int maxRouteLength;

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	static void init(StreamTokenizer tokenizer) throws IOException {
		boardRow = read(tokenizer);
		boardCol = read(tokenizer);
		maxRouteLength = -1;
		board = new int[boardRow][boardCol];
		visitedMemo = new int[boardRow][boardCol];

		for (int row = 0; row < boardRow; row++) {
			String line = readLine(tokenizer);
			for (int col = 0; col < boardCol; col++) {
				board[row][col] = line.charAt(col) - 'A';
			}
		}
	}

	static void dfs(int row, int col, int count, int visited) {
		visited |= (1 << board[row][col]);

		if (visitedMemo[row][col] == visited) {
			return;
		}

		maxRouteLength = Math.max(count, maxRouteLength);

		visitedMemo[row][col] = visited;

		for (int dir = 0; dir < 4; dir++) {
			int nrow = row + dy[dir];
			int ncol = col + dx[dir];

			if (nrow < 0 || nrow >= boardRow || ncol < 0 || ncol >= boardCol)
				continue;

			if ((visited & (1 << board[nrow][ncol])) == 0)
				dfs(nrow, ncol, count + 1, visited);
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);

		init(tokenizer);
		dfs(0, 0, 1, 0);

		System.out.println(maxRouteLength);
	}

	static int read(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

	static String readLine(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return tokenizer.sval;
	}

}
