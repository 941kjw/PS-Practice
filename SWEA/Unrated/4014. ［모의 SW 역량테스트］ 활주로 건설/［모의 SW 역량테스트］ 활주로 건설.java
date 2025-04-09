import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * 
 * 
 * 각 행/열별로 높이가 낮은 쪽에 경사로를 설치할 수 있어야 함.
 * 길이가 최소 경사로 길이 이상은 되어야 한다.
 * 연속 2개를 설치하는 경우도 서로가 간섭이 없어야 함.
 * 
 * 어떻게 할까?
 * 직접 갯수 세기?
 * 만약 기존 높이와 다른 지점이 있다면 다른 처리?
 * 
 *
 */
public class Solution {

	private static int[][] map;
	private static int length;
	private static int rampLength;

	private static void init(StreamTokenizer tokenizer) throws IOException {
		length = read(tokenizer);
		rampLength = read(tokenizer);

		map = new int[length][length];

		for (int row = 0; row < length; row++) {
			for (int col = 0; col < length; col++) {
				map[row][col] = read(tokenizer);
			}
		}
	}

	/**
	 * 경사로를 놓을 수 없는 경우:
	 * 
	 * 1.올라가는 경사로를 놓아야 하는데, 이전 공간이 부족할 경우.
	 * 2.다른 경사로를 놓아야 하는데, 이전 경사로의 빚이 아직 끝나지 않은 경우.
	 * 3.차이가 2 이상인 경우
	 * 4.지도 밖으로 삐져나올 경우.
	 * 5.
	 * 
	 */
	private static int countAvailable() {
		int sum = 0;
		for (int row = 0; row < length; row++) {
			int curHeight = map[row][0];
			int count = 1;
			boolean isAvailable = true;
			for (int col = 1; col < length; col++) {
				if (curHeight == map[row][col]) {
					++count;
				}

				//단차가 너무 크다.
				else if (Math.abs(curHeight - map[row][col]) > 1) {
					isAvailable = false;
					break;
				}

				// 올라가는 경사로
				else if (curHeight < map[row][col]) {

					//공간이 부족하거나... 이전 경사로의 빚이 안끝났거나...
					if (count < rampLength) {
						isAvailable = false;
						break;
					}
					curHeight = map[row][col];
					count = 1;
				}

				// 내려가는 경사로
				else if (curHeight > map[row][col]) {
					// 이전 경사로의 빚이 끝나지 않았다?
					if (count < 0) {
						isAvailable = false;
						break;
					}

					//높이 변경 후 빚 지우기.
					curHeight = map[row][col];
					count = -rampLength + 1;
				}
			}

			//빚을 다 못 갚고 끝난 경우, 혹은 그냥 설치가 불가능한 경우를 거름.
			if (count >= 0 && isAvailable) {
				++sum;
				//				System.out.println("Row : " + row);
			}
		}

		for (int col = 0; col < length; col++) {
			int curHeight = map[0][col];
			int count = 1;
			boolean isAvailable = true;
			for (int row = 1; row < length; row++) {
				if (curHeight == map[row][col]) {
					++count;
				}

				else if (Math.abs(curHeight - map[row][col]) > 1) {
					isAvailable = false;
					break;
				}

				else if (curHeight < map[row][col]) {
					if (count < rampLength) {
						isAvailable = false;
						break;
					}
					curHeight = map[row][col];
					count = 1;
				}
				else if (curHeight > map[row][col]) {
					if (count < 0) {
						isAvailable = false;
						break;
					}
					curHeight = map[row][col];
					count = -rampLength + 1;
				}
			}

			if (count >= 0 && isAvailable) {
				++sum;
				//				System.out.println("Col : " + col);
			}
		}

		return sum;

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

			builder.append('#').append(testNumber).append(' ').append(countAvailable()).append('\n');
		}

		System.out.println(builder);
	}

}
