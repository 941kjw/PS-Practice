import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {

	static int targetRow;
	static int targetCol;
	static int arraySize;
	static int order;

	static void init(StreamTokenizer tokenizer) throws IOException {
		arraySize = read(tokenizer);
		targetRow = read(tokenizer);
		targetCol = read(tokenizer);
		order = 0;
	}

	static void checkOrder(int row, int col, int length) {
		if (row == targetRow && col == targetCol) {
			System.out.println(order);
			return;
		}

		if (targetRow < row + length && row <= targetRow && targetCol < col + length && targetCol >= col) {
			checkOrder(row, col, length / 2);
			checkOrder(row, col + length / 2, length / 2);
			checkOrder(row + length / 2, col, length / 2);
			checkOrder(row + length / 2, col + length / 2, length / 2);
		} else {
			order += length * length;
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);
		init(tokenizer);

		checkOrder(0, 0, (1 << arraySize));
	}

	static int read(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}
}