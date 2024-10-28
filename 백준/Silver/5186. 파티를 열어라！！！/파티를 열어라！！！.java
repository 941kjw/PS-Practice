import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int count = Integer.parseInt(st.nextToken());
        for (int i = 1; i <= count; i++) {
            st = new StringTokenizer(br.readLine());
            int n, c, l;
            n = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
            l = Integer.parseInt(st.nextToken());
            int[][] cityAndState = new int[l + 1][2];
            for (int j = 0; j < n; j++) {
                st = new StringTokenizer(br.readLine());
                int city = Integer.parseInt(st.nextToken());
                int state = st.nextToken().equals("I") ? 1 : 0;
                cityAndState[city][state]++;
            }

            PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> o2[1] - o1[1]);
            for (int j = 0; j < c; j++) {
                st = new StringTokenizer(br.readLine());
                int to = Integer.parseInt(st.nextToken());
                int total = Integer.parseInt(st.nextToken());
                pq.add(new int[]{to, total});
            }

            while (!pq.isEmpty()) {
                int[] cur = pq.poll();
                if (cityAndState[cur[0]][0] >= 1) {
                    cityAndState[cur[0]][0]--;
                    cur[1]--;
                    if (cityAndState[cur[0]][1] != 0) {
                        int left = Math.min(cityAndState[cur[0]][1], cur[1]);
                        cityAndState[cur[0]][1] -= left;
                        cur[1] -= left;
                    }
                    if (cur[1] != 0) {
                        int left = Math.min(cityAndState[cur[0]][0], cur[1]);
                        cityAndState[cur[0]][0] -= left;
                    }
                }
            }

            int t = 0;
            for (int j = 1; j <= l; j++) {
                t += cityAndState[j][0] + cityAndState[j][1];
            }

            bw.write("Data Set " + i + ":");
            bw.newLine();
            bw.write(Integer.toString(t));
            bw.newLine();
        }
        bw.close();
        br.close();
    }
}