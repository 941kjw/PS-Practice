import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

/**
 * 
 * 단순한 크루스칼 알고리즘 문제
 * 
 * 
 * 1. 입력받은 간선을 작은 순서대로 정렬한다.
 * 2. 최상단 간선부터 선택하며, union-find를 통해 같은 그래프 상에 존재하는 정점이면 건너뛴다.
 * 3. 정점 수 -1 만큼 선택하면 종료한다.
 * 
 *
 */

public class Solution {

	private static int vertexCount;
	private static int edgeCount;

	private static int[] parents;
	private static int[] ranks;
	private static long weightSum;

	private static Edge[] edges;

	private static void init(StreamTokenizer tokenizer) throws IOException {

		vertexCount = read(tokenizer);
		edgeCount = read(tokenizer);

		parents = new int[vertexCount];
		ranks = new int[vertexCount];
		edges = new Edge[edgeCount];
		weightSum = 0;

		for (int idx = 0; idx < edgeCount; idx++) {
			int from = read(tokenizer) - 1;
			int to = read(tokenizer) - 1;
			int weight = read(tokenizer);

			edges[idx] = new Edge(from, to, weight);
		}

		for (int idx = 0; idx < vertexCount; idx++) {
			parents[idx] = idx;
		}

		Arrays.sort(edges);
	}

	private static int find(int vertexNum) {
		if (parents[vertexNum] == vertexNum)
			return vertexNum;
		return parents[vertexNum] = find(parents[vertexNum]);
	}

	private static boolean union(int from, int to) {
		int fromParent = find(from);
		int toParent = find(to);

		if (fromParent == toParent)
			return false;

//		if (ranks[fromParent] > ranks[toParent]) {
//			parents[toParent] = fromParent;
//			return true;
//		}
//
//		if (ranks[fromParent] == ranks[toParent])
//			ranks[toParent]++;

		parents[fromParent] = toParent;

		return true;
	}

	private static void makeTree() {
		int count = 0;
		int max = vertexCount - 1;

		for (Edge edge : edges) {
			if (union(edge.from, edge.to)) {
				weightSum += edge.weight;

				if (++count == max)
					break;
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		StreamTokenizer tokenizer = new StreamTokenizer(reader);
		StringBuilder builder = new StringBuilder();

		int testCount = read(tokenizer);

		for (int testNumber = 1; testNumber <= testCount; testNumber++) {
			init(tokenizer);
			makeTree();
			builder.append('#').append(testNumber).append(' ').append(weightSum).append('\n');

		}
		System.out.println(builder);
	}

	private static class Edge implements Comparable<Edge> {
		int from, to, weight;

		public Edge(int from, int to, int weight) {
			this.from = from;
			this.to = to;
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return Integer.compare(this.weight, o.weight);
		}
	}

	private static int read(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}
}
