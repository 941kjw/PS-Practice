import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 연산자 순서의  모든 순열을 생성해본다.
 * 
 * 1.입력을 통한 초기화
 * 2.테스트 수만큼 루프
 * 	 2-1.연산자의 모든 순서 순열을 생성
 * 		2-1-a. 조합 재귀 실행
 * 			2-1-a-ㄱ. 선택 갯수 한도에 도달한 경우:
 * 				2-1-a-ㄱ-A. 선택된 연산자 순서에 따른 계산 실행.
 *				2-1-a-ㄱ-B. 최대/최소 갱신.
 *				2-1-a-ㄱ-C. return.
 * 			2-1-a-ㄷ. 그렇지 않을 경우, 각 연산자별로 순회.
 * 				2-1-a-ㄷ-A. 잔여 갯수가 있을 경우, 재귀 실행.
 * 				2-1-a-ㄷ-B. 그렇지 않은 경우 건너뜀.
 * 	2-2. 최대 - 최소 출력.
 *
 */
public class Solution {

	static int testCount;
	static int numberCount;
	static int[] operatorCount = new int[4];
	static int operatorCountSum;
	static int[] numbers;
	static int[] selected;
	static int max;
	static int min;
	static StringBuilder builder = new StringBuilder();

	static void init(BufferedReader reader) throws IOException {

		numberCount = Integer.parseInt(reader.readLine());
		operatorCountSum = 0;
		numbers = new int[numberCount];
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
		for (int idx = 0; idx < 4; idx++) {
			operatorCount[idx] = Integer.parseInt(tokenizer.nextToken());
			operatorCountSum += operatorCount[idx];
		}

		selected = new int[operatorCountSum];

		tokenizer = new StringTokenizer(reader.readLine());
		for (int idx = 0; idx < numberCount; idx++) {
			numbers[idx] = Integer.parseInt(tokenizer.nextToken());
		}

	}

	static void tryAllCase(int selectedIdx) {
		if (selectedIdx == operatorCountSum) {
			int idx = 0;
			int value = numbers[idx++];
			for (int opIdx = 0; opIdx < operatorCountSum; opIdx++) {
				switch (selected[opIdx]) {
					case 0:
						value += numbers[idx++];
						break;
					case 1:
						value -= numbers[idx++];
						break;
					case 2:
						value *= numbers[idx++];
						break;
					case 3:
						value /= numbers[idx++];
						break;
					default:
						break;
				}
			}

			min = Math.min(min, value);
			max = Math.max(max, value);

			return;
		}

		for (int idx = 0; idx < 4; idx++) {
			if (operatorCount[idx] > 0) {
				operatorCount[idx]--;
				selected[selectedIdx] = idx;
				tryAllCase(selectedIdx + 1);
				operatorCount[idx]++;
			}
		}

	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		testCount = Integer.parseInt(reader.readLine());

		for (int testNumber = 1; testNumber <= testCount; testNumber++) {
			init(reader);

			tryAllCase(0);
			builder.append('#').append(testNumber).append(' ').append(max - min).append('\n');
		}

		System.out.println(builder);
		reader.close();
	}
}
