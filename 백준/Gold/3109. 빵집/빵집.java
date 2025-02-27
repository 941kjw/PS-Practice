import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static byte[] dx = { 1, 1, 1 };
	static byte[] dy = { -1, 0, 1 };

	static char[][] map = new char[10000][500];
	static short rowCount;
	static short colCount;
	static int possiblePlaceCount;

	static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder builder = new StringBuilder();

	static void init() throws IOException {
		String line = reader.readLine();
		StringTokenizer tokenizer = new StringTokenizer(line);
		rowCount = Short.parseShort(tokenizer.nextToken());
		colCount = Short.parseShort(tokenizer.nextToken());

		possiblePlaceCount = 0;

		for (short row = 0; row < rowCount; row++) {
			line = reader.readLine();
			for (short col = 0; col < colCount; col++) {
				map[row][col] = line.charAt(col);
			}
		}
	}

	static boolean placePipe(int row, int col) {
		map[row][col] = 'P';
		boolean flag = false;
		if (col == colCount - 1) {
			possiblePlaceCount++;
			return true;
		}

		for (int dir = 0; dir < 3; dir++) {
			int ny = row + dy[dir];
			int nx = col + dx[dir];

			if (ny > -1 && nx < colCount && ny < rowCount && map[ny][nx] == '.') {
				flag = placePipe(ny, nx);
				if (flag)
					break;
			}
		}

		return flag;
	}

	public static void main(String[] args) throws IOException {
		init();

		for (int row = 0; row < rowCount; row++)
			placePipe(row, 0);

		System.out.println(possiblePlaceCount);
	}

}
