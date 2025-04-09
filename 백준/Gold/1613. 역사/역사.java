import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {

	private static int[][] map;
	private static int[][] reverseMap;

	private static final int MAX = 100000;
	private static int eventCount;
	private static int relationCount;

	private static void init(StreamTokenizer tokenizer) throws IOException {
		eventCount = read(tokenizer);
		relationCount = read(tokenizer);

		map = new int[eventCount][eventCount];
		reverseMap = new int[eventCount][eventCount];

		for (int row = 0; row < eventCount; row++) {
			for (int col = 0; col < eventCount; col++) {
				int value;
				if (row == col)
					value = 0;
				else
					value = MAX;

				map[row][col] = value;
				reverseMap[row][col] = value;

			}
		}

		for (int count = 0; count < relationCount; count++) {
			int before = read(tokenizer) - 1;
			int after = read(tokenizer) - 1;

			map[before][after] = 1;
			reverseMap[after][before] = 1;
		}

	}

	private static void floyd() {

		for (int passPoint = 0; passPoint < eventCount; passPoint++) {
			for (int from = 0; from < eventCount; from++) {
				for (int to = 0; to < eventCount; to++) {
					map[from][to] = Math.min(map[from][to], map[from][passPoint] + map[passPoint][to]);
					reverseMap[from][to] = Math.min(reverseMap[from][to], reverseMap[from][passPoint] + reverseMap[passPoint][to]);
				}
			}
		}
	}

	private static void query(StreamTokenizer tokenizer) throws IOException {
		int queryCount = read(tokenizer);

		for (int count = 0; count < queryCount; count++) {
			int event1 = read(tokenizer) - 1;
			int event2 = read(tokenizer) - 1;

			if (map[event1][event2] != MAX)
				System.out.println(-1);
			else if (reverseMap[event1][event2] != MAX)
				System.out.println(1);
			else {
				System.out.println(0);
			}
		}
	}

	private static int read(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);

		init(tokenizer);

		floyd();

		query(tokenizer);

	}
}
