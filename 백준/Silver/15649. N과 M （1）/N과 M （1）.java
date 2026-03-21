import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


/**
 * 단순한 순열.
 * 
 * 1. 입력을 받음
 * 2. 입력받은 원소 수와, 고를 수 갯수를 토대로 배열 초기화.
 * 3. 순열 생성 (재귀)
 * 		3-a. 만약 전부 골랐다면
 * 			3-a-1. 고른 배열 내 숫자를 출력
 * 		3-b. 전체 원소들을 순회.
 * 			3-b-1. 이미 사용한 수라면 건너뛰기.
 * 			3-b-2. 고르지 않은 수라면 고른 후, 다음 재귀 호출.
 * 			3-b-3. 다음 재귀를 위해 사용 취소하기.
 * 
 *
 */
public class Main {

	static int[] elements;
	static boolean[] used;
	static int[] selected;
	static int elementCount;
	static int selectCount;
	static StringBuilder builder = new StringBuilder();
	private static void permutation(int selectedIdx) throws IOException {
		
		if(selectedIdx == selectCount) {
			for(int num : selected) {
				builder.append(num + " ");
			}
			builder.append("\n");
			return;
		}
		
		for(int idx = 0; idx < elementCount; idx++) {
			if(!used[idx]) {
				used[idx] = true;
				selected[selectedIdx]= elements[idx];
				permutation(selectedIdx+1);
				used[idx] = false;
			}
		}
		
	}
	
	public static void main(String[] args) throws IOException {
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		String[] input = reader.readLine().split(" ");
		
		elementCount = Integer.parseInt(input[0]);
		selectCount = Integer.parseInt(input[1]);
		
		used = new boolean[elementCount];
		selected = new int[selectCount];
		elements = new int[elementCount];
		
		for(int i=1;i<=elementCount; i++) {
			elements[i-1] = i;
		}
		
		permutation(0);
		System.out.println(builder);
		reader.close();
	}
}