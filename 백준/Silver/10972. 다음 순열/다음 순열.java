import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 자리 교체 방식의 순열 생성 과정을 1회 호출하면 풀리는 문제.
 * 
 * 1. 입력을 통한 초기화. (정렬할 필요 없다)
 * 2. 다음 순열을 찾는 메소드 호출
 * 		2-1.뒤에서부터 오름차순이 깨지는 인덱스를 찾는다.
 * 			2-1-a. 만약 전부 정렬되어있다면, -1 출력.(이미 사전순 마지막이다.)
 * 		2-2.이후, 마지막 정렬되지 않은 인덱스와, 정렬된 구간에서 그 인덱스의 숫자보다 큰 숫자를 찾는다.
 * 		2-3. 둘을 교체한다.
 * 		2-4. 뒤쪽을 뒤집는다.(오름차순이 되게) 이로써 입력받은 순열 바로 다음의 순열을 얻는다. 
 * 3. 다음 순열을 출력.
 * 
 */
public class Main {

	static int[] numbers;
	static StringBuilder builder = new StringBuilder();

	static void init(BufferedReader reader) throws IOException {

		int numberCount = Integer.parseInt(reader.readLine());

		numbers = new int[numberCount];
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
		for (int idx = 0; idx < numbers.length; idx++) {
			numbers[idx] = Integer.parseInt(tokenizer.nextToken());
		}
	}

	static void getNextPermutation() {
		int firstSortedIdx = numbers.length - 1;

		while (firstSortedIdx > 0 && numbers[firstSortedIdx - 1] >= numbers[firstSortedIdx]) {
			firstSortedIdx--;
		}

		if (firstSortedIdx == 0) {
			builder.append(-1);
			return;
		}

		int unsortedIdx = firstSortedIdx - 1;
		int largerNumIdxInSortedArea = numbers.length - 1;

		while (numbers[unsortedIdx] >= numbers[largerNumIdxInSortedArea]) {
			largerNumIdxInSortedArea--;
		}

		swapNumbers(unsortedIdx, largerNumIdxInSortedArea);

		int currentIdx = numbers.length - 1;

		while (firstSortedIdx < currentIdx) {
			swapNumbers(firstSortedIdx, currentIdx);
			firstSortedIdx++;
			currentIdx--;
		}

		for (int number : numbers) {
			builder.append(number).append(' ');
		}
	}

	static void swapNumbers(int from, int to) {
		int temp = numbers[to];
		numbers[to] = numbers[from];
		numbers[from] = temp;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		init(reader);

		getNextPermutation();

		System.out.println(builder);
	}
}
