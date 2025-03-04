import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * 
 * 2048 게임과 같이 특정 방향으로 숫자들을 이동시켜 합친다.
 * 단!!! 연속적으로 합쳐지는 일은 없음 ! (ex. right 2222 -> 4400)
 * 
 * 1.입력을 통한 초기화. 이때 입력받은 command string을 direction의 index로 변환한다.
 * 2.각 방향별 이동
 * 		2-1.방향에 따라 각 행/열을 순회한다. 이때, 결과 보드에 쓸 index를 초기화한다.
 * 		2-2.현재 칸이 0이라면 스킵.
 * 		2-3.당길 방향의 반대 방향으로 0이 아닌 다음 원소를 찾는다.
 * 		2-4.만약 같은 원소라면 현재 칸을 2배로 하고, 해당 칸을 0으로 바꾼다.
 * 		2-5.현재 칸을 결과 보드에 기입한다.
 * 3.결과 보드를 출력한다.
 *
 */
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
