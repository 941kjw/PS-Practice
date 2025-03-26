import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

/**
 * 비상 연락망이 주어진다.단, 상호 연락이 가능한게 아니고 편도로만 가능하다.
 * 특정인이 연락 담당이 되고, 이 사람으로부터 마지막으로 연락을 받는 사람들 중, 가장 번호가 큰 사람을 구하자.
 * depth를 활용?
 * 단일 탐색이므로 depth의 최댓값과, 최대 번호를 저장해 두자.
 *
 * 1. 입력으로 그래프를 초기화.
 * 2. depth 정보를 이용하는 BFS 탐색을 통해, 매번 탐색이 종료될 때, 각 depth의 가장 번호가 큰 사람을 저장함.
 *      2-1. 마지막 탐색은 무조건 0이 차므로, index를 하나 더 빼줘야 한다. (말단 노드이기 때문에)
 *
 *
 */
public class Solution {

    private static int starter;

    private static int maxNumber;

    private static boolean[][] phoneMap;
    private static boolean[] visited;

    private static void init(StreamTokenizer tokenizer) throws IOException {
        int phoneLineCount = read(tokenizer);
        starter = read(tokenizer);
        maxNumber = 0;

        phoneMap = new boolean[101][101];
        visited = new boolean[101];

        for (int idx = 0; idx < phoneLineCount / 2; idx++) {
            int element1 = read(tokenizer);
            int element2 = read(tokenizer);

            phoneMap[element1][element2] = true;
        }

    }

    /**
     * BFS. 일반적인 BFS와 다르게 처음 시작 시점에서 큐 크기를 구하고, 그만큼만 poll하면 같은 depth 내 탐색이 된다.
     */
    private static void makeCalls(int current) {
        Queue<Integer> queue = new ArrayDeque<>();
        visited[current] = true;
        queue.offer(current);

        List<Integer> depthMaxList = new ArrayList<>();
        while (!queue.isEmpty()) {
            int sameDepthCount = queue.size();
            int max = 0;

            for (int idx = 0; idx < sameDepthCount; ++idx) {
                int cur = queue.poll();

                for (int opponentNumber = 1; opponentNumber < 101; opponentNumber++) {
                    if (phoneMap[cur][opponentNumber] && !visited[opponentNumber]) {
                        queue.offer(opponentNumber);
                        visited[opponentNumber] = true;
                        max = Math.max(max, opponentNumber);
                    }
                }
            }
            depthMaxList.add(max);
        }
        maxNumber = depthMaxList.get(depthMaxList.size() - 2);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer tokenizer = new StreamTokenizer(reader);

        for (int testNumber = 1; testNumber <= 10; testNumber++) {
            init(tokenizer);
            makeCalls(starter);

            System.out.println("#" + testNumber + " " + maxNumber);
        }
    }

    private static int read(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        return (int) tokenizer.nval;
    }

}
