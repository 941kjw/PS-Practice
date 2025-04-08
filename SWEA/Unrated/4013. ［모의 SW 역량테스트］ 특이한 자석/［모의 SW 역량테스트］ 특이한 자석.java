import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * 
 * 기어를 회전할 때, 양 옆 기어의 마주보고있는 톱니의 극성이 다를 경우, 반대 방향으로 같이 회전한다.
 * 
 *  1. 기어의 극성 정보를 int형 데이터 내에 비트 연산으로 입력받는다.
 *  2. 기어 회전을 시뮬레이션한다.
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
		for (int idx = 1; idx < 5; ++idx) {
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

	private static boolean checkAffected(int left, int right) {

		int leftFacingTeeth = pos[left] + 2;
		int rightFacingTeeth = pos[right] - 2;

		if (leftFacingTeeth >= 8)
			leftFacingTeeth -= 8;

		if (rightFacingTeeth < 0)
			rightFacingTeeth += 8;

		leftFacingTeeth = posToIdx(leftFacingTeeth);
		rightFacingTeeth = posToIdx(rightFacingTeeth);

		int leftPole = (gears[left] >> leftFacingTeeth) & 1;
		int rightPole = (gears[right] >> rightFacingTeeth) & 1;

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

		for (int testNumber = 1; testNumber <= testCount; ++testNumber) {
			init(tokenizer);
			builder.append('#').append(testNumber).append(' ').append(simulateRotation(tokenizer)).append('\n');
		}

		System.out.println(builder);
	}

}
