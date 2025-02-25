import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 
 * 최대한 신맛의 곱과 쓴맛의 합의 차가 적은 요리를 만들자.
 * 
 * 
 * 1.입력 초기화
 * 2.테스트 수만큼 루프
 * 	2-1. 부분집합 생성 재귀 실행
 * 		2-1-a. 재료 수 한도에 도달한 경우:
 * 			2-1-a-ㄱ. 0개 고른 부분집합일 경우 return.
 * 			2-1-a-ㄴ. 최대 맛 점수 갱신. 이때, 절댓값을 취해줘야 한다!
 *			2-1-a-ㄷ. return.
 * 		2-1-b. 그렇지 않을 경우, 경로 분기.
 * 			2-1-b-ㄱ. 현재 재료를 선택.
 * 			2-1-b-ㄴ. 선택하지 않음.
 * 	2-2. 최대 맛 점수 출력.
 *
 */
public class Main {

	static int minGap;
	static int elementCount;
	static int[] sourScore;
	static int[] bitterScore;
	static boolean[] selected;

	//입력을 통한 초기화.
	static void init(BufferedReader reader) throws IOException {
		minGap = Integer.MAX_VALUE;

		StringTokenizer tokenizer = new StringTokenizer(reader.readLine());
		elementCount = Integer.parseInt(tokenizer.nextToken());

		sourScore = new int[elementCount];
		bitterScore = new int[elementCount];
		selected = new boolean[elementCount];

		for (int idx = 0; idx < elementCount; idx++) {
			tokenizer = new StringTokenizer(reader.readLine());
			sourScore[idx] = Integer.parseInt(tokenizer.nextToken());
			bitterScore[idx] = Integer.parseInt(tokenizer.nextToken());
		}
	}

	static void makeRecipe(int elementIdx, int selectedCount) {
		if (elementIdx == elementCount) {
			if (selectedCount == 0)
				return;
			int sourSum = 1;
			int bitterSum = 0;
			for (int idx = 0; idx < elementCount; idx++) {
				if (selected[idx]) {
					sourSum *= sourScore[idx];
					bitterSum += bitterScore[idx];
				}
			}

			minGap = Math.min(Math.abs(sourSum - bitterSum), minGap);
			return;
		}

		selected[elementIdx] = true;
		makeRecipe(elementIdx + 1, selectedCount + 1);

		selected[elementIdx] = false;
		makeRecipe(elementIdx + 1, selectedCount);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

		init(reader);
		makeRecipe(0, 0);

		System.out.println(minGap);

		reader.close();
	}
}
