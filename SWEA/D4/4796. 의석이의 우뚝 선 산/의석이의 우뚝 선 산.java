import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * 
 * 왼쪽은 증가하는 ,오른쪽은 감소하는 형세의 지리를 찾아야 한다.
 * 
 * 증가하는 구간의 갯수 * 감소하는 구간의 갯수를 하면, 모든 조합의 가짓수가 나온다.
 * ex) 입력이 1 4 6 5 3일때, 증가하는 구간은 1 4, 4 6, 그리고 감소하는 구간은 6 5, 5 3이다.
 * 이때, 증가 * 감소는 4개이고,
 * 가능한 가짓수는 1 4 6 5, 1 4 6 5 3, 4 6 5, 4 6 5 3으로 4가지이다.
 * 
 * 1.입력 초기화
 * 2.지형 정보를 순회
 * 		2-1. 증가/감소 갯수를 초기화 한다.
 * 		2-2. 증가 구간일 경우
 * 			2-2-a. 이전까지 감소 구간이 없었다면 증가 구간 갯수를 증가.
 * 			2-2-b. 감소 구간이 있었다면, 새로운 증가-감소 구간이므로, 이전까지의 결과를 합하고, 초기화.
 * 		2-3. 감소 구간일 경우 증가 구간이 있다면 감소 구간 갯수 증가.(증가 구간이 이전에 있어야 의미가 있다.)
 * 3. 결과 출력.
 * 
 *
 */
public class Solution {

	static int[] mountains = new int[50000];
	static int length;
	static StringBuilder builder = new StringBuilder();

	static void init(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		length = (int) tokenizer.nval;

		for (int idx = 0; idx < length; idx++) {
			tokenizer.nextToken();
			mountains[idx] = (int) tokenizer.nval;
		}

	}

	static int terrainRecon() {
		int increase = 0;
		int decrease = 0;
		int sum = 0;
		for (int idx = 0; idx < length - 1; idx++) {
			int prev = mountains[idx];
			int next = mountains[idx + 1];

			if (prev < next) {
				if (decrease == 0)
					increase++;
				else {
					sum += (increase * decrease);
					increase = 1;
					decrease = 0;
				}
			} else {
				if (increase != 0)
					decrease++;
			}
		}
		sum += (increase * decrease);
		return sum;
	}

	public static void main(String[] args) throws IOException {
		InputStreamReader reader = new InputStreamReader(System.in);
		StreamTokenizer tokenizer = new StreamTokenizer(reader);

		tokenizer.nextToken();
		int testCount = (int) tokenizer.nval;

		for (int testNumber = 1; testNumber <= testCount; testNumber++) {

			init(tokenizer);
			builder.append('#').append(testNumber).append(' ').append(terrainRecon()).append('\n');
		}

		System.out.println(builder);
	}
}
