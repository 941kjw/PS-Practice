import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.Arrays;

public class Solution {

	private static int islandCount;

	private static Island[] islands;
	private static int[] parents;
	private static int[] ranks;
	private static double taxRate;
	private static double minTax;

	private static Edge[] edges;

	private static void init(StreamTokenizer tokenizer) throws IOException {

		islandCount = read(tokenizer);

		parents = new int[islandCount];
		ranks = new int[islandCount];
		islands = new Island[islandCount];
		edges = new Edge[islandCount * (islandCount - 1) / 2];
		minTax = 0;

		for (int id = 0; id < islandCount; id++) {
			parents[id] = id;
			islands[id] = new Island();
			islands[id].x = read(tokenizer);
		}

		for (int id = 0; id < islandCount; id++) {
			islands[id].y = read(tokenizer);
		}

		taxRate = readDouble(tokenizer);

		makeEdges();

		Arrays.sort(edges);
	}

	private static void makeEdges() {
		int idx = 0;

		for (int from = 0; from < islandCount; from++) {
			for (int to = from + 1; to < islandCount; to++) {
				double price = getDistance(islands[from], islands[to]) * taxRate;
				edges[idx++] = new Edge(from, to, price);
			}
		}
	}

	private static int find(int islandId) {
		if (parents[islandId] == islandId)
			return islandId;

		return parents[islandId] = find(parents[islandId]);
	}

	private static boolean union(int from, int to) {
		int fromParent = find(from);
		int toParent = find(to);

		if (fromParent == toParent)
			return false;

		if (ranks[fromParent] > ranks[toParent]) {
			parents[toParent] = fromParent;
			return true;
		}

		if (ranks[fromParent] == ranks[toParent])
			ranks[toParent]++;

		parents[fromParent] = toParent;

		return true;
	}

	private static void findMinCombination() {
		int count = 0;
		int max = islandCount - 1;
		for (Edge edge : edges) {
			if (union(edge.from, edge.to)) {
				minTax += edge.price;
				if (++count == max) {
					break;
				}
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
			findMinCombination();
			builder.append('#').append(testNumber).append(' ').append(Math.round(minTax)).append('\n');
		}

		System.out.println(builder);

	}

	private static int read(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return (int) tokenizer.nval;
	}

	private static double readDouble(StreamTokenizer tokenizer) throws IOException {
		tokenizer.nextToken();
		return tokenizer.nval;
	}

	private static double getDistance(Island island1, Island island2) {
		return Math.pow(Math.abs(island1.x - island2.x), 2) + Math.pow(Math.abs(island1.y - island2.y), 2);
	}

	private static class Island {
		int x, y;
	}

	private static class Edge implements Comparable<Edge> {
		int from, to;
		double price;

		public Edge(int from, int to, double price) {
			this.from = from;
			this.to = to;
			this.price = price;
		}

		@Override
		public int compareTo(Edge o) {
			return Double.compare(this.price, o.price);
		}

		@Override
		public String toString() {
			return "Edge [from=" + from + ", to=" + to + ", price=" + price + "]";
		}

	}

}
