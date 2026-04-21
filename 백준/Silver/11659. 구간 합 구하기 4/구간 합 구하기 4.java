import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 아주 간단한 구간합 구하기 문제.
 * 1.입력을 통한 초기화
 * 		1-1. 입력과 동시에 현재 위치까지의 합을 따로 저장함.
 * 2. 요구하는 구간에 대해 구간합을 출력한다.
 *
 */
public class Main {

	static int[] sums;
	static int[][] queries;
	static int numberAmount;
	static int questCount;
	static StringBuilder builder = new StringBuilder();

	static void init(BufferedReader reader) throws IOException {
		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

		numberAmount = Integer.parseInt(tokenizer.nextToken());
		questCount = Integer.parseInt(tokenizer.nextToken());

		sums = new int[numberAmount + 1];
		tokenizer = new StringTokenizer(reader.readLine());

		for (int idx = 1; idx <= numberAmount; idx++) {
			sums[idx] = sums[idx - 1] + Integer.parseInt(tokenizer.nextToken());
		}

		queries = new int[questCount][2];

		for (int idx = 0; idx < questCount; idx++) {
			tokenizer = new StringTokenizer(reader.readLine());

			queries[idx][0] = Integer.parseInt(tokenizer.nextToken());
			queries[idx][1] = Integer.parseInt(tokenizer.nextToken());
		}
	}

	static void query() {
		for (int idx = 0; idx < questCount; idx++) {
			builder.append(sums[queries[idx][1]] - sums[queries[idx][0] - 1]).append('\n');
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		init(reader);
		query();
		System.out.println(builder);
		reader.close();
	}

}
