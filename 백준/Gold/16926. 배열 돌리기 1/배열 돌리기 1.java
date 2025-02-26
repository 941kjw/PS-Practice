import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {

	static int[] dy = { 0, 1, 0, -1 };
	static int[] dx = { 1, 0, -1, 0 };

	static int[][] array;
	static short rotateCount;

	static void init(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		int rowAmount = (int) tokenizer.nval;
		tokenizer.nextToken();
		int colAmount = (int) tokenizer.nval;

		tokenizer.nextToken();
		rotateCount = (short) tokenizer.nval;

		array = new int[rowAmount][colAmount];

		for (int row = 0; row < rowAmount; row++) {
			for (int col = 0; col < colAmount; col++) {
				tokenizer.nextToken();
				array[row][col] = (int) tokenizer.nval;
			}
		}
	}

	static void rotate() {
		int padding = 0;
		int maxPadding = (array.length < array[0].length ? array.length : array[0].length) / 2;
		while (padding < maxPadding) {
			int xidx = padding;
			int yidx = padding;

			int dir = 0;
			int temp = array[yidx][xidx];
			while (true) {
				int nextX = xidx + dx[dir];
				int nextY = yidx + dy[dir];

				if (nextX < padding || nextX >= array[0].length - padding || nextY < padding || nextY >= array.length - padding) {
					dir++;
					continue;
				}
				array[yidx][xidx] = array[nextY][nextX];

				yidx = nextY;
				xidx = nextX;

				if (xidx == padding && yidx == padding)
					break;
			}

			array[padding + 1][padding] = temp;
			padding++;
		}
	}

	public static void main(String[] args) throws IOException {
		InputStreamReader reader = new InputStreamReader(System.in);
		StreamTokenizer tokenizer = new StreamTokenizer(reader);

		init(tokenizer);

		while (rotateCount-- > 0) {
			rotate();
		}
		StringBuilder builder = new StringBuilder();
		for (int row = 0; row < array.length; row++) {
			for (int col = 0; col < array[0].length; col++) {
				builder.append(array[row][col]).append(' ');
			}
			builder.append('\n');
		}
		System.out.println(builder);
	}
}
