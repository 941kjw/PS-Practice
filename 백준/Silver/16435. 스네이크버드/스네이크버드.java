import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Main {

	static int length;
	static int[] fruits;

	static void init(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		int fruitCount = (int) tokenizer.nval;
		tokenizer.nextToken();
		length = (int) tokenizer.nval;
		fruits = new int[fruitCount];
		for (int idx = 0; idx < fruits.length; idx++) {
			tokenizer.nextToken();
			fruits[idx] = (int) tokenizer.nval;
		}

		Arrays.sort(fruits);
	}

	static void eat() {
		for (int idx = 0; idx < fruits.length; idx++) {
			if (fruits[idx] <= length) {
				length++;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		InputStreamReader reader = new InputStreamReader(System.in);
		StreamTokenizer tokenizer = new StreamTokenizer(reader);
		init(tokenizer);

		eat();

		System.out.println(length);
	}
}
