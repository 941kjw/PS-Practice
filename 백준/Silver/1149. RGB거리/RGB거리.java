import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

public class Main {

	private static int[][] houses;
	private static int minPrice;
	private static int houseCount;

	static void init(StreamTokenizer tokenizer) throws IOException {
		houseCount = read(tokenizer);
		houses = new int[houseCount + 1][3];
	}

	static void findMaxScore(StreamTokenizer tokenizer) throws IOException {

		for (int idx = 1; idx <= houseCount; idx++) {
			int red = read(tokenizer);
			int green = read(tokenizer);
			int blue = read(tokenizer);

			houses[idx][0] = Math.min(houses[idx - 1][1], houses[idx - 1][2]) + red;
			houses[idx][1] = Math.min(houses[idx - 1][0], houses[idx - 1][2]) + green;
			houses[idx][2] = Math.min(houses[idx - 1][1], houses[idx - 1][0]) + blue;
		}

		minPrice = Math.min(houses[houseCount][0], Math.min(houses[houseCount][1], houses[houseCount][2]));
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);

		init(tokenizer);
		findMaxScore(tokenizer);
		System.out.println(minPrice);
		reader.close();
	}

	private static int read(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}
}
