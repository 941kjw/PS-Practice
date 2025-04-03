import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * 
 * 칼로리 제한을 넘지 않으면서, 최대한 맛있는 햄버거를 만들자.
 * 
 * 
 * 1.DP를 통해 최대 점수 조합을 찾는다.
 * 		1-1. 각 재료와, 각 칼로리 점수의 제한마다, 최대 점수를 비교한다. 
 * 		1-2. 이때, 재료의 칼로리양보다 현재 칼로리 제한이 더 낮으면, 선택이 불가능하므로 스킵한다.
 * 		1-3. 칼로리 양이 여유 있었을 때 현재 재료를 골랐을 경우와, 이전 경우를 비교한다. 
 *
 */
public class Solution {

	static int maxScore;
	static int limit;
	static int elementCount;
	static int selectCount;
	static int[] elementTaste;
	static int[] elementCalorie;
	static int[][] dpBoard;

	static void init(StreamTokenizer tokenizer) throws IOException {
		maxScore = Integer.MIN_VALUE;

		elementCount = read(tokenizer);
		limit = read(tokenizer);

		elementTaste = new int[elementCount + 1];
		elementCalorie = new int[elementCount + 1];
		dpBoard = new int[elementCount + 1][limit + 1];

		for (int idx = 1; idx <= elementCount; idx++) {
			elementTaste[idx] = read(tokenizer);
			elementCalorie[idx] = read(tokenizer);
		}
	}

	static void findMaxScore() {

		for (int elementIdx = 1; elementIdx <= elementCount; elementIdx++) {
			for (int calorie = 1; calorie <= limit; calorie++) {
				if (calorie < elementCalorie[elementIdx]) {
					dpBoard[elementIdx][calorie] = dpBoard[elementIdx - 1][calorie];
					continue;
				}

				dpBoard[elementIdx][calorie] = Math.max(dpBoard[elementIdx - 1][calorie], dpBoard[elementIdx - 1][calorie - elementCalorie[elementIdx]] + elementTaste[elementIdx]);

			}
		}

		maxScore = dpBoard[elementCount][limit];
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);
		StringBuilder builder = new StringBuilder();
		int testCount = Integer.parseInt(reader.readLine());

		for (int testNum = 1; testNum <= testCount; testNum++) {
			init(tokenizer);
			findMaxScore();
			builder.append('#').append(testNum).append(' ').append(maxScore).append('\n');
		}

		System.out.println(builder);
		reader.close();
	}

	private static int read(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}
}
