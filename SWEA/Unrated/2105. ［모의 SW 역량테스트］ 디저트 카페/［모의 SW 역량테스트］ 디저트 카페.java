import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StreamTokenizer;

/**
 * 기존과 다르게 대각선으로 DFS를 하는 특이한 탐색 문제.
 *
 * 1.입력 초기화
 * 2.다이아몬드 모양을 만들 수 있는 모든 칸에 대해 탐색 실행.
 *      2-1.이전 방향~ 마지막 방향까지 탐색
 *      2-2. 다음 칸이 범위 내일 때,
 *          2-2-1. 시작 지점에 다시 도착했다면, 그리고 최소 4개의 디저트를 포함하고있다면, 최댓값 갱신.
 *          2-2-2. 아직 방문하지 않았고, 아직 등장하지 않은 수이면 재귀 탐색.
 * 3. 결과물 출력.
 *
 */
public class Solution {

    static int length;
    static int[][] dessertMap;

    //우하 -> 좌하 -> 좌상 -> 우상의 다이아몬드 모양
    static int[] dx = {1, -1, -1, 1};
    static int[] dy = {1, 1, -1, -1};

    static boolean[][] visited;
    static boolean[] dessertKindSet;

    static int maxCount;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StreamTokenizer tokenizer = new StreamTokenizer(reader);
        StringBuilder builder = new StringBuilder();

        int testCount = read(tokenizer);

        for (int testNumber = 1; testNumber <= testCount; testNumber++) {
            init(tokenizer);
            searchLongestRoute();
            builder.append('#').append(testNumber).append(' ').append(maxCount).append('\n');
        }

        System.out.println(builder);
    }

    //다이아몬드 모양을 만들려면 최소한 오른쪽으로 2칸이 보장되어야 함.
    private static void searchLongestRoute() {
        for (int row = 0; row < length - 2; row++) {
            for (int col = 1; col < length - 1; col++) {
                visited = new boolean[length][length];
                dessertKindSet = new boolean[101];
                visited[row][col] = true;
                dessertKindSet[dessertMap[row][col]] = true;
                dfs(1, row, col, row, col, 0);
            }
        }
    }

    //직전 사용한 방향을 이어서 시작. 갑자기 이전 방향을 사용하면 이상하게 꺾일 수 있다.
    static void dfs(int count, int currentRow, int currentCol, int startRow, int startCol, int previousDirection) {
        for (int dir = previousDirection; dir < 4; dir++) {
            int nRow = currentRow + dy[dir];
            int nCol = currentCol + dx[dir];

            if (nRow > -1 && nRow < length && nCol > -1 && nCol < length) {

                //진행은 했으나 중간에 실패해서 다시 역류했을 때, 최소 4개는 있어야 마름모가 완성된 것.
                if (nRow == startRow && nCol == startCol && count >= 4) {
                    maxCount = Math.max(maxCount, count);
                    return;
                }

                if (!visited[nRow][nCol] && !dessertKindSet[dessertMap[nRow][nCol]]) {
                    visited[nRow][nCol] = true;
                    dessertKindSet[dessertMap[nRow][nCol]] = true;
                    dfs(count + 1, nRow, nCol, startRow, startCol, dir);
                    visited[nRow][nCol] = false;
                    dessertKindSet[dessertMap[nRow][nCol]] = false;
                }
            }
        }
    }


    static void init(StreamTokenizer tokenizer) throws IOException {
        length = read(tokenizer);
        dessertMap = new int[length][length];
        maxCount = -1;

        for (int row = 0; row < length; row++) {
            for (int col = 0; col < length; col++) {
                dessertMap[row][col] = read(tokenizer);
            }
        }
    }


    static int read(StreamTokenizer tokenizer) throws IOException {
        tokenizer.nextToken();
        return (int) tokenizer.nval;
    }
}
