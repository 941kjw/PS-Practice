import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 간단한 위상 정렬 문제.
 * 제일 먼저 실행할 수 있는 작업은 선행 요구 조건의 갯수가 0이다.
 * 작업을 실행한 후, 후속 작업의 요구 갯수를 1 감소시켜줘야 한다.
 * 
 * 1.입력 초기화
 * 2.작업 순서를 만드는 메소드 호출
 * 		2-1. 모든 작업이 완료될 때까지 반복.
 * 			2-1-a. 만약 선행 요구 조건이 없다면
 * 				2-1-b.  
 *
 */
public class Solution {

	static int vertexCount;
	static int edgeCount;

	static int[] inflow;
	static int[] workOrder;
	static List <List <Integer>> edgeList;

	static void init(StreamTokenizer tokenizer) throws IOException {
		vertexCount = read(tokenizer);
		edgeCount = read(tokenizer);

		edgeList = new ArrayList <>();

		for (int vertexNumber = 0; vertexNumber < vertexCount; vertexNumber++) {
			edgeList.add(new LinkedList <>());
		}

		workOrder = new int[vertexCount];
		inflow = new int[vertexCount];

		for (int edgeNumber = 0; edgeNumber < edgeCount; edgeNumber++) {
			int from = read(tokenizer) - 1;
			int to = read(tokenizer) - 1;
			edgeList.get(from).add(to);
			inflow[to]++;
		}
	}

	static int read(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

	static void makeWorkOrder() {
		int count = 0;

		while (count < vertexCount) {
			for (int idx = 0; idx < vertexCount; idx++) {
				if (inflow[idx] == 0) {
					inflow[idx]--;
					workOrder[count++] = idx + 1;
					for (int adjacentVertex : edgeList.get(idx)) {
						inflow[adjacentVertex]--;
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);
		StringBuilder builder = new StringBuilder();
		for (int testNumber = 1; testNumber <= 10; testNumber++) {
			builder.append('#').append(testNumber).append(' ');
			init(tokenizer);

			makeWorkOrder();

			for (int idx = 0; idx < vertexCount; idx++) {
				builder.append(workOrder[idx]).append(' ');
			}
			builder.append('\n');
		}

		System.out.println(builder);
	}

}
