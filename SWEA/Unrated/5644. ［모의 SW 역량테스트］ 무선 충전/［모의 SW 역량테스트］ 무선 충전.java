import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * 
 * 특정 지도에 범위 내에 동일한 충전량을 제공하는 충전탑이 있다.
 * A/B 두 사람이 특정 경로로 이동할 때, 가장 많이 충전하는 경우의 충전량을 구하자.
 * 
 * !주의!
 * 두 사람이 같은 충전탑을 사용시 충전량이 반감한다.
 * 0초에도 충전이 가능하다. 즉, 최초 위치에서도 충전량을 세야하므로, 움직이지 않는 상황을 하나 더 만들던, 충전량을 세고 움직이던 하자.
 * 
 * 1.입력을 통한 초기화
 *  	1-1. 맵 전체를 돌면서 각 충전기의 범위 내에 들어오는지 기록한다.
 * 2.움직임 시뮬레이션 실시
 * 		2-1.우선 움직인다.
 * 		2-2.현재 칸에서 A/B의 모든 충전탑을 조합해본다.
 * 			2-2-a.A/B 각각의 충전량을 구해온다.
 * 			2-2-b.만약 A/B의 충전탑 번호가 같고, 충전량이 동일하다면, A/B가 특정 충전탑을 둘 다 사용할 수 있고, 동시에 사용중인 것이므로, 충전량을 반감시킨다.
 * 			2-2-c.충전량 합의 최댓값을 갱신한다.
 * 		2-3.충전량 합에 현재 시간의 충전량 합 최댓값을 더한다.
 * 3. 최대 충전량 합 출력.
 * 
 * 이동 시뮬레이션은,
 * 전체 이동 시간을 T, 충전기의 수를 N이라 할때,
 * O(T * N^2) 아닐까?
 *
 * 충전기 배치 시뮬레이션은, 
 * 행/열 길이가 동일하므로 한 변의 길이가 L일 때,
 * O(N*L^2)?
 */
public class Solution {

	static boolean[][][] map;

	static int[][] userRoute;

	static int[] dx = { 0, 0, 1, 0, -1 };
	static int[] dy = { 0, -1, 0, 1, 0 };

	static int[][] userPos;

	static int totalMoveTime;

	static int chargerCount;
	static int[][] chargeInfo;

	static int maxChargeSum;

	static int[][] chargerInfo;

	static void init(StreamTokenizer tokenizer) throws IOException {
		maxChargeSum = 0;

		totalMoveTime = read(tokenizer);
		chargerCount = read(tokenizer);

		chargerInfo = new int[chargerCount][4];
		map = new boolean[10][10][chargerCount];
		userRoute = new int[2][totalMoveTime + 1];
		for (int userNumber = 0; userNumber < 2; userNumber++) {
			for (int time = 1; time <= totalMoveTime; time++) {
				userRoute[userNumber][time] = read(tokenizer);
			}
			userRoute[userNumber][0] = 0;
		}
		userPos = new int[2][2];
		chargeInfo = new int[2][totalMoveTime + 1];

		userPos[0][0] = 0;
		userPos[0][1] = 0;

		userPos[1][0] = 9;
		userPos[1][1] = 9;

		for (int idx = 0; idx < chargerCount; idx++) {
			chargerInfo[idx][1] = read(tokenizer) - 1;
			chargerInfo[idx][0] = read(tokenizer) - 1;
			chargerInfo[idx][2] = read(tokenizer);
			chargerInfo[idx][3] = read(tokenizer);
		}

		for (int row = 0; row < 10; row++) {
			for (int col = 0; col < 10; col++) {
				for (int charger = 0; charger < chargerCount; charger++) {
					map[row][col][charger] = isInRange(row, col, charger);
				}
			}
		}

	}

	static boolean isInRange(int row, int col, int chargerIdx) {
		int distance = Math.abs(row - chargerInfo[chargerIdx][0]) + Math.abs(col - chargerInfo[chargerIdx][1]);
		if (distance <= chargerInfo[chargerIdx][2])
			return true;
		return false;
	}

	static int getChargeableValueOf(int userNumber, int batteryIdx) {
		if (map[userPos[userNumber][0]][userPos[userNumber][1]][batteryIdx]) {
			return chargerInfo[batteryIdx][3];
		}
		return 0;
	}

	static void simulate() {
		for (int time = 0; time <= totalMoveTime; time++) {

			for (int userNumber = 0; userNumber < 2; userNumber++) {
				userPos[userNumber][0] += dy[userRoute[userNumber][time]];
				userPos[userNumber][1] += dx[userRoute[userNumber][time]];
			}

			int tempMaxChargeValueSum = 0;

			for (int chargerIdxOfA = 0; chargerIdxOfA < chargerCount; chargerIdxOfA++) {
				for (int chargerIdxOfB = 0; chargerIdxOfB < chargerCount; chargerIdxOfB++) {

					int chargeValueOfA = getChargeableValueOf(0, chargerIdxOfA);
					int chargeValueOfB = getChargeableValueOf(1, chargerIdxOfB);

					if (chargerIdxOfA == chargerIdxOfB && chargeValueOfA == chargeValueOfB) {
						chargeValueOfA /= 2;
						chargeValueOfB /= 2;
					}

					tempMaxChargeValueSum = Math.max(tempMaxChargeValueSum, chargeValueOfA + chargeValueOfB);
				}
			}
			maxChargeSum += tempMaxChargeValueSum;
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);
		StringBuilder builder = new StringBuilder();

		int testCount = read(tokenizer);

		for (int testNumber = 1; testNumber <= testCount; testNumber++) {
			init(tokenizer);
			simulate();
			builder.append('#').append(testNumber).append(' ').append(maxChargeSum).append('\n');
		}

		System.out.println(builder);
	}

	static int read(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}
}
