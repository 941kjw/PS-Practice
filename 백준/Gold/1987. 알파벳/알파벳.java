import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.HashSet;
import java.util.Set;

public class Main {

	static char[][] board;
	static int boardRow;
	static int boardCol;
	static int maxRouteLength;

	static int[] dx = { -1, 0, 1, 0 };
	static int[] dy = { 0, -1, 0, 1 };

	static void init(StreamTokenizer tokenizer) throws IOException {
		boardRow = read(tokenizer);
		boardCol = read(tokenizer);
		maxRouteLength = 1;
		board = new char[boardRow][boardCol];

		for (int row = 0; row < boardRow; row++) {
			String line = readLine(tokenizer);
			for (int col = 0; col < boardCol; col++) {
				board[row][col] = line.charAt(col);
			}
		}
	}

	static void findMaxRoute() {
		dfs(new Pos(0, 0));
	}

	static void dfs(Pos pos) {
		pos.trace.add(board[pos.row][pos.col]);
		int originRow = pos.row;
		int originCol = pos.col;

		maxRouteLength = Math.max(maxRouteLength, pos.trace.size());

		for (int dir = 0; dir < 4; dir++) {
			int nrow = pos.row + dy[dir];
			int ncol = pos.col + dx[dir];

			if (nrow < 0 || nrow >= boardRow || ncol < 0 || ncol >= boardCol || pos.trace.contains(board[nrow][ncol]))
				continue;

			pos.trace.remove(board[nrow][ncol]);
			pos.row = nrow;
			pos.col = ncol;

			dfs(pos);

			pos.trace.remove(board[nrow][ncol]);
			pos.row = originRow;
			pos.col = originCol;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);

		init(tokenizer);
		findMaxRoute();

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

	static class Pos {
		int row, col;
		Set <Character> trace;

		public Pos(int row, int col) {
			this.row = row;
			this.col = col;
			this.trace = new HashSet <>();
		}
	}

}
