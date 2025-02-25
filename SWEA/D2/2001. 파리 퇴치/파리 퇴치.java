import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 파리가 있는 지도에서 파리가 최대한 많은 지역을 내리쳐야 한다!
 * 
 * 이때, 각 칸마다 행 길이 * 열 길이 만큼 더하면서 일일히 계산하면,
 * O(range ^2 * length ^2) 만큼의 연산이 필요하다.
 * 이를 누적합을 이용해서 합을 확보해두면,
 * range가 연관된 연산을 뺄 수 있다.
 * 
 * !!! 누적합이란 !!!
 * n ~ n+ m 까지의 합을 구하기 위해 m만큼 순회하면서 더하는 것보다,
 * n+m까지의 합에서 n-1까지의 합을 빼면, 단 2번의 연산만에 합을 구할 수 있다.
 * 마침 입력도 가로 방향으로 받으니, 누적 합을 구하기도 쉽다.
 * 
 * 1. 입력 초기화
 * 		1-1. 입력을 받는다.
 * 		1-2. 2차원 누적합 전처리.
 * 			1-2-a. 특정 칸 + 바로 전 행/동일 열 + 동일 행/바로 전 열 - (두번 더해진) 바로 전 행/바로 전 열만큼을 빼면, (0,0) ~ 현재 지점 까지 더한 합이 된다.
 * 2. 최댓값 찾기
 * 		2-1. 모든 칸을 탐색하며, 누적합 배열의 현재 칸의 행/열에 범위만큼을 더한 특정 칸 - 범위가 더해진 행/동일 열 - 동일 행/범위가 더해진 열 + (두번 빼진) 바로 전 행/바로 전 열만큼을 더하면, 특정 범위의 합이 된다.
 * 3. 최댓값 출력 
 *
 */
public class Solution {

	static int length;
	static byte[][] flyMap;
	static short[][] flySumMap;
	static int range;

	/**
	 * 1. 입력 초기화
	 * 		1-1. 입력을 받는다.
	 * 		1-2. 2차원 누적합 전처리.
	 * 			1-2-a. 특정 칸 + 바로 전 행/동일 열 + 동일 행/바로 전 열 - (두번 더해진) 바로 전 행/바로 전 열만큼을 빼면, (0,0) ~ 현재 지점 까지 더한 합이 된다.
	 * 2. 최댓값 찾기
	 * 		2-1. 모든 칸을 탐색하며, 누적합 배열의 현재 칸의 행/열에 범위만큼을 더한 특정 칸 - 범위가 더해진 행/동일 열 - 동일 행/범위가 더해진 열 + (두번 빼진) 바로 전 행/바로 전 열만큼을 더하면, 특정 범위의 합이 된다.
	 * 3. 최댓값 출력 
	 */
	static void init(BufferedReader reader) throws IOException {
		String[] limitInput = reader.readLine().split(" ");

		length = Integer.parseInt(limitInput[0]);
		range = Integer.parseInt(limitInput[1]);
		max = -1;
		flyMap = new byte[length][length];
		flySumMap = new short[length + 1][length + 1];

		for (int row = 0; row < length; row++) {
			String[] line = reader.readLine().split(" ");
			for (int col = 0; col < length; col++) {
				flyMap[row][col] = Byte.parseByte(line[col]);
			}
		}

		for (int row = 1; row <= length; row++) {
			for (int col = 1; col <= length; col++) {
				flySumMap[row][col] = (short) (flyMap[row - 1][col - 1] + flySumMap[row - 1][col] - flySumMap[row - 1][col - 1] + flySumMap[row][col - 1]);
			}
		}
	}

	static int max;

	/**
	 *2-1. 행 수만큼 반복:
	 *		2-1-a. 열 수만큼 반복:
	 *		2-1-a-ㄱ. 범위 높이만큼 반복:
	 *			2-1-a-ㄱ-A. 현재 열 ~ 현재 열 + 범위 만큼의 합을 구한다. 
	 *		2-1-a-ㄴ. 최댓값 갱신.
	 */
	static void find() {
		for (int row = 0; row + range <= length; row++) {
			for (int col = 0; col + range <= length; col++) {
				int sum = flySumMap[row + range][col + range] - flySumMap[row + range][col] - flySumMap[row][col + range] + flySumMap[row][col];
				max = Math.max(sum, max);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder builder = new StringBuilder();
		int testCase = Integer.parseInt(reader.readLine());

		for (int testNum = 1; testNum <= testCase; testNum++) {
			init(reader);
			find();
			builder.append('#').append(testNum).append(' ').append(max).append('\n');
		}
		System.out.println(builder.toString());
	}
}
