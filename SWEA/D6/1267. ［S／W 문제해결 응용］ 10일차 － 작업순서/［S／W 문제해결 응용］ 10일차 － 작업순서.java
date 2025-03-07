import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 간단한 위상 정렬 문제.
 * 제일 먼저 실행할 수 있는 작업은 선행 요구 조건의 갯수가 0이다.
 * 작업을 실행한 후, 후속 작업의 요구 갯수를 1 감소시켜줘야 한다.
 * 
 * 1.입력 초기화
 * 2.작업 순서를 만드는 메소드 호출
 * 		2-1. 모든 작업이 완료될 때까지 반복.
 * 			2-1-a. 만약 선행 요구 조건이 없다면,
 * 				2-1-a-ㄱ. 자기 자신의 선행 요구 조건을 1만큼 빼줌(재방문 방지)
 * 				2-1-a-ㄴ. 작업 순서에 자기 자신을 기록.
 * 				2-1-a-ㄷ. 후속 작업의 선행 작업 수를 1만큼 빼준다.
 * 3. 완성한 작업 순서를 출력.
 * 
 * 모든 노드를 1회 탐색하고, 이때 간선을 전부 탐색하므로
 * 
 * O(V+E)?
 * 
 */
public class Solution {

	static int vertexCount;
	static int edgeCount;

	static int[] inflow;
	static StringBuilder builder;
	static List <List <Integer>> edgeList;

	static void init(StreamTokenizer tokenizer) throws IOException {
		vertexCount = read(tokenizer);
		edgeCount = read(tokenizer);

		edgeList = new ArrayList <>();

		for (int vertexNumber = 0; vertexNumber < vertexCount; vertexNumber++) {
			edgeList.add(new LinkedList <>());
		}

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
		Queue <Integer> queue = new ArrayDeque <>();

		for (int idx = 0; idx < inflow.length; idx++) {
			if (inflow[idx] == 0)
				queue.add(idx);
		}

		while (!queue.isEmpty()) {
			int currentIdx = queue.poll();
			builder.append(currentIdx + 1).append(' ');

			for (int next : edgeList.get(currentIdx)) {
				if (--inflow[next] == 0) {
					queue.add(next);
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);
		builder = new StringBuilder();
		for (int testNumber = 1; testNumber <= 10; testNumber++) {
			builder.append('#').append(testNumber).append(' ');
			init(tokenizer);

			makeWorkOrder();
			builder.append('\n');
		}

		System.out.println(builder);
	}

}
