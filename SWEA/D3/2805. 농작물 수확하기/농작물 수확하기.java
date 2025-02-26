import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 
 * 주어진 농장에서, 가운데 기준으로 가득 찬 정사각형 마름모꼴로 수확한다.
 * 
 * 1.입력을 통한 초기화
 * 2.수확 실행
 * 		2-1. 각 행을 순회
 * 			2-1-a. 현재 행에서 수확해야 할 칸의 갯수를 계산. 이때, 절반 이상을 넘어가면 칸 수가 다시 줄어들어야 한다.
 * 			2-1-b. 시작점의 위치는 절반에서 수확할 칸 수의 절반만큼 뺀 위치.
 * 			2-1-c. 합에 수확지점의 수확량을 더한다.
 * 		2-2.합 반환
 * 3.합 출력.
 */
public class Solution {

	static int[][] farm;
	static StringBuilder builder = new StringBuilder();

	static void init(BufferedReader reader) throws IOException {
		int length = Integer.parseInt(reader.readLine());

		farm = new int[length][length];

		for (int row = 0; row < length; row++) {
			String line = reader.readLine();
			for (int col = 0; col < farm.length; col++) {
				farm[row][col] = line.charAt(col) - '0';
			}
		}
	}

	static int harvest() {
		int length = farm.length;
		int sum = 0;
		int amount = -1;
		for (int row = 0; row < farm.length; row++) {
			if (row > length / 2)
				amount -= 2;
			else
				amount += 2;

			int startCol = (length / 2) - (amount / 2);
			for (int idx = 0; idx < amount; idx++) {
				sum += farm[row][startCol + idx];
			}
		}

		return sum;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		int testCount = Integer.parseInt(reader.readLine());

		for (int testNumber = 1; testNumber <= testCount; testNumber++) {
			init(reader);

			builder.append('#').append(testNumber).append(' ').append(harvest()).append('\n');
		}
		System.out.println(builder);
	}
}
