import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int height;
	static int width;

	static char[][] battleField;

	static int commandCount;
	static String command;

	static int tankDirection;

	static int tankPosRow;
	static int tankPosCol;

	static final int UP = 0;
	static final int DOWN = 1;
	static final int RIGHT = 2;
	static final int LEFT = 3;

	static int[] dx = { 0, 0, 1, -1 };
	static int[] dy = { -1, 1, 0, 0 };

	static char[] tankGraphic = { '^', 'v', '>', '<' };

	static void init(BufferedReader reader) throws IOException {
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

		height = Integer.parseInt(tokenizer.nextToken());
		width = Integer.parseInt(tokenizer.nextToken());
		battleField = new char[height][width];

		for (int row = 0; row < height; row++) {
			String line = reader.readLine();
			for (int col = 0; col < width; col++) {
				char fieldInfo = line.charAt(col);
				battleField[row][col] = fieldInfo;

				switch (fieldInfo) {
					case '<':
						tankDirection = LEFT;
						break;
					case 'v':
						tankDirection = DOWN;
						break;
					case '>':
						tankDirection = RIGHT;
						break;
					case '^':
						tankDirection = UP;
						break;
					default:
						break;
				}

				if (fieldInfo == '<' || fieldInfo == 'v' || fieldInfo == '>' || fieldInfo == '^') {
					tankPosRow = row;
					tankPosCol = col;
				}
			}
		}

		commandCount = Integer.parseInt(reader.readLine());
		command = reader.readLine();
	}

	static void simulate() {
		for (int idx = 0; idx < commandCount; idx++) {
			switch (command.charAt(idx)) {
				case 'U':
					rotate(UP);
					move(UP);
					break;
				case 'D':
					rotate(DOWN);
					move(DOWN);
					break;
				case 'R':
					rotate(RIGHT);
					move(RIGHT);
					break;
				case 'L':
					rotate(LEFT);
					move(LEFT);
					break;
				case 'S':
					shoot();
					break;
				default:
					break;
			}
		}
	}

	static void rotate(int direction) {
		//		System.out.println("rotate to" + direction);
		tankDirection = direction;
		battleField[tankPosRow][tankPosCol] = tankGraphic[tankDirection];
	}

	static void move(int direction) {
		//		System.out.println("move to" + direction);
		int nextRow = tankPosRow + dy[direction];
		int nextCol = tankPosCol + dx[direction];

		if (nextRow < height && nextRow > -1 && nextCol > -1 && nextCol < width && battleField[nextRow][nextCol] == '.') {
			battleField[nextRow][nextCol] = battleField[tankPosRow][tankPosCol];
			battleField[tankPosRow][tankPosCol] = '.';

			tankPosRow = nextRow;
			tankPosCol = nextCol;
		}
	}

	static void shoot() {
		//		System.out.println("UTEEEEEEE!!!!!" + tankDirection);
		int nextShellPosCol = tankPosCol + dx[tankDirection];
		int nextShellPosRow = tankPosRow + dy[tankDirection];

		while (nextShellPosRow < height && nextShellPosRow > -1 && nextShellPosCol < width && nextShellPosCol > -1) {
			if (battleField[nextShellPosRow][nextShellPosCol] == '*') {
				battleField[nextShellPosRow][nextShellPosCol] = '.';
				break;
			} else if (battleField[nextShellPosRow][nextShellPosCol] == '#')
				break;

			nextShellPosCol += dx[tankDirection];
			nextShellPosRow += dy[tankDirection];
		}
	}

	static void printMap(StringBuilder builder) {
		for (int row = 0; row < height; row++) {
			for (int col = 0; col < width; col++) {
				builder.append(battleField[row][col]);
			}
			builder.append('\n');
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder builder = new StringBuilder();

		int testCount = Integer.parseInt(reader.readLine());

		for (int testNumber = 1; testNumber <= testCount; testNumber++) {
			init(reader);
			builder.append('#').append(testNumber).append(' ');
			simulate();
			printMap(builder);
		}
		System.out.println(builder);
	}

}
