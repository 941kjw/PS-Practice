import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

	static int selectCount;
	static int[] selected;

	static int elementCount;
	static StringBuilder builder = new StringBuilder();

	static void combination(int elementIdx, int selectedIdx) {
		if (selectedIdx == selectCount) {
			for (int num : selected) {
				builder.append(num + " ");
			}
			builder.append('\n');
			return;
		}

		if (elementIdx == elementCount + 1)
			return;

		selected[selectedIdx] = elementIdx;
		combination(elementIdx + 1, selectedIdx + 1);
		combination(elementIdx + 1, selectedIdx);
	}

	static void init(BufferedReader reader) throws IOException {
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

		elementCount = Integer.parseInt(tokenizer.nextToken());
		selectCount = Integer.parseInt(tokenizer.nextToken());
		selected = new int[selectCount];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		init(reader);
		combination(1, 0);

		System.out.println(builder);
		reader.close();
	}
}
