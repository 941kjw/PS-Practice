import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 칼로리 제한을 넘지 않으면서, 최대한 맛있는 햄버거를 만들자.
 * 
 * 조합을 생성하고, 조합의 점수를 갱신하는 중 칼로리 한도를 넘으면 갱신하지 않는다.
 * 
 * 1.입력 초기화
 * 2.테스트 수만큼 루프
 * 	 2-1.1개부터 전부 선택하는 모든 조합을 생성 시도.
 * 		2-1-a. 우선 마지막 n개만큼 선택한 가장 첫번째 조합으로 점수를 갱신한다.
 * 		2-1-b. 조합 루프 실행
 * 			2-1-b-ㄱ. 0-1의 형태인 인덱스를 찾는다.
 * 			2-1-b-ㄴ. 만약 배열의 맨 앞에 도달했다면, 이 조합이 마지막 조합이다.
 * 		2-2.이후, 0-1 형태에서 0 인 인덱스와, 가장 뒤의 1을 교체한다.
 * 		2-4. 뒤쪽을 뒤집는다.(오름차순이 되게).
 * 	2-2. 최대 맛 점수 출력.
 *
 */
public class Solution {

	static int maxScore;
	static int limit;
	static int elementCount;
	static int selectCount;
	static int[] elementTaste;
	static int[] elementCalorie;
	static int[] selected;

	//입력을 통한 초기화.
	static void init(BufferedReader reader) throws IOException {
		maxScore = Integer.MIN_VALUE;

		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
		elementCount = Integer.parseInt(tokenizer.nextToken());
		limit = Integer.parseInt(tokenizer.nextToken());

		elementTaste = new int[elementCount];
		elementCalorie = new int[elementCount];
		selected = new int[elementCount];

		for (int idx = 0; idx < elementCount; idx++) {
			tokenizer = new StringTokenizer(reader.readLine());
			elementTaste[idx] = Integer.parseInt(tokenizer.nextToken());
			elementCalorie[idx] = Integer.parseInt(tokenizer.nextToken());
		}
	}

	static boolean hasNextCombination() {
		int lastIdxOfZeroOne = elementCount - 1;

		while (lastIdxOfZeroOne > 0 && selected[lastIdxOfZeroOne] <= selected[lastIdxOfZeroOne - 1]) {
			lastIdxOfZeroOne--;
		}

		if (lastIdxOfZeroOne == 0)
			return false;

		int idxToBeSwapped = lastIdxOfZeroOne - 1;
		int indexToBeSwappedWith = elementCount - 1;

		while (selected[idxToBeSwapped] >= selected[indexToBeSwappedWith]) {
			indexToBeSwappedWith--;
		}

		swap(idxToBeSwapped, indexToBeSwappedWith);

		int sortHelperIndex = elementCount - 1;

		while (sortHelperIndex > lastIdxOfZeroOne) {
			swap(lastIdxOfZeroOne, sortHelperIndex);
			lastIdxOfZeroOne++;
			sortHelperIndex--;
		}
		return true;
	}

	static void swap(int from, int to) {
		int temp = selected[from];
		selected[from] = selected[to];
		selected[to] = temp;
	}

	static void calcMaxScore(int[] selected) {
		int calorieSum = 0;
		int tasteSum = 0;
		for (int idx = 0; idx < selected.length; idx++) {
			if (selected[idx] == 1) {
				tasteSum += elementTaste[idx];
				calorieSum += elementCalorie[idx];
			}
			if (calorieSum > limit)
				return;
		}

		maxScore = Math.max(tasteSum, maxScore);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder builder = new StringBuilder();
		int testCount = Integer.parseInt(reader.readLine());

		for (int testNum = 1; testNum <= testCount; testNum++) {
			init(reader);

			for (selectCount = 1; selectCount <= elementCount; selectCount++) {
				selected = new int[elementCount];
				for (int idx = 0; idx < selectCount; idx++) {
					selected[elementCount - 1 - idx] = 1;
				}

				do {
					calcMaxScore(selected);
				} while (hasNextCombination());
			}

			builder.append('#').append(testNum).append(' ').append(maxScore).append('\n');
		}

		System.out.println(builder);
		reader.close();
	}
}
