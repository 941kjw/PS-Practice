import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * 
 * DP를 이용한 냅색 문제
 * 
 * 제한된 부피 내에서 가장 가치의 합이 큰 경우를 구해야 한다.
 * 
 * 1.각 물건에 대해서
 * 	1-1.최대 부피에서, 현재 물건의 부피까지
 * 		1-1-1.현재 부피 제한에서 최댓값을 구한다. 이전에 기록했던 최대치와, 현재 물건을 추가할 수 있던 시점의 최댓값에서 물건의 가치만큼 더한 값을 비교한다. 
 *
 */
public class Solution {

	private static int rotationCount;
	private static int[] gears;
	private static int[] pos;
	private static boolean[] visited;

	private static void init(StreamTokenizer tokenizer) throws IOException {
		rotationCount = read(tokenizer);
		gears = new int[5];
		pos = new int[5];
		visited = new boolean[5];

		for (int idx = 1; idx <= 4; ++idx) {
			for (int gearTeeth = 0; gearTeeth < 8; ++gearTeeth) {
				gears[idx] <<= 1;
				gears[idx] |= read(tokenizer);
			}
		}
	}

	private static int simulateRotation(StreamTokenizer tokenizer) throws IOException {
		while (rotationCount-- > 0) {
			int rotateTarget = read(tokenizer);
			int direction = read(tokenizer);

			spreadRotationEffect(rotateTarget, direction);
		}
		int score = 0;
		for (int idx = 1; idx < 5; idx++) {
			if ((gears[idx] & (1 << posToIdx(pos[idx]))) != 0) {
				score += (1 << (idx - 1));
			}
		}
		return score;
	}

	private static void spreadRotationEffect(int target, int direction) {
		visited[target] = true;
		if (target - 1 > 0 && !visited[target - 1] && checkAffected(target - 1, target))
			spreadRotationEffect(target - 1, -1 * direction);

		if (target + 1 < 5 && !visited[target + 1] && checkAffected(target, target + 1))
			spreadRotationEffect(target + 1, -1 * direction);

		rotate(target, direction);
		visited[target] = false;
	}

	private static void rotate(int target, int direction) {
		pos[target] -= direction;
		if (pos[target] < 0)
			pos[target] += 8;
		else if (pos[target] > 7)
			pos[target] -= 8;
	}

	private static void print() {
		for (int idx = 1; idx <= 4; ++idx) {
			for (int i = 7; i > -1; --i) {
				if (i == posToIdx(pos[idx]))
					System.out.print('x');
				else
					System.out.print((gears[idx] >> i) & 1);
			}
			System.out.println();
		}
	}

	private static boolean checkAffected(int left, int right) {

		int leftFacingTeeth = pos[left] + 2;
		int rightFacingTeeth = pos[right] - 2;

		if (leftFacingTeeth >= 8)
			leftFacingTeeth -= 8;

		if (rightFacingTeeth < 0)
			rightFacingTeeth += 8;

		leftFacingTeeth = posToIdx(leftFacingTeeth);
		rightFacingTeeth = posToIdx(rightFacingTeeth);

		int leftPole = (gears[left] & (1 << leftFacingTeeth)) == 0 ? 0 : 1;
		int rightPole = (gears[right] & (1 << rightFacingTeeth)) == 0 ? 0 : 1;

		return leftPole != rightPole;
	}

	private static int posToIdx(int pos) {
		return 7 - pos;
	}

	private static int read(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);
		StringBuilder builder = new StringBuilder();
		int testCount = read(tokenizer);

		for (int testNumber = 1; testNumber <= testCount; testNumber++) {
			init(tokenizer);
			builder.append('#').append(testNumber).append(' ').append(simulateRotation(tokenizer)).append('\n');
		}

		System.out.println(builder);
	}

}
