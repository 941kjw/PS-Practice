import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static StringBuilder builder = new StringBuilder();
	static int[][] synergyInfo;
	static int[] selected;
	static boolean[] used;
	static int ingredientCount;

	static int selectedIdx;
	static int elementIdx;

	static int aTaste;
	static int bTaste;
	static int min;

	static void init(BufferedReader reader) throws IOException {
		ingredientCount = Integer.parseInt(reader.readLine());
		synergyInfo = new int[ingredientCount][ingredientCount];
		selected = new int[ingredientCount / 2];
		used = new boolean[ingredientCount];
		min = Integer.MAX_VALUE;
		StringTokenizer tokenizer;
		for (int row = 0; row < ingredientCount; row++) {
			tokenizer = new StringTokenizer(reader.readLine());
			for (int col = 0; col < ingredientCount; col++) {
				synergyInfo[row][col] = Integer.parseInt(tokenizer.nextToken());
			}
		}
	}

	static int getSynergy(int[] ingredients) {
		int sum = 0;

		for (int idx = 0; idx < ingredients.length; idx++) {
			for (int subIdx = idx + 1; subIdx < ingredients.length; subIdx++) {
				sum += synergyInfo[ingredients[idx]][ingredients[subIdx]];
				sum += synergyInfo[ingredients[subIdx]][ingredients[idx]];
			}
		}

		return sum;
	}

	static int getOpponentSynergy() {
		int[] opponentSelected = new int[ingredientCount / 2];

		int idx = 0;

		for (int ingredientIdx = 0; ingredientIdx < used.length; ingredientIdx++) {
			if (!used[ingredientIdx]) {
				opponentSelected[idx++] = ingredientIdx;
			}
		}
		return getSynergy(opponentSelected);
	}

	static void combination(int elementIdx, int selectedIdx) {
		if (selectedIdx == ingredientCount / 2) {
			aTaste = getSynergy(selected);
			bTaste = getOpponentSynergy();

			min = Math.min(min, Math.abs(aTaste - bTaste));
			return;
		}

		if (elementIdx == ingredientCount)
			return;

		selected[selectedIdx] = elementIdx;
		used[elementIdx] = true;
		combination(elementIdx + 1, selectedIdx + 1);

		used[elementIdx] = false;
		combination(elementIdx + 1, selectedIdx);

	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		int testCount = Integer.parseInt(reader.readLine());

		for (int testNumber = 1; testNumber <= testCount; testNumber++) {
			init(reader);
			combination(0, 0);
			builder.append('#').append(testNumber).append(' ').append(min).append('\n');
		}

		System.out.println(builder);
		reader.close();

	}
}
