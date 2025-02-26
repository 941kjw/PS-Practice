import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 배열을 반시계 방향으로 회전시킨다. 안쪽에 있는 것도!
 * 
 * !! 주의 !!
 * 돌리는 방향의 역방향으로 할당해야 잘 된다.
 * 순방향으로 하면 이전 값이 계속 따라서 흘러내려온다.
 * 첫번째 칸의 값을 저장해 두었다가 회전 시킨 후 할당시키자.
 * 
 * 1.입력 초기화
 * 2.
 * 
 *
 */
public class Main {

	static int[] dy = { 0, 1, 0, -1 };
	static int[] dx = { 1, 0, -1, 0 };

	static int[][] array;
	static short rotateCount;

	static void init(BufferedReader reader) throws IOException {
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
		int rowAmount = Integer.parseInt(tokenizer.nextToken());
		int colAmount = Integer.parseInt(tokenizer.nextToken());
		rotateCount = Short.parseShort(tokenizer.nextToken());

		array = new int[rowAmount][colAmount];

		for (int row = 0; row < rowAmount; row++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for (int col = 0; col < colAmount; col++) {
				array[row][col] = Integer.parseInt(tokenizer.nextToken());
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
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		init(reader);

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
