import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 모든 부분집합을 생성해서 테스트해본다.
 * 
 * 1.입력 초기화
 * 2.부분집합 생성
 * 		2-1. 요소들을 전부 고려해보았다면
 * 			2-1-a. 총 합을 계산하여, 최소치를 넘었다면 차이를 갱신
 * 		2-2. 그렇지 않다면 사용한 경우와 사용하지 않은 경우를 나눠 재귀를 실행한다.
 * 3. 최소 차이 출력.
 * 
 *
 */
public class Solution {

	static StringBuilder builder = new StringBuilder();
	static int min;
	static int cutLine;
	static int[] employees = new int[20];
	static int employeeCount;

	static void init(BufferedReader reader) throws IOException {
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

		employeeCount = Integer.parseInt(tokenizer.nextToken());
		min = Integer.MAX_VALUE;
		cutLine = Integer.parseInt(tokenizer.nextToken());

		tokenizer = new StringTokenizer(reader.readLine());
		for (int idx = 0; idx < employeeCount; idx++) {
			employees[idx] = Integer.parseInt(tokenizer.nextToken());
		}
	}

	static void buildTower(int elementIdx, int sum) {
		if (elementIdx == employeeCount) {
			if (sum >= cutLine)
				min = Math.min(min, sum - cutLine);
			return;
		}

		buildTower(elementIdx + 1, sum + employees[elementIdx]);
		buildTower(elementIdx + 1, sum);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		int testCount = Integer.parseInt(reader.readLine());

		for (int testNumber = 1; testNumber <= testCount; testNumber++) {
			init(reader);
			buildTower(0, 0);
			builder.append('#').append(testNumber).append(' ').append(min).append('\n');
		}

		System.out.println(builder);
	}
}
