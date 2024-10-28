import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int count = Integer.parseInt(st.nextToken());
        int[] likeList = new int[count];
        int likeTotal = 0;
        int nonViewTotal = 0;
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < count; i++) {
            likeList[i] = Integer.parseInt(st.nextToken());
            likeTotal += likeList[i];
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < count; i++) {
            int num = Integer.parseInt(st.nextToken());
            if (num == 0) {
                nonViewTotal += likeList[i];
            }
        }

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        bw.write(Integer.toString(likeTotal));
        bw.newLine();
        bw.write(Integer.toString(nonViewTotal));
        bw.flush();
        bw.close();
        br.close();
    }
}