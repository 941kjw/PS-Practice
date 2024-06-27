import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;


public class Main {

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static char[][] board;
    static int[][] dp;
    static int maxl = 0;
    static int R, C;

    public static void dfs(int y, int x, int count, int visited) {
        visited |= (1 << board[y][x]);
        if (dp[y][x] == visited) {
            return;
        }
        maxl = Math.max(maxl, count);

        dp[y][x] = visited;

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            if (nx < 0 || nx >= C || ny < 0 || ny >= R) {
                continue;
            }
            if ((visited & (1 << board[ny][nx])) == 0) {
                dfs(ny, nx, count + 1, visited);
            }
        }

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        board = new char[R][C];
        dp = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for (int j = 0; j < C; j++) {
                board[i][j] = s.charAt(j);
            }
        }
        dfs(0, 0, 1, 0);
        bw.write(String.valueOf(maxl));
        bw.flush();

        br.close();
        bw.close();
    }
}