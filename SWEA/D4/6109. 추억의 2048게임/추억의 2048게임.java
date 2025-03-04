import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Solution {

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StreamTokenizer tokenizer = new StreamTokenizer(reader);
	static StringBuilder builder = new StringBuilder();
	static int boardSize;
	static int[][] board = new int[20][20];
	static int[][] resultBoard = new int[20][20];
	static int direction;

	static int LEFT = 0;
	static int RIGHT = 1;
	static int UP = 2;
	static int DOWN = 3;

	static int[] dy = { 0, 0, 1, -1 };
	static int[] dx = { 1, -1, 0, 0 };

	static void init() throws IOException {

		boardSize = readInt();
		String command = read();

		for (int row = 0; row < boardSize; row++) {
			for (int col = 0; col < boardSize; col++) {
				board[row][col] = readInt();
				resultBoard[row][col] = 0;
			}
		}

		switch (command) {
			case "left":
				direction = LEFT;
				break;
			case "right":
				direction = RIGHT;
				break;
			case "up":
				direction = UP;
				break;
			case "down":
				direction = DOWN;
				break;
		}
	}

	static void doCommand() {
		if (direction == LEFT) {
			for (int row = 0; row < boardSize; row++) {
				int resultColCounter = 0;
				for (int col = 0; col < boardSize; col++) {
					if (board[row][col] == 0)
						continue;

					int nextNonZeroFinder = col + 1;

					while (nextNonZeroFinder < boardSize && board[row][nextNonZeroFinder] == 0) {
						nextNonZeroFinder++;
					}

					if (nextNonZeroFinder < boardSize && board[row][col] == board[row][nextNonZeroFinder]) {
						board[row][col] *= 2;
						board[row][nextNonZeroFinder] = 0;
					}

					if (board[row][col] != 0)
						resultBoard[row][resultColCounter++] = board[row][col];
				}
			}
		}

		if (direction == RIGHT) {

			for (int row = 0; row < boardSize; row++) {
				int resultColCounter = boardSize - 1;
				for (int col = boardSize - 1; col > -1; col--) {
					if (board[row][col] == 0)
						continue;

					int nextNonZeroFinder = col + dx[direction];

					while (nextNonZeroFinder > -1 && board[row][nextNonZeroFinder] == 0) {
						nextNonZeroFinder--;
					}

					if (nextNonZeroFinder > -1 && board[row][col] == board[row][nextNonZeroFinder]) {
						board[row][col] *= 2;
						board[row][nextNonZeroFinder] = 0;
					}

					if (board[row][col] != 0)
						resultBoard[row][resultColCounter--] = board[row][col];
				}
			}
		}
		if (direction == UP) {
			for (int col = 0; col < boardSize; col++) {
				int resultRowCounter = 0;
				for (int row = 0; row < boardSize; row++) {
					if (board[row][col] == 0)
						continue;

					int nextNonZeroFinder = row + dy[direction];

					while (nextNonZeroFinder < boardSize && board[nextNonZeroFinder][col] == 0) {
						nextNonZeroFinder++;
					}

					if (nextNonZeroFinder < boardSize && board[row][col] == board[nextNonZeroFinder][col]) {
						board[row][col] *= 2;
						board[nextNonZeroFinder][col] = 0;
					}

					if (board[row][col] != 0)
						resultBoard[resultRowCounter++][col] = board[row][col];
				}
			}
		}
		if (direction == DOWN) {
			for (int col = 0; col < boardSize; col++) {
				int resultRowCounter = boardSize - 1;
				for (int row = boardSize - 1; row > -1; row--) {
					if (board[row][col] == 0)
						continue;

					int nextNonZeroFinder = row + dy[direction];

					while (nextNonZeroFinder > -1 && board[nextNonZeroFinder][col] == 0) {
						nextNonZeroFinder--;
					}

					if (nextNonZeroFinder > -1 && board[row][col] == board[nextNonZeroFinder][col]) {
						board[row][col] *= 2;
						board[nextNonZeroFinder][col] = 0;
					}

					if (board[row][col] != 0)
						resultBoard[resultRowCounter--][col] = board[row][col];
				}
			}
		}
	}

	static void printResult(int testNumber) {
		builder.append('#').append(testNumber).append('\n');
		for (int row = 0; row < boardSize; row++) {
			for (int col = 0; col < boardSize; col++) {
				builder.append(resultBoard[row][col]).append(' ');
			}
			builder.append('\n');
		}
	}

	public static void main(String[] args) throws IOException {
		int testCount = readInt();

		for (int testnumber = 1; testnumber <= testCount; testnumber++) {
			init();
			doCommand();
			printResult(testnumber);
		}
		System.out.println(builder);
	}

	static int readInt() throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

	static String read() throws IOException {
		tokenizer.nextToken();
		return tokenizer.sval;
	}

}
