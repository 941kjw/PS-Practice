import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * 주어진 재료들을 사용해 햄버거를 만든다.
 * 그런데 이때, 어떤 재료들은 중복해서 사용 할 수 없다!
 * !!! 주의 !!!
 * 아무것도 넣지 않은 버거도 버거다. 
 * 
 * 1. 입력을 통한 초기화
 * 		1-1. 이때, 제한 조건을 이진수로 바꾸어 저장함.
 * 2. 부분집합 생성 : 2^(재료 갯수)까지 0부터 순회 (이진법으로 모든 부분집합을 생성할 수 있다.)
 * 		2-1. 각 부분집합마다 조합 제한 조건들을 & 연산을 통해 제한 조건이 포함되어있는지 순회하며 검사
 * 			2-1-a.제한 조건이 모두 포함되어있지 않으면, 가능한 방법이므로 합을 1늘림.
 * 			2-1-b.하나라도 포함되어있으면 건너뜀.
 * 3. 합 출력.  
 *
 */
public class Solution {

	static int elementCount;
	static int testCount;
	static int[] elements;
	static boolean[] used;
	static int sum;
	static int restrictionCount;
	static int[] restrictionMap;

	static void init(BufferedReader reader) throws IOException {
		String[] input = reader.readLine().split(" ");

		elementCount = Integer.parseInt(input[0]);
		restrictionCount = Integer.parseInt(input[1]);
		sum = 0;
		elements = new int[elementCount];
		used = new boolean[elementCount];
		restrictionMap = new int[restrictionCount];

		for (int restrictionIdx = 0; restrictionIdx < restrictionCount; restrictionIdx++) {
			input = reader.readLine().split(" ");
			int element1 = (1 << (Integer.parseInt(input[0]) - 1));
			int element2 = (1 << (Integer.parseInt(input[1]) - 1));
			restrictionMap[restrictionIdx] |= (element1 | element2);
		}

		for (int idx = 1; idx <= elementCount; idx++) {
			elements[idx - 1] = idx;
		}
	}

	/**
	 * 햄버거를 만든다. 만약, 현재 선택한 부분집합에 제한 사항중 특정 조합이 포함된 경우, 합을 늘리지 않고 건너뛴다. 
	 */
	static void makeBoogee() {
		for (int elementIdx = 0; elementIdx < (1 << elementCount); elementIdx++) {
			boolean illegalCombinationFlag = false;
			for (int restrictionIdx = 0; restrictionIdx < restrictionCount; restrictionIdx++) {
				if ((elementIdx & restrictionMap[restrictionIdx]) == restrictionMap[restrictionIdx]) {
					illegalCombinationFlag = true;
					break;
				}
			}
			if (!illegalCombinationFlag)
				sum++;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		testCount = Integer.parseInt(reader.readLine());
		StringBuilder builder = new StringBuilder();
		for (int testIdx = 1; testIdx <= testCount; testIdx++) {
			init(reader);

			makeBoogee();

			builder.append('#').append(testIdx).append(' ').append(sum).append('\n');
		}

		System.out.println(builder);
		reader.close();
	}

}
